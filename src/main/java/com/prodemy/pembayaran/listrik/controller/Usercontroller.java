package com.prodemy.pembayaran.listrik.controller;


import com.prodemy.pembayaran.listrik.Repository.AdminRepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.Service.UserService;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.UserDto;
import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;



@RestController
@RequestMapping("/home")
public class Usercontroller {

   @Autowired
   UserService service;

   @Autowired
   Userrepo repo;

   @Autowired
   AdminRepo adm;

    //http://localhost:8383/home/register
    @PostMapping("/register")
    public DefaultResponse regis(@RequestBody UserDto dto) {
        DefaultResponse<UserDto> respon = new DefaultResponse<>();
        Optional<User> option = repo.findByEmail(dto.getEmail());
        if (option.isEmpty()) {
            Optional<Admin> role = adm.findByNo_aplikasi(dto.getNo_pegawai());
            if (role.isPresent()) {
                respon.setPesan("Register Berhasil Anda Admin");
                respon.setData(dto);
                service.register(dto);
                }else {
                respon.setData(dto);
                respon.setPesan("No Aplikasi Tidak Ditemukan Anda User");
                service.register(dto);}
            }else{
            respon.setPesan("Register Gagal Email Sudah Terdaftar");}
            return respon;
        }
}
