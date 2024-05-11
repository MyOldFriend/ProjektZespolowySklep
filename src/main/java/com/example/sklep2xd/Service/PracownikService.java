package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Models.PracownikEntity;

import java.util.List;

public interface PracownikService {
    List<PracownikDto> findAllPracownicy();
    PracownikDto findPracownikById(int id);
    PracownikEntity savePracownik(PracownikEntity pracownik);
    void updatePracownik(PracownikDto pracownikDto);


    PracownikDto zalogujPracownika(String login, String haslo);
    boolean czyAdmin(PracownikDto pracownikDto);

    void removePracownikById(int id);

}
