package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Models.ProduktEntity;

import java.util.List;

public interface ProduktService {

    List<ProduktDto> findAllProdukty();

    List<ProduktDto> findProdukyByKategoria_KategoriaId(int idKat);

    ProduktDto findProduktById(int id);

    ProduktEntity saveProdukt(ProduktEntity produkt);
    void updateProdukt(ProduktDto produktDto);
}
