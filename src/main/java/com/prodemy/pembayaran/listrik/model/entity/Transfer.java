package com.prodemy.pembayaran.listrik.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="tbl_trans")
public class Transfer {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "transfer_sequence"),
                    @Parameter(name = "initial_value", value = "51021031"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long kodetransfer;
    @ManyToOne
    @JoinColumn(name ="no_telpawal")
    private Account acc;
    @Column(name = "no_tujuan")
    private Long tujuan;
    @Column(name ="rupiah")
    private Long besar;

    @Column(name = "waktu")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date(System.currentTimeMillis());

    public Long getKodetransfer() {
        return kodetransfer;
    }

    public void setKodetransfer(Long kodetransfer) {
        this.kodetransfer = kodetransfer;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public Long getTujuan() {
        return tujuan;
    }

    public void setTujuan(Long tujuan) {
        this.tujuan = tujuan;
    }

    public Long getBesar() {
        return besar;
    }

    public void setBesar(Long besar) {
        this.besar = besar;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
