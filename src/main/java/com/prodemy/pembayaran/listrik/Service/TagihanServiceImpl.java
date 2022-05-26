package com.prodemy.pembayaran.listrik.Service;

import com.prodemy.pembayaran.listrik.Repository.Tagihanrepo;
import com.prodemy.pembayaran.listrik.model.entity.Tagihan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    private Tagihanrepo tagihanrepo;
    @Override
    public Tagihan insertData(Tagihan tagihan) {
        Tagihan entity = tagihanrepo.save(tagihan);
        entity.setIdPenggunaListrik(tagihan.getNoUrut().getIdPenggunaListrik());
        entity.setBulan(tagihan.getNoUrut().getBulanini());
        entity.setKwh(tagihan.getNoUrut().getCttkwh());
        entity.setBiaya(tagihan.getKwh()*tagihan.getIdPenggunaListrik().getIdJenis().getTarif());
        return tagihanrepo.save(entity);
    }

}
