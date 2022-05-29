package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.CatatMeterRepo;
import com.prodemy.pembayaran.listrik.Repository.DataPelRepo;
import com.prodemy.pembayaran.listrik.model.dto.CatatMeterDto;
import com.prodemy.pembayaran.listrik.model.entity.CatatMeter;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catat")
public class CatatMeterController {
    private final CatatMeterRepo catatMeterRepo;
    private final DataPelRepo dataPelRepo;
    public CatatMeterController(CatatMeterRepo catatMeterRepo, DataPelRepo dataPelRepo){
        this.catatMeterRepo = catatMeterRepo;
        this.dataPelRepo = dataPelRepo;
    }
    @PostMapping("/kwhcurrent")
    public CatatMeterDto catatKwh (@RequestBody CatatMeterDto ct) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        String date = sdf.format(new Date());
        CatatMeter catat = convertToEntity(ct);
        catat.setBulanini(date);
        catatMeterRepo.save(catat);
        return convertToDto(catat);
    }
    @GetMapping("/cekKwhcurrent/{idPenggunaListrik}")
    public List<CatatMeterDto> getByIdPenggunaListrik(@PathVariable Long idPenggunaListrik) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        String date = sdf.format(new Date());
        List<CatatMeter> list = catatMeterRepo.findByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, date);
        List<CatatMeterDto> dtoList = list.stream().map(this::convertToDto).collect(Collectors.toList());
        return dtoList;
    }
    @PostMapping("/kwh")
    public CatatMeterDto catatmeter (@RequestBody CatatMeterDto ct){
        CatatMeter catat = convertIniToEntity(ct);
        catatMeterRepo.save(catat);
        return convertToDto(catat);
    }
    @GetMapping("/cekKwh/{idPenggunaListrik}/{bulan}")
    public List<CatatMeterDto> getIdPenggunaListrik(@PathVariable Long idPenggunaListrik, String bulan) {
        List<CatatMeter> list = catatMeterRepo.findByIdPenggunaListrikIdPenggunaAndBulan(idPenggunaListrik, bulan);
        List<CatatMeterDto> dtoList = list.stream().map(this::convertToDto).collect(Collectors.toList());
        return dtoList;
    }
    private CatatMeter convertToEntity(CatatMeterDto ct) {
        CatatMeter cttkwh = new CatatMeter();
        if(dataPelRepo.findById(ct.getIdPenggunaListrik()).isPresent()){
            PenggunaListrik penggunaListrik = dataPelRepo.findById(ct.getIdPenggunaListrik()).get();
            cttkwh.setIdPenggunaListrik(penggunaListrik);
        }
        cttkwh.setCttkwh(ct.getCttkwh());
        return cttkwh;
    }
    private CatatMeter convertIniToEntity(CatatMeterDto ct) {
        CatatMeter cttkwh = new CatatMeter();
        if(dataPelRepo.findById(ct.getIdPenggunaListrik()).isPresent()){
            PenggunaListrik penggunaListrik = dataPelRepo.findById(ct.getIdPenggunaListrik()).get();
            cttkwh.setIdPenggunaListrik(penggunaListrik);
        }
        cttkwh.setCttkwh(ct.getCttkwh());
        cttkwh.setBulanini(ct.getBulanini());
        return cttkwh;
    }
    private CatatMeterDto convertToDto(CatatMeter catat) {
        CatatMeterDto dto = new CatatMeterDto();
        dto.setIdCatat(catat.getIdCatat());
        dto.setIdPenggunaListrik(catat.getIdPenggunaListrik().getIdPengguna());
        dto.setBulanini(catat.getBulanini());
        dto.setCttkwh(catat.getCttkwh());
        return dto;
    }
}
