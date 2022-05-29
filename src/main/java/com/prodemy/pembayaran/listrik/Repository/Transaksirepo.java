package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
import com.prodemy.pembayaran.listrik.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface Transaksirepo extends JpaRepository<Transaksi,Long> {
    @Query(value = "select * from t_transaksi where no_tagihan = :no and status_transaksi = 'transaksi berhasil' "
            , nativeQuery = true)
    Optional <Transaksi> findByNoTagihanAndStatusTransaksi(Long no);
}