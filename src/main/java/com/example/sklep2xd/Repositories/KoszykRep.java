package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.KoszykEntity;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KoszykRep extends JpaRepository<KoszykEntity, Integer> {
    List<KoszykEntity> findByProdukt_IdProduktu(int id); //raczej nie potrzebne ale pies
    List<KoszykEntity> findByKlient_IdKlienta(int id); //to interesuje nas barddziej do wyświetlania koszyka klienta wedle mnie - Pawel

    KoszykEntity findByKlient_IdKlientaAndProdukt_IdProduktu(int idk, int idp); //do usuwania potrzebne
}
