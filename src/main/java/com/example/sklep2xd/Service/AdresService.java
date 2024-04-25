package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Models.AdresEntity;

import java.util.List;

public interface AdresService {
    List<AdresDto> findallAdress();
    AdresDto findAdresByIdAdresu(int id);
    AdresEntity saveAdres(AdresEntity adres);

    void updateAdres(AdresDto adresDto);
    //AdresEntity editAdres(AdresEntity adres);
}
