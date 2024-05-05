package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduktServiceimpl implements ProduktService {

    private ProduktRep produktRep;
    @Autowired
    public ProduktServiceimpl(ProduktRep produktRep){
        this.produktRep = produktRep;
    }


    private ProduktDto mapToProduktDto(ProduktEntity produkt) {
        return ProduktDto.builder()
                .idProduktu(produkt.getIdProduktu())
                .nazwa(produkt.getNazwa())
                .cena(produkt.getCena())
                .rozmiar(produkt.getRozmiar())
                .urlzdjecia(produkt.getUrlzdjecia())
                .kategoria(produkt.getKategoriaByKategoriaId())
                .build();
    }

    @Override
    public List<ProduktDto> findAllProdukty() {
        List<ProduktEntity> produkty = produktRep.findAll();
        return produkty.stream().map((produkt) -> mapToProduktDto(produkt)).collect(Collectors.toList());
    }

    @Override
    public List<ProduktDto> findProdukyByKategoria_KategoriaId(int idKat) {
        List<ProduktEntity> produkty = produktRep.findByKategoria_IdKategorii(idKat);
        return produkty.stream().map((produkt) -> mapToProduktDto(produkt)).collect(Collectors.toList());
    }

    @Override
    public ProduktDto findProduktById(int id) {
        ProduktEntity produkt = produktRep.findById(id).orElse(null);
        if (produkt != null) {
            return mapToProduktDto(produkt);
        }
        return null;
    }

    @Override
    public ProduktEntity saveProdukt(ProduktEntity produkt) {
        return produktRep.save(produkt);
    }

    @Override
    public void updateProdukt(ProduktDto produktDto) {
        ProduktEntity existingProdukt = produktRep.findById(produktDto.getIdProduktu()).orElse(null);
        if (existingProdukt != null) {
            existingProdukt.setNazwa(produktDto.getNazwa());
            existingProdukt.setCena(produktDto.getCena());
            existingProdukt.setRozmiar(produktDto.getRozmiar());
            existingProdukt.setUrlzdjecia(produktDto.getUrlzdjecia());
            // Ustawienie kategorii - wymaga implementacji
            produktRep.save(existingProdukt);
        }
    }

    @Override
    public void removeProduktById(int produktId) {
        ProduktEntity produkt = produktRep.findByIdProduktu(produktId);
        if (produkt != null) {
            produktRep.delete(produkt);
        }
    }
}
