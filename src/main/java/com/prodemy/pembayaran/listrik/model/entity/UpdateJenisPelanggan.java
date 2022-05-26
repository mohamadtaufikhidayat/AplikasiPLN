package com.prodemy.pembayaran.listrik.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_update_jp")
public class UpdateJenisPelanggan {
    @Id
    @Column//pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date tglUp;

    @Column
    private Long idJenisUp;
    @Column
    private String jenisUp;

    @Column
    private String dayaUp;

    @Column
    private Long tarifUp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTglUp() {
        return tglUp;
    }

    public void setTglUp(Date tglUp) {
        this.tglUp = tglUp;
    }

    public Long getIdJenisUp() {
        return idJenisUp;
    }

    public void setIdJenisUp(Long idJenisUp) {
        this.idJenisUp = idJenisUp;
    }

    public String getJenisUp() {
        return jenisUp;
    }

    public void setJenisUp(String jenisUp) {
        this.jenisUp = jenisUp;
    }

    public String getDayaUp() {
        return dayaUp;
    }

    public void setDayaUp(String dayaUp) {
        this.dayaUp = dayaUp;
    }

    public Long getTarifUp() {
        return tarifUp;
    }

    public void setTarifUp(Long tarifUp) {
        this.tarifUp = tarifUp;
    }
}
