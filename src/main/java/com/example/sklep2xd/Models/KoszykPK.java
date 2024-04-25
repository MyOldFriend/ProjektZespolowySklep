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
public class KoszykPK implements Serializable {
    @Column(name = "id_klienta")
    private Integer idKlienta;
    @Column(name = "id_produktu")
    private Integer idProduktu;

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, idProduktu);
    }
    public Integer getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(Integer idProduktu) {
        this.idProduktu = idProduktu;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }


    // Default constructor, getters, setters, equals, and hashCode methods
}
