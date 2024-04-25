package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "koszyk",  schema = "public", catalog = "Sklep")
public class KoszykEntity {

    @Basic
    @Column(name = "ilosc", nullable = true)
    private Integer ilosc;
    @MapsId("idKlienta")
    @ManyToOne
    @JoinColumn(name = "id_klienta", referencedColumnName = "id_klienta")
    private KlientEntity klient;
    @MapsId("idProduktu")
    @ManyToOne
    @JoinColumn(name = "id_produktu", referencedColumnName = "id_produktu")
    private ProduktEntity produkt;
    @EmbeddedId
    private KoszykPK kpk;

}
