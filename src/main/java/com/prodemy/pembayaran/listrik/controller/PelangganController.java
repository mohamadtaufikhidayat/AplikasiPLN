package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.DataPelRepo;
import com.prodemy.pembayaran.listrik.Repository.Pelangganrepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.model.dto.PelangganDto;
import com.prodemy.pembayaran.listrik.model.entity.Pelanggan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pelanggan")
public class PelangganController {
    final private Pelangganrepo pelangganrepo;
    final private Userrepo userrepo;
    final private DataPelRepo penggunaListrikrepo;

    public PelangganController(Pelangganrepo pelangganrepo, Userrepo userrepo, DataPelRepo penggunaListrikrepo) {
        this.pelangganrepo = pelangganrepo;
        this.userrepo = userrepo;
        this.penggunaListrikrepo = penggunaListrikrepo;
    }
    @PostMapping("/save")
    public PelangganDto PelangganDto(@RequestBody PelangganDto pelangganDto){
        Pelanggan pelanggan=conversiDtoToEntity(pelangganDto);
        pelangganrepo.save(pelanggan);
        return conversiEntityToDto(pelanggan);
    }
    @PostMapping("/addbanyak")
    public PelangganDto[] addBanyakPelanggan(@RequestBody PelangganDto[] listbanyak){
        for(PelangganDto m : listbanyak){
            Pelanggan pelangganbanyak= conversiDtoToEntity(m);
            pelangganrepo.save(pelangganbanyak);
        }
        return listbanyak;
    }

    public Pelanggan conversiDtoToEntity(PelangganDto pelangganDto){
        Pelanggan pelanggan = new Pelanggan();

        pelanggan.setNoPelanggan(pelangganDto.getNoPelanggan());
        if(userrepo.findById(pelangganDto.getIdUser()).isPresent()){
            pelanggan.setIdUser(userrepo.findById(pelangganDto.getIdUser()).get());
        }
        if(penggunaListrikrepo.findById(pelangganDto.getIdPenggunaListrik()).isPresent()){
            pelanggan.setIdPenggunaListrik(penggunaListrikrepo.findById(pelangganDto.getIdPenggunaListrik()).get());
        }
        return pelanggan;
    }
    public PelangganDto conversiEntityToDto(Pelanggan pelanggan){
        PelangganDto pelangganDto = new PelangganDto();
        pelangganDto.setNoPelanggan(pelanggan.getNoPelanggan());
        pelangganDto.setIdUser(pelanggan.getIdUser().getNoInduk());
        pelangganDto.setIdPenggunaListrik(pelanggan.getIdPenggunaListrik().getIdPengguna());
        pelangganDto.setNamaPengguna(pelanggan.getIdPenggunaListrik().getNamaPengguna());
        return pelangganDto;
    }
}