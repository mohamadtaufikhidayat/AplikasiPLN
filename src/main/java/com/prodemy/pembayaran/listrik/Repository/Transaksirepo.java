package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Transaksirepo extends JpaRepository<Transaksi,Long> {
}
