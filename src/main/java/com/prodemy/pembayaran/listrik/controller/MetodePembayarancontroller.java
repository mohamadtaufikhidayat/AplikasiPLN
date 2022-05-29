package com.prodemy.pembayaran.listrik.controller;

import com.prodemy.pembayaran.listrik.Repository.MetodePembayaranrepo;
import com.prodemy.pembayaran.listrik.model.dto.DefaultResponse;
import com.prodemy.pembayaran.listrik.model.dto.MetodePembayaranDto;
import com.prodemy.pembayaran.listrik.model.entity.MetodePembayaran;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/MetodePembayaran")
public class MetodePembayarancontroller {
    final private MetodePembayaranrepo metodePembayaranrepo;

    public MetodePembayarancontroller(MetodePembayaranrepo metodePembayaranrepo) {
        this.metodePembayaranrepo = metodePembayaranrepo;
    }
    @PostMapping("/addMetodePembayaranarray")
    public MetodePembayaranDto[] addMetodePembayaran(@RequestBody MetodePembayaranDto[] listbanyak){
        for(MetodePembayaranDto m:listbanyak){
            metodePembayaranrepo.save(conversiDtoToEntity(m));
        }
        return listbanyak;
    }
    @PostMapping("addMetodepembayaran")
    public DefaultResponse<MetodePembayaranDto> addmetodebayar(@RequestBody MetodePembayaranDto metodePembayaranDto){
        DefaultResponse<MetodePembayaranDto>response=new DefaultResponse<>();
        Optional<MetodePembayaran>optional=metodePembayaranrepo.findById(metodePembayaranDto.getMetodeBayar());
        if(optional.isEmpty()){
            response.setPesan("metode pembayaran baru telah ditambahkan dalam sistem");
            response.setData(metodePembayaranDto);
            metodePembayaranrepo.save(conversiDtoToEntity(metodePembayaranDto));
        }else{
            response.setPesan("gagal menambahkan metode pembayaran karena metode pembayaran ini sudah ada dan terdafatar dalam sistem");
        }
        return response;
    }
    @PostMapping("updateMetodepembayaran")
    public DefaultResponse<MetodePembayaranDto> updatemetodebayar(@RequestBody MetodePembayaranDto metodePembayaranDto){
        DefaultResponse<MetodePembayaranDto>response=new DefaultResponse<>();
        Optional<MetodePembayaran>optional=metodePembayaranrepo.findById(metodePembayaranDto.getMetodeBayar());
        if(optional.isPresent()){
            response.setPesan("metode pembayaran berhasil di update");
            response.setData(metodePembayaranDto);
            metodePembayaranrepo.save(conversiDtoToEntity(metodePembayaranDto));
        }else{
            response.setPesan("gagal update metode pembayaran karena metode pembayaran ini belum ada dan belum terdafatar dalam sistem");
        }
        return response;
    }
    @GetMapping("listMetodePembayaran/")
    public List<MetodePembayaranDto>searchMetode(){
        List<MetodePembayaranDto>list=listdata();
        return list;
    }
    @GetMapping("listMetodePembayaran/{nama}")
    public List<MetodePembayaranDto>searchMetodenama(@PathVariable String nama){
        List<MetodePembayaranDto>list=listdata();
        MetodePembayaranDto vari = new MetodePembayaranDto();
        List<MetodePembayaranDto>listsearchnama= new ArrayList<>();
        for (MetodePembayaranDto m : list){
            if((m.getMetodeBayar().toLowerCase()).matches((".*"+nama+".*").toLowerCase())){
                vari=m;
                listsearchnama.add(vari);
            }
        }
        return listsearchnama;
    }
    public List<MetodePembayaranDto> listdata(){
        List<MetodePembayaranDto>list=new ArrayList<>();
        for(MetodePembayaran m :metodePembayaranrepo.findAll()){
            list.add(conversiEntityToDto(m));
        }
        return list;
    }
    public List<MetodePembayaran> listdataEntity(){
        List<MetodePembayaran>listEntity=new ArrayList<>();
        for(MetodePembayaran m :metodePembayaranrepo.findAll()){
            listEntity.add(m);
        }
        return listEntity;
    }
    public MetodePembayaran conversiDtoToEntity(MetodePembayaranDto metodePembayaranDto){
        MetodePembayaran metodePembayaran=new MetodePembayaran();
        metodePembayaran.setMetodeBayar(metodePembayaranDto.getMetodeBayar());
        metodePembayaran.setNoRekPLN(metodePembayaranDto.getNoRekPLN());
        return metodePembayaran;
    }
    public MetodePembayaranDto conversiEntityToDto(MetodePembayaran metodePembayaran){
        MetodePembayaranDto metodePembayaranDto = new MetodePembayaranDto();
        metodePembayaranDto.setMetodeBayar(metodePembayaran.getMetodeBayar());
        metodePembayaranDto.setNoRekPLN(metodePembayaran.getNoRekPLN());
        return metodePembayaranDto;
    }
}