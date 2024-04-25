package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Models.KlientEntity;

import java.util.List;

public interface KlientService {

    List<KlientDto> findAllKlients();
    KlientDto findKlientById(int id);
    KlientEntity saveKlient(KlientEntity klient);
    void updateKlient(KlientDto klientDto);
}
