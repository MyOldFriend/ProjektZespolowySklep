package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ZamowienieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ZamowienieRep extends JpaRepository<ZamowienieEntity, Integer> {
     ZamowienieEntity findByIdZamowienia( int id);
    List<ZamowienieEntity> findByKlient_IdKlienta(int id);
    List<ZamowienieEntity> findByPracownik_IdPracownika(int id);

}
