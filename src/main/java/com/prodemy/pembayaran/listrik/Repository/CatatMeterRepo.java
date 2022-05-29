package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.CatatMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatatMeterRepo extends JpaRepository<CatatMeter, Long> {
    List<CatatMeter> findByIdPenggunaListrikIdPenggunaAndBulan(Long n, String k);
    Optional<CatatMeter> findByIdCatat(Long id);
}
