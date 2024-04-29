package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.RozmiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RozmiaryRep extends JpaRepository<RozmiaryEntity, Integer> {

    RozmiaryEntity findByIdRozmiaru( int id);
    List<RozmiaryEntity> findByProdukt_IdProduktu(int idProduktu);


}
