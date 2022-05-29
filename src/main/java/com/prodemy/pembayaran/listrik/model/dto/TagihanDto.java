package com.prodemy.pembayaran.listrik.model.dto;

public class TagihanDto {
    private Long noTagihan;
    private Long idCatat;
    private Long idPenggunaListrik;
    private String bulan;
    private Long biaya;
    private Long kwh;
    public Long getNoTagihan() {
        return noTagihan;
    }

    public void setNoTagihan(Long noTagihan) {
        this.noTagihan = noTagihan;
    }

    public Long getIdCatat() {
        return idCatat;
    }

    public void setIdCatat(Long idCatat) {
        this.idCatat = idCatat;
    }

    public Long getIdPenggunaListrik() {
        return idPenggunaListrik;
    }

    public void setIdPenggunaListrik(Long idPenggunaListrik) {
        this.idPenggunaListrik = idPenggunaListrik;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public Long getBiaya() {
        return biaya;
    }

    public void setBiaya(Long biaya) {
        this.biaya = biaya;
    }

    public Long getKwh() {
        return kwh;
    }

    public void setKwh(Long kwh) {
        this.kwh = kwh;
    }


}
