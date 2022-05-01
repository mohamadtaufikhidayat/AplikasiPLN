package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {

    @Query("select x from Admin x where x.id =:no_Pegawai")
    Optional<Admin> findByNo_aplikasi(@PathParam("no_pegawi")Long no_Pegawai);
    @Query ("select x from Admin x where x.id = : no_Pegawai")
    Optional<Admin> findByNo_pegawai(@PathParam("no_pegawai")Long no_Pegawai);
}
