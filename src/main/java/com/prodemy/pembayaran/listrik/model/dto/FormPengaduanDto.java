package com.prodemy.pembayaran.listrik.model.dto;

import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import com.prodemy.pembayaran.listrik.model.entity.TopikPengaduan;

public class FormPengaduanDto {
    private Long IdpenggunaListrik;
    private Long noPengaduan;
    private String alamat;
    private String deskripsi;
    private String status;
    private String kodeTopik;

    public String getKodeTopik() {
        return kodeTopik;
    }

    public void setKodeTopik(String kodeTopik) {
        this.kodeTopik = kodeTopik;
    }

    public Long getNoPengaduan() {
        return noPengaduan;
    }

    public void setNoPengaduan(Long noPengaduan) {
        this.noPengaduan = noPengaduan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Long getIdpenggunaListrik() {
        return IdpenggunaListrik;
    }

    public void setIdpenggunaListrik(Long idpenggunaListrik) {
        IdpenggunaListrik = idpenggunaListrik;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        this.status = status;
        return status;
    }

}
