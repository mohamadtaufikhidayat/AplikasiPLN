package com.prodemy.pembayaran.listrik.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_update_jp")
public class UpdateJenisPelanggan {
    @Id
    @Column//pk
    @GeneratedValue(generator = "sequenceupdate",
            strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "sequenceupdate",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "update_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "9101"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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
    private Double tarifUp;

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

    public Double getTarifUp() {
        return tarifUp;
    }

    public void setTarifUp(Double tarifUp) {
        this.tarifUp = tarifUp;
    }
}
