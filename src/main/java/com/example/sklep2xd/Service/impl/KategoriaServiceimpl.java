package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Models.KategoriaEntity;
import com.example.sklep2xd.Repositories.KategoriaRep;
import com.example.sklep2xd.Service.KategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KategoriaServiceimpl implements KategoriaService {

    private KategoriaRep kategoriaRep;

    @Autowired
    public KategoriaServiceimpl(KategoriaRep kategoriaRep){
        this.kategoriaRep = kategoriaRep;
    }

    @Override
    public List<KategoriaDto> findallKategories(){
        List<KategoriaEntity> kategorie =kategoriaRep.findAll();
        return kategorie.stream().map((kategoria) -> mapToKategoriaDto(kategoria)).collect(Collectors.toList());
    }

    @Override
    public KategoriaDto findKategoriaByIdKategori(int id) {
        KategoriaEntity kategoria = kategoriaRep.findById(id).orElse(null);
        if (kategoria != null) {
            return mapToKategoriaDto(kategoria);
        } else {
            // Obsługa przypadku, gdy kategoria o danym id nie istnieje
            return null;
        }
    }

    @Override
    public KategoriaEntity saveKategoria(KategoriaEntity kategoria) {
        return null;
    }

    @Override
    public void updateKategoria(KategoriaDto kategoriaDto) {

    }

    @Override
    public List<KategoriaDto> findAllKategories(){
        List<KategoriaEntity> kategorie = kategoriaRep.findAll();
        return kategorie.stream().map(this::mapToKategoriaDto).collect(Collectors.toList());
    }

    private KategoriaDto mapToKategoriaDto(KategoriaEntity kategoria){
        KategoriaDto kategoriaDto = KategoriaDto.builder()
                .idKategorii(kategoria.getIdKategorii())
                .nazwaKategorii(kategoria.getNazwaKategorii())
                .build();
        return kategoriaDto;
    }

    @Override
    public KategoriaEntity mapToKategoriaEntity(KategoriaDto kategoriaDto) {
        KategoriaEntity kategoriaEntity = new KategoriaEntity();
        kategoriaEntity.setIdKategorii(kategoriaDto.getIdKategorii());
        kategoriaEntity.setNazwaKategorii(kategoriaDto.getNazwaKategorii());
        return kategoriaEntity;
    }
}
