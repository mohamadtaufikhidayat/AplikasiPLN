package com.prodemy.pembayaran.listrik.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_assignment")
public class Assignment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Column
    private Long idPetugas;
    @ManyToOne
    @JoinColumn(name = "no_pengaduan")
    private FormPengaduan noPengaduan;

    public Long getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(Long idPetugas) {
        this.idPetugas = idPetugas;
    }

    public FormPengaduan getNoPengaduan() {
        return noPengaduan;
    }

    public void setNoPengaduan(FormPengaduan noPengaduan) {
        this.noPengaduan = noPengaduan;
    }


}
