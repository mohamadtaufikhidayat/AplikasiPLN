package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.LoginRepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.LoginDto;
import com.prodemy.pembayaran.listrik.model.dto.PenggunaListrikDto;
import com.prodemy.pembayaran.listrik.model.dto.UserDto;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.User;
import com.prodemy.pembayaran.listrik.model.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home")
public class LoginController {

    @Autowired
    Userrepo repo;

    @Autowired
    LoginRepo logrep;

    @PostMapping("/login")
    public DefaultResponse login(@RequestBody UserDto dto) {
        Login log = convertToEntity(dto);
        DefaultResponse<Login> respon = new DefaultResponse<>();
        Optional<User> option = repo.findByEmail(dto.getEmail());
        if (option.isEmpty()) {
            respon.setPesan("Email Tidak Ada");
        } else {
            Optional<User> password = repo.findByPassword(dto.getEmail());
            if (String.valueOf(password).equals(String.format("Optional[%s]", dto.getPassword()))) {
                Optional<User> role = repo.findByPegawai(dto.getEmail());
                if (role.isPresent()) {
                    respon.setPesan("Login Berhasil,ADMIN");
                    logrep.save(convertToEntity(dto));
                    dto.setId(log.getId());
                    respon.setData(convertToEntity(dto));
                } else {
                    respon.setPesan("Login Berhasil,USER");
                    logrep.save(convertToEntity(dto));
                    respon.setData(convertToEntity(dto));
                }
            } else {
                respon.setPesan("Password Salah");
            }
        }
        return respon;
    }
    @GetMapping("/history/{email}")
    public List<LoginDto> get(@PathVariable String email) {
        List<Login> log = logrep.findByEmail(email);
        List<LoginDto> kotaDtoList = log.stream().map(this::convertToDto)
                .collect(Collectors.toList());
        return kotaDtoList;
    }

    private Login convertToEntity(UserDto dto) {
        Login log = new Login();
        log.setRole(String.valueOf(dto.getNo_pegawai()));
        log.setPassword(dto.getPassword());
        log.setId(dto.getId());
        User user = repo.findByEmail(dto.getEmail()).get();
        log.setUser(user);
        Optional<User> option = repo.findByPegawai(dto.getEmail());
        if (option.isPresent()) {
            log.setRole("Admin");
        } else {
            log.setRole("User");
        }
        return log;
    }

    private LoginDto convertToDto(Login log) {
        LoginDto dto = new LoginDto();
        dto.setEmail(log.getUser().getEmail());
        dto.setPassword(log.getPassword());
        dto.setRole(log.getRole());
        dto.setWaktu(String.valueOf(log.getCreateDate()));
        return dto;
    }
}