package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.PenggunaListrikrepo;
import com.prodemy.pembayaran.listrik.Repository.Tagihanrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.TagihanDto;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private final PenggunaListrikrepo penggunaListrikrepo;
    public Tagihancontroller(Tagihanrepo tagihanrepo, PenggunaListrikrepo penggunaListrikrepo) {
        this.tagihanrepo = tagihanrepo;
        this.penggunaListrikrepo = penggunaListrikrepo;
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
        if(penggunaListrikrepo.findById(dto.getIdPenggunaListrik()).isPresent()){
            PenggunaListrik penggunaListrik =  penggunaListrikrepo.findById(dto.getIdPenggunaListrik()).get();
            tgh.setIdPenggunaListrik(penggunaListrik);
        }
        tgh.setNoTagihan(dto.getNoTagihan());
        tgh.setBulan(dto.getBulan());
        tgh.setKwh(dto.getKwh());
        tgh.setBiaya(dto.getBiaya());
        tgh.setMetodePembayaran(dto.getMetodePembayaran());
        tgh.setStatus(dto.getStatus());

        return tgh;
    }

    @GetMapping("/cek/{idPenggunaListrik}/{bulan}")
    public DefaultResponse<TagihanDto> getByIdPenggunaListrikAndBulan(@PathVariable String idPenggunaListrik, @PathVariable String bulan) {
        DefaultResponse<TagihanDto> response = new DefaultResponse<>();
        Optional<Tagihan> optional = tagihanrepo.findByIdPenggunaListrikAndBulan(idPenggunaListrik, bulan);
        if(optional.isPresent()) {
            response.setPesan("Tagihan bulan ini sudah terbayar");
            response.setData(convertEntityToDto(optional.get()));
        } else {
            response.setPesan("Tagihan bulan ini belum terbayar, silakan melanjutkan pembayaran");
        }
        return response;
    }
    private TagihanDto convertEntityToDto(Tagihan entity) {
        TagihanDto dto = new TagihanDto();
        dto.setIdPenggunaListrik(entity.getIdPenggunaListrik().getIdPengguna());
        dto.setNoTagihan(entity.getNoTagihan());
        dto.setBulan(entity.getBulan());
        dto.setKwh(entity.getKwh());
        dto.setBiaya(entity.getBiaya());
        dto.setMetodePembayaran(entity.getMetodePembayaran());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    @GetMapping("/history/{idPenggunaListrik}")
    public List<TagihanDto> getHistoryByIdPenggunaListrik(@PathVariable String idPenggunaListrik){
        List<Tagihan> hist = tagihanrepo.findByIdPenggunaListrik(idPenggunaListrik);
        List<TagihanDto> histDto = hist.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        return histDto;
    }

    @GetMapping("/alldata")
    public List<TagihanDto> getListTagihan(){
        List<TagihanDto> list = new ArrayList<>();
//        logger.info(String.valueOf(Math.floor(Math.random() * 100) + 100));
//        logger.info(String.valueOf(penggunaListrikrepo.findJenisByIdPengguna("1234").getJenis()));
//        logger.info(String.valueOf(penggunaListrikrepo.findJenisByIdPengguna("1234").getDaya()));
        for(Tagihan m : tagihanrepo.findAll()) {
            list.add(convertEntityToDto(m));
        }
        return list;
    }

    public void hitungBiaya() {
        TagihanDto tagihanDto = new TagihanDto();
        tagihanDto.setKwh((long) (Math.floor(Math.random() * 100) + 100));
        if (penggunaListrikrepo.findJenisByIdPengguna(tagihanDto.getIdPenggunaListrik()).getJenis()=="RT1") {
            tagihanDto.setBiaya(tagihanDto.getKwh()*1352);
        }
        tagihanrepo.save(convertDtoToEntity(tagihanDto));
    }

    @GetMapping("/hitung")
    public List<TagihanDto> getbiaya() {
        List<TagihanDto> bi = new ArrayList<>();
        bi.add(hitungBiaya());
        return bi;
    }


}
