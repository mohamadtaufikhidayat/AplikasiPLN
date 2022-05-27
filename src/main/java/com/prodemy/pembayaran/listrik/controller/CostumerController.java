package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.Costumerrepo;
import com.prodemy.pembayaran.listrik.Repository.PenggunaListrikrepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.CosutumerDto;
import com.prodemy.pembayaran.listrik.model.entity.Cosutumer;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daftar")
public class CostumerController {

    final
    Costumerrepo repo;

    final
    Userrepo userrepo;

    final
    PenggunaListrikrepo penggunarepo;

    public CostumerController(Costumerrepo repo, Userrepo userrepo, PenggunaListrikrepo penggunarepo) {
        this.repo = repo;
        this.userrepo = userrepo;
        this.penggunarepo = penggunarepo;
    }

    @PostMapping("/registrasi")
    public DefaultResponse register(@RequestBody CosutumerDto dto) {
        DefaultResponse<Cosutumer> respon = new DefaultResponse<>();
        Cosutumer data = convertDTotoEntity(dto);
        List<Cosutumer> daftar = repo.findByUser(dto.getNo_user());
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
    public Cosutumer convertDTotoEntity(CosutumerDto dto) {
        Cosutumer data = new Cosutumer();
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
//    public CosutumerDto convertEntitytoDTo (Cosutumer data){
//        CosutumerDto dto = new CosutumerDto();
//        dto.setNo_meteran(data.getPel().getIdPengguna());
//        dto.setNo_user(data.getNo_langganan());
//        dto.setNo_user(data.getUser().getNoInduk());
//
//        return dto;
//    }
}
