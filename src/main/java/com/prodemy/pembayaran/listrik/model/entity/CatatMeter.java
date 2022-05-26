package com.prodemy.pembayaran.listrik.model.entity;

import javax.persistence.*;
@Entity
@Table(name = "t_catatmeter")
public class CatatMeter {
    @Id
    @Column//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatat;

    @ManyToOne
    @JoinColumn(name = "idPenggunaListrik",  nullable = false)
    private PenggunaListrik idPenggunaListrik;

    @Column(name = "kwh",  nullable = false)
    private Long cttkwh;

    @Column(name = "bulan",  nullable = false)
    private String bulanini;

    public Long getIdCatat() {
        return idCatat;
    }

    public void setIdCatat(Long idCatat) {
        this.idCatat = idCatat;
    }

    public PenggunaListrik getIdPenggunaListrik() {
        return idPenggunaListrik;
    }

    public void setIdPenggunaListrik(PenggunaListrik idPenggunaListrik) {
        this.idPenggunaListrik = idPenggunaListrik;
    }

    public Long getCttkwh() {
        return cttkwh;
    }

    public void setCttkwh(Long cttkwh) {
        this.cttkwh = cttkwh;
    }

    public String getBulanini() {
        return bulanini;
    }

    public void setBulanini(String bulanini) {
        this.bulanini = bulanini;
    }
}
