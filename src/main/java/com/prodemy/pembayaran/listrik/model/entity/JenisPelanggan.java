package com.prodemy.pembayaran.listrik.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_jenis_pelanggan")
public class JenisPelanggan {
    @Id
    @Column//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJenis;

    @Column
    private String jenis;

    @Column
    private String daya;

    @Column
    private Double tarif;

    public Long getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(Long idJenis) {
        this.idJenis = idJenis;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDaya() {
        return daya;
    }

    public void setDaya(String daya) {
        this.daya = daya;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
}
