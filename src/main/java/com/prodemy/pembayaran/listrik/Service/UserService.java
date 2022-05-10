package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.Repository.AdminRepo;
import com.prodemy.pembayaran.listrik.Repository.Userrepo;
import com.prodemy.pembayaran.listrik.model.dto.UserDto;
import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private Userrepo repo;

    @Autowired
    private AdminRepo adm;

    public UserDto register(UserDto dto) {
        User data = convertDTOtoEntity(dto);
        repo.save(data);//convert lagi
        dto.setId(data.getNoInduk());
        return (dto);
    }

    private UserDto convertToDto(User kota) {
        UserDto kotaDto = new UserDto();
        kotaDto.setEmail(kota.getEmail());
        kotaDto.setPassword(kota.getPassword());
        return kotaDto;
    }

    public User convertDTOtoEntity(UserDto dto) {
        User data = new User();
        data.setNoInduk(dto.getId());
        data.setEmail(dto.getEmail());
        data.setPassword(dto.getPassword());
        Optional<Admin> role = adm.findByNo_aplikasi(dto.getNo_pegawai());
        if (role.isPresent()) {
            data.setData(dto.getNo_pegawai());
        }else{
                data.setData(null);
            }
        return data;
        }

    }
