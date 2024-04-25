package com.example.sklep2xd.Repositories;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProduktZamowienieRep extends JpaRepository<ProduktZamowienieEntity, Integer> {
        List<ProduktZamowienieEntity> findByProdukt_IdProduktu(int id);
        List<ProduktZamowienieEntity> findByZamowienie_IdZamowienia( int id);
}
