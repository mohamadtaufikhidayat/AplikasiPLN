package com.prodemy.pembayaran.listrik.model.dto;

public class FormPengaduanDto {
    private Long noPengaduan;
    private String alamat;
    private String deskripsi;
    private byte foto;
    private Long IdpenggunaListrik;
//    private Long codeTopik;

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

    public byte getFoto() {
        return foto;
    }

    public void setFoto(byte foto) {
        this.foto = foto;
    }

    public Long getIdpenggunaListrik() {
        return IdpenggunaListrik;
    }

    public void setIdpenggunaListrik(Long idpenggunaListrik) {
        IdpenggunaListrik = idpenggunaListrik;
    }

//    public Long getCodeTopik() {
//        return codeTopik;
//    }
//
//    public void setCodeTopik(Long codeTopik) {
//        this.codeTopik = codeTopik;
//    }
}
