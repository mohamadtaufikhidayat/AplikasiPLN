package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface Transferrepo extends JpaRepository<Transfer, Long> {
    @Query("select x from Transfer  x where x.acc.notel = :no_telp")
    List<Transfer> findByNotel(@PathParam("no_telp") Long no_telp);

}
