package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pelangganrepo extends JpaRepository<Pelanggan,Long> {
}