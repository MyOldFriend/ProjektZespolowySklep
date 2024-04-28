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

    public static double obliczCeneKoszyka(List<KoszykDto> koszyk) {
        double suma = 0.0;

        for (KoszykDto pozycja : koszyk) {
            double cenaProduktu = pozycja.getProdukt().getCena(); // Pobierz cenę produktu
            int ilosc = pozycja.getIlosc(); // Pobierz ilość produktu
            double wartoscPozycji = cenaProduktu * ilosc; // Oblicz wartość pozycji
            suma += wartoscPozycji; // Dodaj wartość pozycji do sumy
        }

        return suma;
    }
}
