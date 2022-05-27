package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    @Query("select p from User p where p.email = :email")
    Optional<User> findByEmail(@PathParam("email")String email);

    @Query ("select x from Admin x where x.no_aplikasi = :no_Pegawai")
    Optional<Admin> findByNo_aplikasi(@PathParam("no_pegawai")Long no_Pegawai);

    @Query("select x from Admin x where x.id =:id")
    Optional<Admin> findByNoID(@PathParam("id")Long id);
}
