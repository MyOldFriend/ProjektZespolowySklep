package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.RecenzjaEntity;

import java.util.List;

public interface RecenzjaService {
    List<RecenzjaDto> findAllRecenzje();
    RecenzjaDto findRecenzjaById(int id);
    RecenzjaEntity saveRecenzja(RecenzjaEntity recenzja);
    List<RecenzjaDto> findRecenzjeByProductId(int id); // Dodana metoda

    void removeRecenzja(int idRec);
    void updateRecenzja(RecenzjaDto recenzjaDto);
}
