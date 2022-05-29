package com.prodemy.pembayaran.listrik.model.dto;

import java.util.Date;

public class TransaksiDto {
    private Long noTransaksi;
    private Long noTagihan;
    private Long idNoPelanggan;
    private Long idUser;
    private Long idPenggunaListrik;
    private String namaPenggunaListrik;
    private String daya;
    private String bulan;
    private Long kwh;
    private String metodePembayaran;
    private String konfirmasiMetodePembayaran;
    private Long noRekPLN;
    private Long konfirmasirekeningpln;
    private Long biaya;
    private Long konfirmasiBiaya;
    private String statusTransaksi;

    public Long getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(Long noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Long getNoTagihan() {
        return noTagihan;
    }

    public void setNoTagihan(Long noTagihan) {
        this.noTagihan = noTagihan;
    }

    public Long getIdNoPelanggan() {
        return idNoPelanggan;
    }

    public void setIdNoPelanggan(Long idNoPelanggan) {
        this.idNoPelanggan = idNoPelanggan;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdPenggunaListrik() {
        return idPenggunaListrik;
    }

    public void setIdPenggunaListrik(Long idPenggunaListrik) {
        this.idPenggunaListrik = idPenggunaListrik;
    }

    public String getNamaPenggunaListrik() {
        return namaPenggunaListrik;
    }

    public void setNamaPenggunaListrik(String namaPenggunaListrik) {
        this.namaPenggunaListrik = namaPenggunaListrik;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Long getKwh() {
        return kwh;
    }

    public void setKwh(Long kwh) {
        this.kwh = kwh;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getKonfirmasiMetodePembayaran() {
        return konfirmasiMetodePembayaran;
    }

    public void setKonfirmasiMetodePembayaran(String konfirmasiMetodePembayaran) {
        this.konfirmasiMetodePembayaran = konfirmasiMetodePembayaran;
    }

    public Long getNoRekPLN() {
        return noRekPLN;
    }

    public void setNoRekPLN(Long noRekPLN) {
        this.noRekPLN = noRekPLN;
    }

    public Long getKonfirmasirekeningpln() {
        return konfirmasirekeningpln;
    }

    public void setKonfirmasirekeningpln(Long konfirmasirekeningpln) {
        this.konfirmasirekeningpln = konfirmasirekeningpln;
    }

    public Long getBiaya() {
        return biaya;
    }

    public void setBiaya(Long biaya) {
        this.biaya = biaya;
    }

    public Long getKonfirmasiBiaya() {
        return konfirmasiBiaya;
    }

    public void setKonfirmasiBiaya(Long konfirmasiBiaya) {
        this.konfirmasiBiaya = konfirmasiBiaya;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
}
