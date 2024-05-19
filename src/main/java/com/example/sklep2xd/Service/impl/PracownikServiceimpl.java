package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Repositories.PracownikRep;
import com.example.sklep2xd.Service.PracownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PracownikServiceimpl implements PracownikService {

    private final PracownikRep pracownikRep;

    @Autowired
    public PracownikServiceimpl(PracownikRep pracownikRep) {
        this.pracownikRep = pracownikRep;
    }

    @Override
    public List<PracownikDto> findAllPracownicy() {
        List<PracownikEntity> pracownicy = pracownikRep.findAll();
        return pracownicy.stream().map(this::mapToPracownikDto).collect(Collectors.toList());
    }

    @Override
    public PracownikDto findPracownikById(int id) {
        PracownikEntity pracownik = pracownikRep.findById(id).orElse(null);
        if (pracownik != null) {
            return mapToPracownikDto(pracownik);
        }
        return null;
    }

    @Override
    public PracownikEntity savePracownik(PracownikEntity pracownik) {
        PracownikEntity existingLoginUser = pracownikRep.findByLogin(pracownik.getLogin());
        if (existingLoginUser != null) {
            throw new RuntimeException("Użytkownik o podanym loginie już istnieje");
        }
        return pracownikRep.save(pracownik);
    }

    @Override
    public void updatePracownik(PracownikEntity pracownik) {
        pracownikRep.save(pracownik);
    }

//    @Override
//    public void updatePracownik(PracownikEntity pracownik) {
//        pracownikRep.save(pracownik);
//    }

    @Override
    public PracownikDto zalogujPracownika(String login, String haslo) {
        PracownikEntity pracownik = pracownikRep.findByLogin(login);
        if (pracownik != null && pracownik.getHaslo().equals(haslo)) {
            return mapToPracownikDto(pracownik);
        }
        return null;
    }

    @Override
    public boolean czyAdmin(PracownikDto pracownikDto) {
        return pracownikDto.getDzial().equals("admin");
    }

    @Override
    public void removePracownikById(int id) {
        PracownikEntity pracownik = pracownikRep.findById(id).orElse(null);
        if (pracownik != null) {
            pracownikRep.delete(pracownik);
        } else {
            throw new RuntimeException("Nie znaleziono pracownika o ID: " + id);
        }
    }

    @Override
    public PracownikDto mapToPracownikDto(PracownikEntity pracownik) {
        return PracownikDto.builder()
                .idPracownika(pracownik.getIdPracownika())
                .login(pracownik.getLogin())
                .haslo(pracownik.getHaslo())
                .dzial(pracownik.getDzial())
                .imie(pracownik.getImie())
                .nazwisko(pracownik.getNazwisko())
                .roles(pracownik.getRoles())
                .build();
    }


    @Override
    public PracownikEntity mapToPracownikEntity(PracownikDto pracownikDto) {
        return PracownikEntity.builder()
                .idPracownika(pracownikDto.getIdPracownika())
                .login(pracownikDto.getLogin())
                .haslo(pracownikDto.getHaslo())
                .dzial(pracownikDto.getDzial())
                .imie(pracownikDto.getImie())
                .nazwisko(pracownikDto.getNazwisko())
                .roles(pracownikDto.getRoles())
                .build();
    }
}
