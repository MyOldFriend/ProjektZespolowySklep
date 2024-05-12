package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.RolaPracownika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolaPracownikaRep extends JpaRepository<RolaPracownika, Integer> {
    Optional<RolaPracownika> findByNazwa(String nazwa);
}
