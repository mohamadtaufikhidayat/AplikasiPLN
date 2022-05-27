package com.prodemy.pembayaran.listrik.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_admin")
public class Admin {

    @Column(unique = true,nullable = false)
    private Long id;

    private String nama;
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "admin_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "310890"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "no_aplikasi",nullable = false,unique = true)//Masih Bingung gimana bisa auto generated value
    private Long no_aplikasi;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNo_aplikasi() {
        return no_aplikasi;
    }

    public void setNo_aplikasi(Long no_aplikasi) {
        this.no_aplikasi = no_aplikasi;
    }
}
