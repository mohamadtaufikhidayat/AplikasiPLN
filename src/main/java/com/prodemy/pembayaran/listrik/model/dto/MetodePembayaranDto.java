package com.prodemy.pembayaran.listrik.model.dto;

public class MetodePembayaranDto {
    private String metodeBayar;
    private Long noRekPLN;

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public Long getNoRekPLN() {
        return noRekPLN;
    }

    public void setNoRekPLN(Long noRekPLN) {
        this.noRekPLN = noRekPLN;
    }
}