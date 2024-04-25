package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produkt_zamowienie", schema = "public", catalog = "Sklep")
public class ProduktZamowienieEntity {
//    @Basic
//    @Column(name = "zamowienie_id", nullable = true)
//    private Integer zamowienieId;
//    @Basic
//    @Column(name = "produkt_id", nullable = true)
//    private Integer produktId;

    @Basic
    @Column(name = "ilosc", nullable = true)
    private Integer ilosc;
    @MapsId("idZamowienia")
    @ManyToOne
    @JoinColumn(name = "zamowienie_id", referencedColumnName = "id_zamowienia")
    private ZamowienieEntity zamowienie;
    @MapsId("idProduktu")
    @ManyToOne
    @JoinColumn(name = "produkt_id", referencedColumnName = "id_produktu")
    private ProduktEntity produkt;
    @EmbeddedId
    private ProduktZamowieniePK idpz;

//    public Integer getZamowienieId() {
//        return zamowienieId;
//    }
//
//    public void setZamowienieId(Integer zamowienieId) {
//        this.zamowienieId = zamowienieId;
//    }
//
//    public Integer getProduktId() {
//        return produktId;
//    }
//
//    public void setProduktId(Integer produktId) {
//        this.produktId = produktId;
//    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

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
