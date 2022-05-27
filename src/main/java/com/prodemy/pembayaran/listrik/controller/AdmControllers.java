package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.AdminRepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.Service.AdminService;
import com.prodemy.pembayaran.listrik.Service.UserService;
import com.prodemy.pembayaran.listrik.model.dto.AdminDto;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.SearchData;
import com.prodemy.pembayaran.listrik.model.dto.UserDto;
import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdmControllers {

    @Autowired
    AdminRepo repo;

    @Autowired
    Userrepo usrep;
    @Autowired
    AdminService ser;

    @Autowired
    UserService user;

    @PostMapping("/admin")
    public DefaultResponse register(@RequestBody AdminDto dto){
        DefaultResponse<AdminDto> data = new DefaultResponse<>();
        Optional<Admin> option = repo.findByNoID(dto.getId());
        if(option.isPresent()){
            data.setPesan("Register Gagal Anda sudah terdaftar ");

        }else{
            data.setData(dto);
            data.setPesan("Regitrasi Berhasil!!!");
            ser.register(dto);

        }
        return data;
    }
    @GetMapping("/find-all-user")
    public List<UserDto> get() {
        List<User> kotaList = usrep.findAll();
        List<UserDto> kotaDtoList = kotaList.stream().map(this::convertToDtoUser)
                .collect(Collectors.toList());
        return kotaDtoList;
    }
    @PostMapping ("/searchlike")
    public List<User> find(@RequestBody SearchData searchdata){
        List<User> daftar = usrep.findByNameLike("%"+searchdata.getSearchKey()+"%");
        return daftar;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody UserDto dto){
       usrep.deleteById(dto.getId());
    }

    private UserDto convertToDtoUser(User kota){
        UserDto kotaDto = new UserDto();
        kotaDto.setId(kota.getNoInduk());
        kotaDto.setEmail(kota.getEmail());
        kotaDto.setPassword(kota.getPassword());
        return kotaDto;
    }
}
