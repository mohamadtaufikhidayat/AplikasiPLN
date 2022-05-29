package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.MetodePembayaran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodePembayaranrepo extends JpaRepository<MetodePembayaran, String> {
}