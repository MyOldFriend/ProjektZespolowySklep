package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Repositories.RecenzjaRep;
import com.example.sklep2xd.Service.RecenzjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecenzjaServiceimpl implements RecenzjaService {

    private final RecenzjaRep recenzjaRep;
    private final KlientRep klientRep;
    private final ProduktRep produktRep;

    @Autowired
    public RecenzjaServiceimpl(RecenzjaRep recenzjaRep, KlientRep klientRep, ProduktRep produktRep) {
        this.recenzjaRep = recenzjaRep;
        this.klientRep = klientRep;
        this.produktRep = produktRep;
    }

    private RecenzjaDto mapToRecenzjaDto(RecenzjaEntity recenzja) {
        return RecenzjaDto.builder()
                .idRecenzji(recenzja.getIdRecenzji())
                .ocena(recenzja.getOcena())
                .tresc(recenzja.getTresc())
                .produkt(recenzja.getProduktByProduktId())
                .klient(recenzja.getKlientByKlientId())
                .build();
    }

    @Override
    public List<RecenzjaDto> findAllRecenzje() {
        return recenzjaRep.findAll().stream()
                .map(this::mapToRecenzjaDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecenzjaDto findRecenzjaById(int id) {
        RecenzjaEntity recenzja = recenzjaRep.findByIdRecenzji(id);
        return recenzja != null ? mapToRecenzjaDto(recenzja) : null;
    }

    @Override
    public RecenzjaEntity saveRecenzja(RecenzjaEntity recenzja) {
        return recenzjaRep.save(recenzja);
    }

    @Override
    public List<RecenzjaDto> findRecenzjeByProductId(int id) {
        return recenzjaRep.findByProdukt_IdProduktu(id).stream()
                .map(this::mapToRecenzjaDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeRecenzja(int idR) {
        recenzjaRep.deleteById(idR);
    }

    @Override
    public void updateRecenzja(RecenzjaDto recenzjaDto) {
        RecenzjaEntity recenzja = recenzjaRep.findByIdRecenzji(recenzjaDto.getIdRecenzji());
        if (recenzja != null) {
            recenzja.setOcena(recenzjaDto.getOcena());
            recenzja.setTresc(recenzjaDto.getTresc());
            recenzjaRep.save(recenzja);
        }
    }
}
