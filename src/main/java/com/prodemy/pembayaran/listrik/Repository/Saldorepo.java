package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Account;
import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.Optional;

public interface Saldorepo extends JpaRepository<Saldo, Long> {
    @Query("select x from Saldo x where x.account.notel = :no_telp")
    Optional<Saldo> findByno_telpi(@PathParam("no_telp")Long no_telp);

    @Query("select x.saldo from Saldo x where x.id_acc = :id_acc")
    Long findBySaldo(@PathParam("id_acc")Long id_acc);

    @Query("select x.saldo from Saldo x where x.account.notel= :notelp")
    Long findBySaldoByPhone(@PathParam("notelp")Long notelp);

    @Query("select x.id_acc from Saldo x where x.account.notel= :notelp")
    Long findIdByPhone(@PathParam("notelp")Long notelp);

}
