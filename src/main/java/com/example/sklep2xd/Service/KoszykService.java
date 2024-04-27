package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.KoszykDto;
import com.example.sklep2xd.Models.KoszykEntity;

import java.util.List;

public interface KoszykService {
    List<KoszykDto> findAllKoszyki();
    List<KoszykDto> findKoszykByKlientId(int idKlienta);
    KoszykEntity saveKoszyk(KoszykEntity koszykEntity);
    void deleteKoszykKlienta(int klienta); //do wywołania po złożeniu zamówienia
    void deleteKoszyk(int idKlienta, int idProduktu);
    void updateKoszyk(KoszykEntity koszykEntity);
}
