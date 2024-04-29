package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.KlientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlientRep  extends JpaRepository<KlientEntity, Integer> {
    KlientEntity findByIdKlienta(int id);
    KlientEntity findByEmail(String email); // Dodane
    KlientEntity findByLogin(String login); // Dodane
}
