package com.prodemy.pembayaran.listrik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "akun")
public class Account {

    @Id
    @Column(name ="no_telp",unique = true)
    private Long notel;

    @Column(name ="nik",unique = true)
    private Long nomor;

    @Column(name="name")
    private String nama;

    @OneToOne
    @JoinColumn(name="id_app",unique = true)
    private User user;

    public Long getNotel() {
        return notel;
    }

    public void setNotel(Long notel) {
        this.notel = notel;
    }

    public Long getNomor() {
        return nomor;
    }

    public void setNomor(Long nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
