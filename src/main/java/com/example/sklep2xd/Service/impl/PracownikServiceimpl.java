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

    private PracownikRep pracownikRep;
    @Autowired
    public PracownikServiceimpl(PracownikRep pracownikRep){
        this.pracownikRep = pracownikRep;
    }

    private  PracownikDto mapToPracownikDto(PracownikEntity pracownik){
        PracownikDto pracownikDto = PracownikDto.builder()
                .idPracownika(pracownik.getIdPracownika())
                .login(pracownik.getLogin())
                .haslo(pracownik.getHaslo())
                .dzial(pracownik.getDzial())
                .imie(pracownik.getImie())
                .nazwisko(pracownik.getNazwisko())
                .build();
        return  pracownikDto;
    }

    @Override
    public List<PracownikDto> findAllPracownicy() {
        List<PracownikEntity> pracownicy = pracownikRep.findAll();
        return pracownicy.stream().map((pracownik) -> mapToPracownikDto(pracownik)).collect(Collectors.toList());
    }

    @Override
    public PracownikDto findPracownikById(int id) {
        return null;
    }

    @Override
    public PracownikEntity savePracownik(PracownikEntity pracownik) {
        return null;
    }

    @Override
    public void updatePracownik(PracownikDto pracownikDto) {

    }
}
