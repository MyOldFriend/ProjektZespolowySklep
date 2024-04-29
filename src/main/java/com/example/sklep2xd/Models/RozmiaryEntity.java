package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rozmiary", schema = "public", catalog = "Sklep")
public class RozmiaryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_rozmiaru", nullable = false)
    private int idRozmiaru;
    @Basic
    @Column(name = "rozmiar")
    private String rozmiar;

    @ManyToOne
    @JoinColumn(name = "produkt_id", referencedColumnName = "id_produktu")
    private ProduktEntity produkt;
    public int getIdRozmiaru() {
        return idRozmiaru;
    }

    public void setIdRozmiaru(int idRozmiaru) {
        this.idRozmiaru = idRozmiaru;
    }

    public String getRozmiar() {
        return rozmiar;
    }
    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

    public ProduktEntity getProdukt() {
        return produkt;
    }

    public void setProdukt(ProduktEntity produkt) {
        this.produkt = produkt;
    }
}
