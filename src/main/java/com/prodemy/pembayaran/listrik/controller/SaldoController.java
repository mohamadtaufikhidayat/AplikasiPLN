package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.AccountRepo;
import com.prodemy.pembayaran.listrik.Repository.Saldorepo;
import com.prodemy.pembayaran.listrik.Service.SaldoService;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.SaldoDto;
import com.prodemy.pembayaran.listrik.model.entity.Account;
import com.prodemy.pembayaran.listrik.model.entity.Saldo;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    Saldorepo repo;

    @Autowired
    AccountRepo accrepo;

    @Autowired
    SaldoService ser;
    @PostMapping("/daftar")
    public DefaultResponse daftarsaldo(@RequestBody SaldoDto dto){
        Saldo sal = ser.convertDtoEntitySaldo(dto);
        DefaultResponse<SaldoDto> respon = new DefaultResponse<>();
        Optional<Saldo> opsi = repo.findByno_telpi(dto.getNo_telp());
        if(opsi.isEmpty()){
            respon.setPesan("Registrasi Berhasil");
            repo.save(sal);
        }else{
            respon.setPesan("Registrasi Gagal Nomor Handphone Sudah Terdaftar");
            respon.setData(null);
        }
        return respon;
    }

    @PostMapping("/top-up")//Masih Harus Pake ID ACC
    public SaldoDto Trans(@RequestBody SaldoDto dto){
        Saldo data = ser.convertDtoEntitySaldoTrans(dto);
        Long saldo = repo.findBySaldoByPhone(dto.getNo_telp());
        data.setSaldo(saldo + data.getPemasukan());
        repo.save(data);
        data.setCreateDate(data.getCreateDate());
        return converttoDto(data);
        }
    @PostMapping("/transaksi")//Masih Harus Pake ID ACC
    public DefaultResponse Transaksi(@RequestBody SaldoDto dto){
        Saldo data = ser.convertDtoEntitySaldoTrans(dto);
        DefaultResponse<Saldo> opsi = new DefaultResponse<>();
        Long saldo = repo.findBySaldoByPhone(dto.getNo_telp());
        if(saldo >= dto.getPengeluaran()){
            dto.setSaldo(saldo - dto.getPengeluaran());
            repo.save(data);
            opsi.setData(data);
            opsi.setPesan("Transaksi Sebesar Berhasil ");
        }else{
            opsi.setPesan("Transaksi Gagal Saldo Kurang");
        }
        return opsi;
    }

    @GetMapping("/find-all")
    public List<SaldoDto> get() {
        List<Saldo> kotaList = repo.findAll();
        List<SaldoDto> kotaDtoList = kotaList.stream().map(this::converttoDto)
                .collect(Collectors.toList());
        return kotaDtoList;
    }
    public SaldoDto converttoDto(Saldo data){
        SaldoDto dto = new SaldoDto();
        dto.setSaldo(data.getSaldo());
        dto.setPemasukan(data.getPemasukan());
        dto.setPengeluaran(data.getPengeluaran());
        dto.setNo_telp(data.getAccount().getNotel());
        dto.setId(data.getId_acc());
        dto.setWaktu(data.getCreateDate());
        return dto;
    }
    public Saldo converttoEntity(SaldoDto dto){
        Saldo data = new Saldo();
        Long id = repo.findIdByPhone(dto.getNo_telp());
        data.setId_acc(id);
        data.setPengeluaran(dto.getPengeluaran());
        data.setSaldo(dto.getSaldo());
        data.setCreateDate(dto.getWaktu());
        if (accrepo.findById(dto.getNo_telp()).isPresent()) {
            Account user = accrepo.findById(dto.getNo_telp()).get();
            data.setAccount(user);
        }
        return data;
    }

}
