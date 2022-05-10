package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.Repository.AccountRepo;
import com.prodemy.pembayaran.listrik.Repository.Saldorepo;
import com.prodemy.pembayaran.listrik.Repository.Transaksirepo;
import com.prodemy.pembayaran.listrik.Repository.Transferrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.TransferDto;
import com.prodemy.pembayaran.listrik.model.entity.Account;
import com.prodemy.pembayaran.listrik.model.entity.Saldo;
import com.prodemy.pembayaran.listrik.model.entity.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TransferService {
    @Autowired
    AccountRepo akun;
    @Autowired
    Saldorepo repo;

    @Autowired
    Transferrepo trans;


    public DefaultResponse transfer(TransferDto dto) {
        Transfer data = convertDtotoEntity(dto);
        Saldo awalan = convertfromTransferDtoAwalan(dto);
        Saldo sasaran = convertfromTransferDtotoTujuan(dto);
        DefaultResponse<TransferDto> respon = new DefaultResponse<>();
        Optional<Account> awal = akun.findById(dto.getAcc());
        Optional<Account> tujuan = akun.findById(dto.getTujuan());
        if (awal.isPresent() && tujuan.isPresent()) {
            Long saldo = repo.findBySaldoByPhone(dto.getAcc());
            if (saldo >= dto.getBesar()) {
                trans.save(data);
                dto.setKodetransfer(data.getKodetransfer());
                respon.setPesan("Transaksi Berhasil");
                respon.setData(dto);
                repo.save(awalan);
                repo.save(sasaran);
            } else {
                respon.setPesan("Gagal Saldo Kurang!!!");
            }

        }else{
            respon.setPesan("Maaf nomor hp Tidak Ada Silahkan Cek Lagi");
        }
        return respon;
    }
    public Transfer convertDtotoEntity(TransferDto dto) {
        Transfer data = new Transfer();
        data.setBesar(dto.getBesar());
        data.setKodetransfer(dto.getKodetransfer());
        data.setTujuan(dto.getTujuan());
        if (akun.findById(dto.getAcc()).isPresent()) {
            Account acc = akun.findById(dto.getAcc()).get();
            data.setAcc(acc);
        }
        return data;
    }

    public TransferDto EntitytoDto(Transfer data) {
        TransferDto dto = new TransferDto();
        dto.setKodetransfer(data.getKodetransfer());
        dto.setAcc(data.getAcc().getNotel());
        dto.setBesar(data.getBesar());
        dto.setTujuan(data.getTujuan());

        return dto;
    }

    public Saldo convertfromTransferDtoAwalan(TransferDto dto) {
        Saldo data = new Saldo();
        Long saldo = repo.findBySaldoByPhone(dto.getAcc());
        Long id = repo.findIdByPhone(dto.getAcc());
        if (akun.findById(dto.getAcc()).isPresent()) {
            Account acc = akun.findById(dto.getAcc()).get();
            data.setAccount(acc);
            data.setSaldo(saldo - dto.getBesar());
            data.setId_acc(id);

        }
        return data;
    }

    public Saldo convertfromTransferDtotoTujuan(TransferDto dto) {
        Saldo data = new Saldo();
        Long saldo = repo.findBySaldoByPhone(dto.getTujuan());
        Long id = repo.findIdByPhone(dto.getTujuan());
        if (akun.findById(dto.getTujuan()).isPresent()) {
            Account acc = akun.findById(dto.getTujuan()).get();
            data.setAccount(acc);
            data.setSaldo(saldo + dto.getBesar());
            data.setId_acc(id);
        }
        return data;
    }
}
