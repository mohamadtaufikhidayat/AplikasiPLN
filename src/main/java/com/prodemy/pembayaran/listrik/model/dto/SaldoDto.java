package com.prodemy.pembayaran.listrik.model.dto;

import java.util.Date;

public class SaldoDto {
    private Long saldo;
    private Long pemasukan;
    private Long pengeluaran;
    private Date waktu;
    private Long no_telp;
    private Long id;

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Long getPemasukan() {
        return pemasukan;
    }

    public void setPemasukan(Long pemasukan) {
        this.pemasukan = pemasukan;
    }

    public Long getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(Long pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }

    public Long getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(Long no_telp) {
        this.no_telp = no_telp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
