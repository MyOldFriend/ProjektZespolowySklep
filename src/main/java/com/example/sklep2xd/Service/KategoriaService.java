package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Models.AdresEntity;
import com.example.sklep2xd.Models.KategoriaEntity;

import java.util.List;

public interface KategoriaService {
    List<KategoriaDto> findallKategories();
    KategoriaDto findKategoriaByIdKategori(int id);
    KategoriaEntity saveKategoria(KategoriaEntity kategoria);
    void updateKategoria(KategoriaDto kategoriaDto);
}

