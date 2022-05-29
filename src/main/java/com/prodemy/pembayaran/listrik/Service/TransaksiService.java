package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.model.entity.Transaksi;

public interface TransaksiService {
    Transaksi insertDataTransaksi(Transaksi transaksi);
    Transaksi  insertDataTransaksibanyak(Transaksi transaksi);
}