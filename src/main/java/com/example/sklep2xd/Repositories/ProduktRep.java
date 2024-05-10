package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.ProduktEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduktRep extends JpaRepository<ProduktEntity, Integer> {
    ProduktEntity findByIdProduktu(int id);
    List<ProduktEntity> findByKategoria_IdKategorii( int id);



}
