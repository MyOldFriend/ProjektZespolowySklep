package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.RecenzjaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzjaRep extends JpaRepository<RecenzjaEntity, Integer> {
    RecenzjaEntity findByIdRecenzji(int idRecenzji);
    List<RecenzjaEntity> findByProdukt_IdProduktu(int idProduktu);
}
