package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.CatatMeterRepo;
import com.prodemy.pembayaran.listrik.Repository.JenisPelangganRepo;
import com.prodemy.pembayaran.listrik.Repository.Tagihanrepo;
import com.prodemy.pembayaran.listrik.Repository.DataPelRepo;
import com.prodemy.pembayaran.listrik.Service.TagihanService;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.TagihanDto;
import com.prodemy.pembayaran.listrik.model.entity.CatatMeter;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
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
    Logger logger = LoggerFactory.getLogger(Tagihancontroller.class);

    private final Tagihanrepo tagihanrepo;
    private final DataPelRepo datapelrepo;
    private final JenisPelangganRepo jenisPelangganRepo;
    private final CatatMeterRepo catatMeterRepo;
    @Autowired
    private TagihanService tagihanService;

    public Tagihancontroller(Tagihanrepo tagihanrepo, DataPelRepo datapelrepo,
                             JenisPelangganRepo jenisPelangganRepo, CatatMeterRepo catatMeterRepo) {
        this.tagihanrepo = tagihanrepo;
        this.datapelrepo = datapelrepo;
        this.jenisPelangganRepo = jenisPelangganRepo;
        this.catatMeterRepo = catatMeterRepo;
    }

    @PostMapping("/savedata")
    public TagihanDto[] addtagihan(@RequestBody TagihanDto[] listbanyak) {
        for(TagihanDto m : listbanyak){
            tagihanrepo.save(convertDtoToEntity(m));
        }
        return listbanyak;
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
//        tgh.setMetodePembayaran(dto.getMetodePembayaran());
//        tgh.setStatus(dto.getStatus());

        return tgh;
    }

    @GetMapping("/cekcurrent/{idPenggunaListrik}")
    public DefaultResponse<TagihanDto> getByIdPenggunaListrik(@PathVariable Long idPenggunaListrik) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        String date = sdf.format(new Date());
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<Tagihan> optional = tagihanrepo.findAllByIdPenggunaListrikIdPengguna(idPenggunaListrik);
        if(optional.isPresent() && optional.get().getBulan() == date) {
            response.setPesan("Tagihan listrik Anda untuk bulan ini adalah");
            response.setData(convertEntityToDto(optional.get()));
        } else {
            response.setPesan("Tagihan listrik Anda untuk bulan ini belum ada");
        }
        return response;
    }

//    @GetMapping("/cek/{idPenggunaListrik}/{bulan}")
//    public DefaultResponse<TagihanDto> getByIdPenggunaListrikAndBulan(@PathVariable Long idPenggunaListrik, @PathVariable String bulan) {
//        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
//        Optional<Tagihan> optional = tagihanrepo.findByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, bulan);
//        if(optional.isPresent() && optional.get().getMetodePembayaran()!= null) {
//            response.setPesan("Tagihan bulan ini sudah terbayar");
//            response.setData(convertEntityToDto(optional.get()));
//        } else if(optional.isPresent() && optional.get().getMetodePembayaran()== null) {
//            response.setPesan("Tagihan bulan ini belum dibayar, silakan lanjutkan pembayaran");
//            response.setData(convertEntityToDto(optional.get()));
//        }else {
//            response.setPesan("Tagihan belum ada");
//        }
//        return response;
//    }
    private TagihanDto convertEntityToDto(Tagihan entity) {
        TagihanDto dto = new TagihanDto();
        dto.setNoTagihan(entity.getNoTagihan());
        dto.setIdCatat(entity.getIdCatat().getIdCatat());
        dto.setIdPenggunaListrik(entity.getIdPenggunaListrik().getIdPengguna());
        dto.setBulan(entity.getBulan());
        dto.setKwh(entity.getKwh());
        dto.setBiaya(entity.getBiaya());
//        dto.setMetodePembayaran(entity.getMetodePembayaran());
//        dto.setStatus(entity.getStatus());

        return dto;
    }
    @PostMapping("/savetrx")
    public TagihanDto insertTrx(@RequestBody TagihanDto tagihanDto) {
        Tagihan tagihan = convertToEntity(tagihanDto);
        tagihanService.insertData(tagihan);
        return convertToDto(tagihan);
    }
    @PostMapping("/savenotrx")
    public TagihanDto insertNoTrx(@RequestBody TagihanDto tagihanDto) {
        Tagihan tagihan = convertToEntity(tagihanDto);
        Tagihan entity = tagihanrepo.save(tagihan);
        entity.setIdPenggunaListrik(tagihan.getIdCatat().getIdPenggunaListrik());
        entity.setBulan(tagihan.getIdCatat().getBulanini());
        entity.setKwh(tagihan.getIdCatat().getCttkwh());
        entity.setBiaya(tagihan.getIdCatat().getCttkwh()*tagihan.getIdPenggunaListrik().getIdJenis().getTarif());
        tagihanrepo.save(entity);
        return convertToDto(tagihan);
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
        dto.setBiaya(tagihan.getIdCatat().getCttkwh()*tagihan.getIdPenggunaListrik().getIdJenis().getTarif());
        return dto;
    }

//    @PutMapping("/pembayaran")
//    public TagihanDto up(@RequestBody TagihanDto tagihanDto) {
//        Tagihan tagihan = convertIniToEntity(tagihanDto);
//        Tagihan entity = tagihanrepo.save(tagihan);
//        entity.setStatus("Lunas");
//        tagihanrepo.save(entity);
//        return convertIniToDto(entity);
//    }
//
//    @PutMapping("/tespembayaran")
//    public DefaultResponse<TagihanDto> update(@RequestBody TagihanDto tagihanDto) {
//        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
//        Tagihan tagihan = convertIniToEntity(tagihanDto);
//        Tagihan entity = tagihanrepo.save(tagihan);
//        if(entity.getMetodePembayaran().isEmpty()) {
//            response.setPesan("Pembayaran gagal, silakan coba lagi");
//        } else {
//            response.setPesan("Pembayaran berhasil");
//            entity.setStatus("Lunas");
//            tagihanrepo.save(entity);
//            response.setData(convertIniToDto(entity));
//        }
//        return response;
//    }

//    private Tagihan convertIniToEntity(TagihanDto tagihanDto) {
//        Tagihan tagihan = new Tagihan();
//        tagihan.setNoTagihan(tagihanDto.getNoTagihan());
//        if(catatMeterRepo.findById(tagihanDto.getIdCatat()).isPresent()){
//            CatatMeter catatMeter =  catatMeterRepo.findById(tagihanDto.getIdCatat()).get();
//            tagihan.setIdCatat(catatMeter);
//        }
//        if(datapelrepo.findById(tagihanDto.getIdPenggunaListrik()).isPresent()){
//            PenggunaListrik penggunaListrik =  datapelrepo.findById(tagihanDto.getIdPenggunaListrik()).get();
//            tagihan.setIdPenggunaListrik(penggunaListrik);
//        }
//        tagihan.setBulan(tagihanDto.getBulan());
//        tagihan.setKwh(tagihanDto.getKwh());
//        tagihan.setBiaya(tagihanDto.getBiaya());
//        tagihan.setMetodePembayaran(tagihanDto.getMetodePembayaran());
//
//        return tagihan;
//    }

    private TagihanDto convertIniToDto(Tagihan tagihan){
        TagihanDto dto = new TagihanDto();
        dto.setNoTagihan(tagihan.getNoTagihan());
        dto.setIdCatat(tagihan.getIdCatat().getIdCatat());
        dto.setIdPenggunaListrik(tagihan.getIdPenggunaListrik().getIdPengguna());
        dto.setBulan(tagihan.getBulan());
        dto.setKwh(tagihan.getKwh());
        dto.setBiaya(tagihan.getBiaya());
//        dto.setMetodePembayaran(tagihan.getMetodePembayaran());
//        dto.setStatus(tagihan.getStatus());
        return dto;
    }

    @GetMapping("/tghn/{idPenggunaListrik}/{bulan}")
    public List<TagihanDto> getTagihan(@PathVariable Long idPenggunaListrik, @PathVariable String bulan){
        List<Tagihan> tghn = tagihanrepo.findAllByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, bulan);
        List<TagihanDto> tdto = tghn.stream().map(this::convertIniToDto).collect(Collectors.toList());
        return tdto;
    }

    @GetMapping("/tghn/{idPenggunaListrik}")
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

}
