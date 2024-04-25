package com.example.sklep2xd.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProduktZamowieniePK implements Serializable {
    @Column(name = "zamowienie_id")
    private Integer zamowienieId;
    @Column(name = "produkt_id")
    private Integer produktId;

    @Override
    public int hashCode() {
        return Objects.hash(zamowienieId, produktId);
    }
    public Integer getProduktId() {
        return produktId;
    }

    public void setProduktId(Integer produktId) {
        this.produktId = produktId;
    }

    public Integer getZamowienieId() {
        return zamowienieId;
    }

    public void setZamowienieId(Integer zamowienieId) {
        this.zamowienieId = zamowienieId;
    }


    // Default constructor, getters, setters, equals, and hashCode methods
}
