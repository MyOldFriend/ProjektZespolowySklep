package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.KategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KategoriaRep extends JpaRepository<KategoriaEntity, Integer> {

    Optional<KategoriaEntity> findByIdKategorii(int id);
}
