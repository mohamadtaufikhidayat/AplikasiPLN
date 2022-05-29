package com.prodemy.pembayaran.listrik.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="t_tagihan")
public class Tagihan {

    @Id
    @Column//pk
    @GeneratedValue(generator = "sequencetagihan",
            strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "sequencetagihan",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tagihan_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "8101"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long noTagihan;

    @ManyToOne//fk
    @JoinColumn(name="IdPenggunaListrik")
    private PenggunaListrik idPenggunaListrik;

    @OneToOne
    @JoinColumn(name = "idCatat")
    private CatatMeter idCatat;
    @Column(name = "bulan")
    private String bulan;

    @Column(name = "biaya")
    private Long biaya;
    @Column(name = "kwh")
    private Long kwh;
//    @Column
//    private String metodePembayaran;
//    @Column
//    private String status;
    public Long getNoTagihan() {
        return noTagihan;
    }

    public void setNoTagihan(Long noTagihan) {
        this.noTagihan = noTagihan;
    }

    public CatatMeter getIdCatat() {
        return idCatat;
    }

    public void setIdCatat(CatatMeter idCatat) {
        this.idCatat = idCatat;
    }

    public PenggunaListrik getIdPenggunaListrik() {
        return idPenggunaListrik;
    }

    public void setIdPenggunaListrik(PenggunaListrik idPenggunaListrik) {
        this.idPenggunaListrik = idPenggunaListrik;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public long getBiaya() {
        return biaya;
    }

    public void setBiaya(long biaya) {
        this.biaya = biaya;
    }

    public long getKwh() {
        return kwh;
    }

    public void setKwh(long kwh) {
        this.kwh = kwh;
    }

//    public String getMetodePembayaran() {
//        return metodePembayaran;
//    }
//
//    public void setMetodePembayaran(String metodePembayaran) {
//        this.metodePembayaran = metodePembayaran;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}