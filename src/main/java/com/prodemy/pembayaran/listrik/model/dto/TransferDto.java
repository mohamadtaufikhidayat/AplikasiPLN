package com.prodemy.pembayaran.listrik.model.dto;

import com.prodemy.pembayaran.listrik.model.entity.Account;

import java.util.Date;

public class TransferDto {
    private Long kodetransfer;
    private Long acc;
    private Long tujuan;
    private Long besar;
    private Date waktu;

    public Long getKodetransfer() {
        return kodetransfer;
    }

    public void setKodetransfer(Long kodetransfer) {
        this.kodetransfer = kodetransfer;
    }

    public Long getAcc() {
        return acc;
    }

    public void setAcc(Long acc) {
        this.acc = acc;
    }

    public Long getTujuan() {
        return tujuan;
    }

    public void setTujuan(Long tujuan) {
        this.tujuan = tujuan;
    }

    public Long getBesar() {
        return besar;
    }

    public void setBesar(Long besar) {
        this.besar = besar;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

}
