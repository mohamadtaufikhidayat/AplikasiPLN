package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Pelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.lang.reflect.Array;
import java.util.List;

public interface Pelangganrepo extends JpaRepository<Pelanggan,Long> {

    @Query("select x from Pelanggan x where x.user.noInduk =:no_user")
     List<Pelanggan> findByUser(@PathParam("no_user")Long no_user);
}

