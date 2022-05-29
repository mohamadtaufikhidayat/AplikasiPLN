package com.prodemy.pembayaran.listrik.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Entity
@Table(name = "t_catatmeter")
public class CatatMeter {
    @Id
    @Column//pk
    @GeneratedValue(generator = "sequencecatat",
            strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "sequencecatat",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "catat_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "3101"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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
