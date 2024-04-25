package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.AdresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRep extends JpaRepository<AdresEntity, Integer> {

    AdresEntity findByIdAdresu(int id);
}
