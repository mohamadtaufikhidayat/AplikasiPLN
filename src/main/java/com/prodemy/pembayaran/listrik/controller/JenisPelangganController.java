package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.JenisPelangganRepo;
import com.prodemy.pembayaran.listrik.Repository.UpdateJenisPelangganRepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.JenisPelangganDto;
import com.prodemy.pembayaran.listrik.model.dto.UpdateJenisPelangganDto;
import com.prodemy.pembayaran.listrik.model.entity.JenisPelanggan;
import com.prodemy.pembayaran.listrik.model.entity.UpdateJenisPelanggan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jenis")
public class JenisPelangganController {
    private final JenisPelangganRepo jenisPelangganRepo;

    private final UpdateJenisPelangganRepo updateJenisPelangganRepo;

    public JenisPelangganController (JenisPelangganRepo jenisPelangganRepo,
                                     UpdateJenisPelangganRepo  updateJenisPelangganRepo) {
        this.jenisPelangganRepo = jenisPelangganRepo;
        this.updateJenisPelangganRepo = updateJenisPelangganRepo;
    }

    @PostMapping("/tarif")
    public JenisPelangganDto[] addtarif (@RequestBody JenisPelangganDto[] listtarif){
        for(JenisPelangganDto t : listtarif) {
            JenisPelanggan jns = convertToEntity(t);
            jenisPelangganRepo.save(jns);
            t.setIdJenis(jns.getIdJenis());

            UpdateJenisPelanggan up = convertIniToEntity(t);
            up.setIdJenisUp(jns.getIdJenis());
            up.setTglUp(new Date());
            updateJenisPelangganRepo.save(up);
        }
        return listtarif;
    }

    @PostMapping("/addtarif")
    public JenisPelangganDto add (@RequestBody JenisPelangganDto dto){
        JenisPelanggan jns = convertToEntity(dto);
        jenisPelangganRepo.save(jns);
        dto.setIdJenis(jns.getIdJenis());

        UpdateJenisPelanggan up = convertIniToEntity(dto);
        up.setIdJenisUp(jns.getIdJenis());
        up.setTglUp(new Date());
        updateJenisPelangganRepo.save(up);

        return dto;
    }

    @GetMapping("/listjenis")
    public List<JenisPelangganDto> getListAll(){
        List<JenisPelangganDto> list = new ArrayList<>();
        for(JenisPelanggan m : jenisPelangganRepo.findAll()) {
            list.add(convertToDto(m));
        }
        return list;
    }
    @PutMapping("/updatetarif")
    public DefaultResponse<JenisPelangganDto> update(@RequestBody JenisPelangganDto dto){
        DefaultResponse<JenisPelangganDto>response=new DefaultResponse<>();
        Optional<JenisPelanggan> optional = jenisPelangganRepo.findById(dto.getIdJenis());
        if(optional.isPresent()){
            response.setPesan("Update jenis pengguna listrik berhasil");
            response.setData(dto);
            jenisPelangganRepo.save(convertToEntity1(dto));

            UpdateJenisPelanggan up = convertIniToEntity(dto);
            up.setIdJenisUp(dto.getIdJenis());
            up.setTglUp(new Date());
            updateJenisPelangganRepo.save(up);
        }else{
            response.setPesan("Gagal update! Jenis pengguna listrik belum terdaftar");
        }
        return response;
    }
    @GetMapping("/listupdatejenis")
    public List<UpdateJenisPelangganDto> getAll(){
        List<UpdateJenisPelangganDto> list = new ArrayList<>();
        for(UpdateJenisPelanggan m : updateJenisPelangganRepo.findAll()) {
            list.add(convertIniToDto(m));
        }
        return list;
    }

    private UpdateJenisPelangganDto convertIniToDto(UpdateJenisPelanggan up) {
        UpdateJenisPelangganDto dto = new UpdateJenisPelangganDto();
        dto.setTglUp(up.getTglUp());
        dto.setIdJenisUp(up.getIdJenisUp());
        dto.setJenisUp(up.getJenisUp());
        dto.setDayaUp(up.getDayaUp());
        dto.setTarifUp(up.getTarifUp());
        return dto;
    }

    private UpdateJenisPelanggan convertIniToEntity(JenisPelangganDto dto) {
        UpdateJenisPelanggan up = new UpdateJenisPelanggan();
        up.setJenisUp(dto.getJenis());
        up.setDayaUp(dto.getDaya());
        up.setTarifUp(dto.getTarif());
        return up;
    }
    private JenisPelanggan convertToEntity1(JenisPelangganDto dto) {
        JenisPelanggan jns = new JenisPelanggan();
        jns.setIdJenis(dto.getIdJenis());
        jns.setJenis(dto.getJenis());
        jns.setDaya(dto.getDaya());
        jns.setTarif(dto.getTarif());
        return jns;
    }
    private JenisPelanggan convertToEntity(JenisPelangganDto dto) {
        JenisPelanggan jns = new JenisPelanggan();
        jns.setJenis(dto.getJenis());
        jns.setDaya(dto.getDaya());
        jns.setTarif(dto.getTarif());
        return jns;
    }

    private JenisPelangganDto convertToDto(JenisPelanggan jns) {
        JenisPelangganDto dto = new JenisPelangganDto();
        dto.setIdJenis(jns.getIdJenis());
        dto.setJenis(jns.getJenis());
        dto.setDaya(jns.getDaya());
        dto.setTarif(jns.getTarif());
        return dto;
    }

}
