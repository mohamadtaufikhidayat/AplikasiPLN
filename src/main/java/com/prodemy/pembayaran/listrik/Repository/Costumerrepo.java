package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Cosutumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface Costumerrepo extends JpaRepository<Cosutumer,Long> {

    @Query("select x from Cosutumer x where x.user.noInduk =:no_user")
     List<Cosutumer> findByUser(@PathParam("no_user")Long no_user);
}

