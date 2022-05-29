package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
import com.prodemy.pembayaran.listrik.model.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Tagihanrepo extends JpaRepository<Tagihan,Long> {
    Optional<Tagihan> findByIdPenggunaListrikIdPenggunaAndBulan(Long n, String k);

    List<Tagihan> findByIdPenggunaListrikIdPengguna(Long idPengguna);
}
