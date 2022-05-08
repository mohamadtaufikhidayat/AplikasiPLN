package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Login;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public interface LoginRepo extends JpaRepository<Login,Long> {

    @Query("select p from Login p where p.user.email = :email")
    List<Login> findByEmail(@PathParam("email")String email);
}

