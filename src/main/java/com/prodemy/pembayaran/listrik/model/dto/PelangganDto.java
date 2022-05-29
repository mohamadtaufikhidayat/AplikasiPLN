package com.prodemy.pembayaran.listrik.model.dto;

public class PelangganDto {
    private Long noPelanggan;
    private Long idUser;
    private Long idPenggunaListrik;
    private String namaPengguna;

    public Long getNoPelanggan() {
        return noPelanggan;
    }

    public void setNoPelanggan(Long noPelanggan) {
        this.noPelanggan = noPelanggan;
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

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }
}