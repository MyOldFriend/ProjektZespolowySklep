package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.RecenzjaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecenzjaRep extends JpaRepository<RecenzjaEntity, Integer> {
    RecenzjaEntity findByIdRecenzji( int id);
    List<RecenzjaEntity> findByKlient_IdKlienta(int id);
    List<RecenzjaEntity> findByProdukt_IdProduktu(int id);
}
