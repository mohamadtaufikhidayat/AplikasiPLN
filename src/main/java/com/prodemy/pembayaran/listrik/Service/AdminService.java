package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.Repository.AdminRepo;
import com.prodemy.pembayaran.listrik.model.dto.AdminDto;
import com.prodemy.pembayaran.listrik.model.dto.UserDto;
import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService {
    @Autowired
    AdminRepo repo;

    public AdminDto register (AdminDto dto) {
        Admin data = convertDTOtoEntity(dto);
        repo.save(data);
        dto.setNo_aplikasi(data.getNo_aplikasi());
        return (dto);
    }
    public Admin convertDTOtoEntity(AdminDto dto) {
        Admin data = new Admin();
        data.setId(dto.getId());
        data.setNama(dto.getNama());
        data.setNo_aplikasi(dto.getNo_aplikasi());
        return data;
    }
}
