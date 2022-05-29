package com.prodemy.pembayaran.listrik.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(
            generator = "sequencetransaksi",
            strategy = GenerationType.SEQUENCE

    )
    @GenericGenerator(
            name = "sequencetransaksi",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "product_sequencetransakasi"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10001"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column//pk
    private Long noTransaksi;
    @OneToOne//fk
    @JoinColumn(name="noTagihan")
    private Tagihan noTagihan;
    //    @ManyToOne
//    @JoinColumn(name="IdPenggunaListrik")
//    private PenggunaListrik idPenggunaListrik;
    @ManyToOne
    @JoinColumn(name="IdNoPelanggan")
    private Pelanggan idNoPelanggan;
    @ManyToOne
    @JoinColumn(name="metodePembayaran")
    private MetodePembayaran metodePembayaran;
    @Column
    private String konfirmasiMetodePembayaran;
    @Column
    private Long noRekPLN;
    @Column
    private Long konfirmasirekeningpln;
    @Column
    private Long biaya;
    @Column
    private Long konfirmasiBiaya;
    @Column
    private String statusTransaksi;

    public Long getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(Long noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public Tagihan getNoTagihan() {
        return noTagihan;
    }

    public void setNoTagihan(Tagihan noTagihan) {
        this.noTagihan = noTagihan;
    }

    public Pelanggan getIdNoPelanggan() {
        return idNoPelanggan;
    }

    public void setIdNoPelanggan(Pelanggan idNoPelanggan) {
        this.idNoPelanggan = idNoPelanggan;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getKonfirmasiMetodePembayaran() {
        return konfirmasiMetodePembayaran;
    }

    public void setKonfirmasiMetodePembayaran(String konfirmasiMetodePembayaran) {
        this.konfirmasiMetodePembayaran = konfirmasiMetodePembayaran;
    }

    public Long getNoRekPLN() {
        return noRekPLN;
    }

    public void setNoRekPLN(Long noRekPLN) {
        this.noRekPLN = noRekPLN;
    }

    public Long getKonfirmasirekeningpln() {
        return konfirmasirekeningpln;
    }

    public void setKonfirmasirekeningpln(Long konfirmasirekeningpln) {
        this.konfirmasirekeningpln = konfirmasirekeningpln;
    }

    public Long getBiaya() {
        return biaya;
    }

    public void setBiaya(Long biaya) {
        this.biaya = biaya;
    }

    public Long getKonfirmasiBiaya() {
        return konfirmasiBiaya;
    }

    public void setKonfirmasiBiaya(Long konfirmasiBiaya) {
        this.konfirmasiBiaya = konfirmasiBiaya;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }
}
