package com.prodemy.pembayaran.listrik.model.entity;


import javax.persistence.*;

@Entity
@Table(name="t_plistrik")
public class PenggunaListrik {
    @Id
    @Column(name = "id_peng",unique = true)
    private Long idPengguna;
    @Column
    private String namaPengguna;
    @Column
    private String alamat;
    @Column
    private String provinsi;
    @Column
    private String kota;
    @Column
    private String kecamatan;
    @Column
    private String kelurahan;
    @ManyToOne
    @JoinColumn(name = "idJenis")
    private JenisPelanggan idJenis;

    @ManyToOne
    @JoinColumn(name="no_penggunaapp", nullable = false)
    private User app;


    public Long getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Long idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public JenisPelanggan getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(JenisPelanggan idJenis) {
        this.idJenis = idJenis;
    }

    public User getUser() {
        return app;
    }

    public void setUser(User app) {
        this.app = app;
    }

    public String getProvinsi() { return provinsi; }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() { return kota;  }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {return kecamatan; }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {return kelurahan; }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }
}