package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produkt_zamowienie", schema = "public", catalog = "Sklep")
public class ProduktZamowienieEntity {

    @EmbeddedId
    private ProduktZamowieniePK idpz;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id", insertable = false, updatable = false)
    private ZamowienieEntity zamowienie;

    @ManyToOne
    @JoinColumn(name = "produkt_id", insertable = false, updatable = false)
    private ProduktEntity produkt;

    @Setter
    @Getter
    @Column(name = "ilosc")
    private Integer ilosc;

    // getters and setters


    public ZamowienieEntity getZamowienieByZamowienieId() {
        return zamowienie;
    }

    public void setZamowienieByZamowienieId(ZamowienieEntity zamowienieByZamowienieId) {
        this.zamowienie = zamowienieByZamowienieId;
    }

    public ProduktEntity getProduktByProduktId() {
        return produkt;
    }

    public void setProduktByProduktId(ProduktEntity produktByProduktId) {
        this.produkt = produktByProduktId;
    }

}
