package com.prodemy.pembayaran.listrik.Repository;

import com.prodemy.pembayaran.listrik.model.entity.Admin;
import com.prodemy.pembayaran.listrik.model.entity.PenggunaListrik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface PenggunaListrikrepo extends JpaRepository<PenggunaListrik,Long> {
    @Query(value="SELECT daya as daya, jenis_pengguna as jenis FROM t_plistrik WHERE id_pengguna = :id_pengguna" , nativeQuery=true)
    TestProjection findJenisByIdPengguna(Long id_pengguna);
    @Query ("select x from PenggunaListrik x where x.kecamatan = :kecamatan")
    List<PenggunaListrik> findByKecamatan(@PathParam("kecamatan")String kecamatan);
    @Query("select r from PenggunaListrik r where r.idPengguna = :idPengguna")
    Optional<PenggunaListrik> findByIdPengguna(@PathParam("idPelanggan")Long idPengguna);

}
