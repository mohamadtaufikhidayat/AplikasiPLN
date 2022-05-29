package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.*;
import com.prodemy.pembayaran.listrik.Service.TagihanService;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.TagihanDto;
import com.prodemy.pembayaran.listrik.model.entity.CatatMeter;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
import com.prodemy.pembayaran.listrik.model.entity.Transaksi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tagihan")
public class Tagihancontroller {

    private final Tagihanrepo tagihanrepo;
    private final DataPelRepo datapelrepo;
    private final CatatMeterRepo catatMeterRepo;
    private final Transaksirepo transaksirepo;
    @Autowired
    private TagihanService tagihanService;

    public Tagihancontroller(Tagihanrepo tagihanrepo, DataPelRepo datapelrepo,
                             CatatMeterRepo catatMeterRepo, Transaksirepo transaksirepo) {
        this.tagihanrepo = tagihanrepo;
        this.datapelrepo = datapelrepo;
        this.catatMeterRepo = catatMeterRepo;
        this.transaksirepo = transaksirepo;
    }

    @PostMapping("/savetrx")
    public DefaultResponse<TagihanDto> insertTrx(@RequestBody TagihanDto tagihanDto) {
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<CatatMeter> optional = catatMeterRepo.findByIdCatat(tagihanDto.getIdCatat());
        Optional<Tagihan> tagihanOptional = tagihanrepo.findByIdPenggunaListrikIdPenggunaAndBulan(optional.get()
                .getIdPenggunaListrik().getIdPengguna(), optional.get().getBulanini());
        if (tagihanOptional.isPresent()){
            response.setPesan("Tagihan sudah tersedia");
        } else {
            Tagihan tagihan = convertToEntity(tagihanDto);
            tagihanService.insertData(tagihan);
            response.setPesan("Tagihan berhasil tersimpan");
            response.setData(convertToDto(tagihan));
        }
        return response;
    }
    @PostMapping("/savenotrx")
    public DefaultResponse<TagihanDto> insertNoTrx(@RequestBody TagihanDto tagihanDto) {
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<CatatMeter> optional = catatMeterRepo.findByIdCatat(tagihanDto.getIdCatat());
        Optional<Tagihan> tagihanOptional = tagihanrepo.findByIdPenggunaListrikIdPenggunaAndBulan(optional.get()
                .getIdPenggunaListrik().getIdPengguna(), optional.get().getBulanini());
        if (tagihanOptional.isPresent()){
            response.setPesan("Tagihan sudah tersedia");
        } else {
            Tagihan tagihan = convertToEntity(tagihanDto);
            Tagihan entity = tagihanrepo.save(tagihan);
            entity.setIdPenggunaListrik(tagihan.getIdCatat().getIdPenggunaListrik());
            entity.setBulan(tagihan.getIdCatat().getBulanini());
            entity.setKwh(tagihan.getIdCatat().getCttkwh());
            entity.setBiaya((long)(tagihan.getIdCatat().getCttkwh()*tagihan.getIdPenggunaListrik().getIdJenis().getTarif()));
            tagihanrepo.save(entity);
            response.setPesan("Tagihan berhasil tersimpan");
            response.setData(convertToDto(tagihan));
        }
        return response;
    }
    @PostMapping("/savetagihan")
    public TagihanDto[] addtagihan(@RequestBody TagihanDto[] listbanyak) {
        for(TagihanDto m : listbanyak){
            tagihanrepo.save(convertDtoToEntity(m));
        }
        return listbanyak;
    }
    @GetMapping("/cektagihan/{idPenggunaListrik}/{bulan}")
    public DefaultResponse<TagihanDto> getByIdPenggunaListrik(@PathVariable Long idPenggunaListrik, String bulan) {
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<Tagihan> optional = tagihanrepo.findByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, bulan);
        if (optional.isPresent()) {
            Long noTag = optional.get().getNoTagihan();
            Optional<Transaksi> opt = transaksirepo.findByNoTagihanAndStatusTransaksi(noTag);
            if (opt.isEmpty()){
                response.setPesan("Berikut tagihan listrik Anda, silakan lanjutkan pembayaran");
                response.setData(convertEntityToDto(optional.get()));
            } else {
                response.setPesan("Tidak ada tagihan yang jatuh tempo saat ini");
            }
        } else {
            response.setPesan("Tagihan listrik Anda untuk bulan ini belum ada");
        }
        return response;
    }
    @GetMapping("/cekcurrent/{idPenggunaListrik}")
    public DefaultResponse<TagihanDto> getIdPenggunaListrik(@PathVariable Long idPenggunaListrik) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        String date = sdf.format(new Date());
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<Tagihan> optional = tagihanrepo.findByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, date);
        if (optional.isPresent()) {
            Long noTag = optional.get().getNoTagihan();
            Optional<Transaksi> opt = transaksirepo.findByNoTagihanAndStatusTransaksi(noTag);
            if (opt.isEmpty()){
                response.setPesan("Berikut tagihan listrik Anda, silakan lanjutkan pembayaran");
                response.setData(convertEntityToDto(optional.get()));
            } else {
                response.setPesan("Tidak ada tagihan yang jatuh tempo saat ini");
            }
        } else {
            response.setPesan("Tagihan listrik Anda untuk bulan ini belum ada");
        }
        return response;
    }
    @GetMapping("/history/{idPenggunaListrik}")
    public List<TagihanDto> getTagihanbyId(@PathVariable Long idPenggunaListrik){
        List<Tagihan> tghn = tagihanrepo.findByIdPenggunaListrikIdPengguna(idPenggunaListrik);
        List<TagihanDto> tdto = tghn.stream().map(this::convertIniToDto).collect(Collectors.toList());
        return tdto;
    }
    @GetMapping("/all")
    public List<TagihanDto> getListAll(){
        List<TagihanDto> list = new ArrayList<>();
        for(Tagihan m : tagihanrepo.findAll()) {
            list.add(convertIniToDto(m));
        }
        return list;
    }
    private Tagihan convertDtoToEntity(TagihanDto dto) {
        Tagihan tgh = new Tagihan();
        tgh.setNoTagihan(dto.getNoTagihan());
        if(datapelrepo.findById(dto.getIdPenggunaListrik()).isPresent()){
            PenggunaListrik penggunaListrik =  datapelrepo.findById(dto.getIdPenggunaListrik()).get();
            tgh.setIdPenggunaListrik(penggunaListrik);
        }
        tgh.setBulan(dto.getBulan());
        tgh.setKwh(dto.getKwh());
        tgh.setBiaya(dto.getBiaya());
        return tgh;
    }
    private TagihanDto convertEntityToDto(Tagihan entity) {
        TagihanDto dto = new TagihanDto();
        dto.setNoTagihan(entity.getNoTagihan());
        dto.setIdCatat(entity.getIdCatat().getIdCatat());
        dto.setIdPenggunaListrik(entity.getIdPenggunaListrik().getIdPengguna());
        dto.setBulan(entity.getBulan());
        dto.setKwh(entity.getKwh());
        dto.setBiaya(entity.getBiaya());
        return dto;
    }
    private Tagihan convertToEntity(TagihanDto tagihanDto) {
        Tagihan tagihan = new Tagihan();
        if(catatMeterRepo.findById(tagihanDto.getIdCatat()).isPresent()){
            CatatMeter catatMeter =  catatMeterRepo.findById(tagihanDto.getIdCatat()).get();
            tagihan.setIdCatat(catatMeter);
        }
        return tagihan;
    }
    private TagihanDto convertToDto(Tagihan tagihan){
        TagihanDto dto = new TagihanDto();
        dto.setNoTagihan(tagihan.getNoTagihan());
        dto.setIdCatat(tagihan.getIdCatat().getIdCatat());
        dto.setIdPenggunaListrik(tagihan.getIdPenggunaListrik().getIdPengguna());
        dto.setBulan(tagihan.getIdCatat().getBulanini());
        dto.setKwh(tagihan.getIdCatat().getCttkwh());
        dto.setBiaya((long)(tagihan.getIdCatat().getCttkwh()*tagihan.getIdPenggunaListrik().getIdJenis().getTarif()));
        return dto;
    }
    private TagihanDto convertIniToDto(Tagihan tagihan){
        TagihanDto dto = new TagihanDto();
        dto.setNoTagihan(tagihan.getNoTagihan());
        dto.setIdCatat(tagihan.getIdCatat().getIdCatat());
        dto.setIdPenggunaListrik(tagihan.getIdPenggunaListrik().getIdPengguna());
        dto.setBulan(tagihan.getBulan());
        dto.setKwh(tagihan.getKwh());
        dto.setBiaya(tagihan.getBiaya());
        return dto;
    }
}
