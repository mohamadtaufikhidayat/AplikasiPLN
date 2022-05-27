package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.Pelangganrepo;
import com.prodemy.pembayaran.listrik.Repository.PenggunaListrikrepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.PelangganDto;
import com.prodemy.pembayaran.listrik.model.dto.PenggunaListrikDto;
import com.prodemy.pembayaran.listrik.model.entity.Pelanggan;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/daftar")
public class PelangganController {

    @Autowired
    Pelangganrepo repo;

    @Autowired
    Userrepo userrepo;

    @Autowired
    PenggunaListrikrepo penggunarepo;

    @PostMapping("/registrasi")
    public DefaultResponse register(@RequestBody PelangganDto dto) {
        DefaultResponse<Pelanggan> respon = new DefaultResponse<>();
        Pelanggan data = convertDTotoEntity(dto);
        List<Pelanggan> daftar = repo.findByUser(dto.getNo_user());
        if (daftar.size() <= 4) {
            repo.save(data);
            dto.setNo_langganan(data.getNo_langganan());
            respon.setPesan("Registrasi Berhasil!!!");
            respon.setData(data);
        }else{
            respon.setPesan("Registrasi Gagal Anda Sudah Mempunyai Lebih dari 4 Meteran!!!");
        }
        return respon;
    }
    public Pelanggan convertDTotoEntity(PelangganDto dto) {
        Pelanggan data = new Pelanggan();
        data.setNo_langganan(dto.getNo_langganan());

        if (userrepo.findById(dto.getNo_user()).isPresent()) {
            User user = userrepo.findById(dto.getNo_user()).get();
            data.setUser(user);
        }
        if (penggunarepo.findById(dto.getNo_meteran()).isPresent()) {
            PenggunaListrik pengguna = penggunarepo.findById(dto.getNo_meteran()).get();
            data.setPel(pengguna);
        }
        return data;
    }
    public PelangganDto convertEntitytoDTo (Pelanggan data){
        PelangganDto dto = new PelangganDto();
        dto.setNo_meteran(data.getPel().getIdPengguna());
        dto.setNo_user(data.getNo_langganan());
        dto.setNo_user(data.getUser().getNoInduk());

        return dto;
    }
}
