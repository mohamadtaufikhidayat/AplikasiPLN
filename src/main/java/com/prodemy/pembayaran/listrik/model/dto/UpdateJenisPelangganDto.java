package com.prodemy.pembayaran.listrik.model.dto;

import java.util.Date;

public class UpdateJenisPelangganDto {
    private Date tglUp;

    private Long idJenisUp;

    private String jenisUp;

    private String dayaUp;

    private Double tarifUp;

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
