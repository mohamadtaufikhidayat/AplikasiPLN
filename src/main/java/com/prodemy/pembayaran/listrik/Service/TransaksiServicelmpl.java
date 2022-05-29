package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.Repository.Transaksirepo;
import com.prodemy.pembayaran.listrik.model.entity.Transaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransaksiServicelmpl implements TransaksiService{
    @Autowired
    private Transaksirepo transaksirepo;

    @Override
    public Transaksi insertDataTransaksi(Transaksi transaksi) {
        //  transaksirepo.save(transaksi);
        return transaksirepo.save(transaksi);
    }
    @Override
    public Transaksi insertDataTransaksibanyak(Transaksi transaksibanyak){
        return transaksirepo.save(transaksibanyak);
    }
}