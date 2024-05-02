package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Repositories.RecenzjaRep;
import com.example.sklep2xd.Service.RecenzjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RecenzjaServiceimpl implements RecenzjaService {

    private RecenzjaRep recenzjaRep;

    @Autowired
    public  RecenzjaServiceimpl (RecenzjaRep recenzjaRep){
        this.recenzjaRep = recenzjaRep;

    }

    private RecenzjaDto mapToRecenzjaDto(RecenzjaEntity recenzja){
        RecenzjaDto recenzjaDto = RecenzjaDto.builder()
                .idRecenzji(recenzja.getIdRecenzji())
                .ocena(recenzja.getOcena())
                .tresc(recenzja.getTresc())
                .produkt(recenzja.getProduktByProduktId())
                .klient(recenzja.getKlientByKlientId())
                .build();
        return recenzjaDto;
    }

    @Override
    public List<RecenzjaDto> findAllRecenzje() {
        List<RecenzjaEntity> recenzje = recenzjaRep.findAll();
        return recenzje.stream().map((recenzja) -> mapToRecenzjaDto(recenzja)).collect(Collectors.toList());
    }

    @Override
    public RecenzjaDto findRecenzjaById(int id) {

        return null;
    }

    @Override
    public RecenzjaEntity saveRecenzja(RecenzjaEntity recenzja) {
        return null;
    }

    @Override
    public List<RecenzjaDto> findRecenzjeByProductId(int id) {
        List<RecenzjaEntity> recenzje = recenzjaRep.findByProdukt_IdProduktu(id);
        return recenzje.stream().map(this::mapToRecenzjaDto).collect(Collectors.toList());
    }


    @Override
    public void removeRecenzja(int idR) {
        RecenzjaEntity recenzja = recenzjaRep.findByIdRecenzji(idR);
        recenzjaRep.delete(recenzja);
    }

    @Override
    public void updateRecenzja(RecenzjaDto recenzjaDto) {

    }
}
