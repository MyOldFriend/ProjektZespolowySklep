package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Models.AdresEntity;
import com.example.sklep2xd.Repositories.AdresRep;
import com.example.sklep2xd.Service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AdresServiceimpl implements AdresService {

    private AdresRep adresRep;

    @Autowired
    public AdresServiceimpl(AdresRep adresRep){
        this.adresRep = adresRep;
    }

    @Override
    public List<AdresDto> findallAdress() {
        List<AdresEntity> adreses = adresRep.findAll();
        return adreses.stream().map((adres) -> mapToAdresDto(adres)).collect(Collectors.toList());
    }

    @Override
    public AdresDto findAdresByIdAdresu(int id) {
        AdresEntity adres = adresRep.findByIdAdresu(id);
        return mapToAdresDto(adres);
    }

    @Override //implementacja do serwisu
    public AdresEntity saveAdres(AdresEntity adres) {
        return adresRep.save(adres);
    }

    public void updateAdres(AdresDto adresDto) {
        AdresEntity adres = adresRep.findById(adresDto.getIdAdresu())
                .orElseThrow(() -> new RuntimeException("Adres not found with id " + adresDto.getIdAdresu()));
        adres.setKraj(adresDto.getKraj());
        adres.setMiejscowosc(adresDto.getMiejscowosc());
        adres.setKodPocztowy(adresDto.getKodPocztowy());
        adres.setUlica(adresDto.getUlica());
        adres.setNrDomu(adresDto.getNrDomu());
        adres.setNrMieszkania(adresDto.getNrMieszkania());
        adresRep.save(adres);
    }

    //metoda edycji wymaga mapowania
    private AdresEntity mapToAdres(AdresDto adres) {
        AdresEntity adresDto = AdresEntity.builder()
                .idAdresu(adres.getIdAdresu())
                .kraj(adres.getKraj())
                .miejscowosc(adres.getMiejscowosc())
                .kodPocztowy(adres.getKodPocztowy())
                .ulica(adres.getUlica())
                .nrDomu(adres.getNrDomu())
                .nrMieszkania(adres.getNrMieszkania())
                .build();
        return adresDto;
    }

    private AdresDto mapToAdresDto(AdresEntity adres){
        AdresDto adresDto = AdresDto.builder()
                .idAdresu(adres.getIdAdresu())
                .kraj(adres.getKraj())
                .miejscowosc(adres.getMiejscowosc())
                .kodPocztowy(adres.getKodPocztowy())
                .ulica(adres.getUlica())
                .nrDomu(adres.getNrDomu())
                .nrMieszkania(adres.getNrMieszkania())
                .build();
        return adresDto;
    }
}
