package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.ZamowienieDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ZamowienieEntity;
import com.example.sklep2xd.Repositories.ZamowienieRep;
import com.example.sklep2xd.Service.ZamowienieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZamowienieServiceimpl implements ZamowienieService {

    private ZamowienieRep zamowienieRep;

    @Autowired
    public ZamowienieServiceimpl(ZamowienieRep zamowienieRep){
        this.zamowienieRep = zamowienieRep;

    }

    private ZamowienieDto mapTozamowienieDto(ZamowienieEntity zamowienie){
        ZamowienieDto zamowienieDto  =ZamowienieDto.builder()
                .idZamowienia(zamowienie.getIdZamowienia())
                .dataZlozenia(zamowienie.getDataZlozenia())
                .wartoscZamowienia(zamowienie.getWartoscZamowienia())
                .czyZaplacone(zamowienie.getCzyZaplacone())
                .status(zamowienie.getStatus())
                .adres(zamowienie.getAdresByAdresId())
                .klient(zamowienie.getKlientByKlientId())
                .pracownik(zamowienie.getPracownikByPracownikId())
                .build();
        return zamowienieDto;
    }

    @Override
    public List<ZamowienieDto> findAllZamowienia() {
        List<ZamowienieEntity> zamowienia = zamowienieRep.findAll();
        return zamowienia.stream().map((zamowienie) -> mapTozamowienieDto(zamowienie)).collect(Collectors.toList());
    }

    @Override
    public ZamowienieDto findZamowienieById(int id) {
        return null;
    }

    @Override
    public ZamowienieEntity saveZamowienie(ZamowienieEntity zamowienie) {
        return null;
    }

    @Override
    public void updateZamowienie(ZamowienieDto zamowienieDto) {

    }
}
