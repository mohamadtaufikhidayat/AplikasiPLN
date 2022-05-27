package com.prodemy.pembayaran.listrik.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


@Entity
@Table(name = "tbl_peluser")
public class Cosutumer {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_pelanggan_sequence"),
                    @Parameter(name = "initial_value", value = "52021031"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id_langganan")
    private Long no_langganan;

    @ManyToOne
    @JoinColumn(name="id_pengguna")
    private User user;

    @ManyToOne
    @JoinColumn(name="id_meteran")
    private PenggunaListrik pel;

    public Long getNo_langganan() {
        return no_langganan;
    }

    public void setNo_langganan(Long no_langganan) {
        this.no_langganan = no_langganan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PenggunaListrik getPel() {
        return pel;
    }

    public void setPel(PenggunaListrik pel) {
        this.pel = pel;
    }
}
