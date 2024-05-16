package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.PracownikEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PracownikRep extends JpaRepository<PracownikEntity, Integer> {
    PracownikEntity findByIdPracownika(int id);

    PracownikEntity findByLogin(String login);
}
