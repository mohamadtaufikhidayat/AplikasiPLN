package com.prodemy.pembayaran.listrik.model.dto;

import javax.persistence.Column;

public class PenggunaListrikDto {
    private Long idPengguna;
    private String namaPengguna;
    private String alamat;
//<<<<<<< HEAD
//    private String jenisPengguna;
//    private Long idJenis;
//
//=======
    private String provinsi;
    private String kota;
    private String kecamatan;
    private String kelurahan;
    private String jenisPengguna;
    private Long user_id;
//>>>>>>> 52b32ecdee5d332940005f66364861016f5621ec
    private Long daya;

    public Long getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Long idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisPengguna() {
        return jenisPengguna;
    }

    public void setJenisPengguna(String jenisPengguna) {
        this.jenisPengguna = jenisPengguna;
    }

    public Long getDaya() {
        return daya;
    }

    public void setDaya(Long daya) {
        this.daya = daya;
    }

//<<<<<<< HEAD
//    public Long getIdJenis() {
//        return idJenis;
//    }
//
//    public void setIdJenis(Long idJenis) {
//        this.idJenis = idJenis;
//=======
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
//>>>>>>> 52b32ecdee5d332940005f66364861016f5621ec
    }
}
