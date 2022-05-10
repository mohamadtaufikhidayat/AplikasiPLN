package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.Transferrepo;
import com.prodemy.pembayaran.listrik.Service.TransferService;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.PenggunaListrikDto;
import com.prodemy.pembayaran.listrik.model.dto.TransaksiDto;
import com.prodemy.pembayaran.listrik.model.dto.TransferDto;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transfer")

public class Transfercontroller {
    @Autowired
    TransferService service;
    @Autowired
    Transferrepo repo;

    @PostMapping("/sesama")
    public DefaultResponse defaultResponse(@RequestBody TransferDto dto){
        DefaultResponse<TransferDto> respon = service.transfer(dto);
        return respon;
    }
    @GetMapping("/all")
    public List<TransferDto> get() {
        List<Transfer> kotaList = repo.findAll();
        List<TransferDto> kotaDtoList = kotaList.stream().map(this::EntitytoDto)
                .collect(Collectors.toList());
        return kotaDtoList;
    }
    @GetMapping("search/{no_telp}")
    public List<TransferDto> search(@PathVariable Long no_telp) {
        List<Transfer> cari = repo.findByNotel(no_telp);
        List<TransferDto> kecdto = cari.stream().map(this::EntitytoDto)
                .collect(Collectors.toList());
        return kecdto;
    }
    public TransferDto EntitytoDto(Transfer data){
        TransferDto dto = new TransferDto();
        dto.setKodetransfer(data.getKodetransfer());
        dto.setAcc(data.getAcc().getNotel());
        dto.setBesar(data.getBesar());
        dto.setTujuan(data.getTujuan());
        dto.setWaktu(data.getCreateDate());

        return dto;
    }
}
