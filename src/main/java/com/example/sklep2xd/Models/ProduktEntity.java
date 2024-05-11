package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produkt", schema = "public", catalog = "Sklep")
public class ProduktEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_produktu", nullable = false)
    private int idProduktu;
    @Basic
    @Column(name = "nazwa", nullable = true, length = 100)
    private String nazwa;
    @Basic
    @Column(name = "cena", nullable = true, precision = 0)
    private Double cena;
    @Basic
    @Column(name = "rozmiar", nullable = true)
    private String rozmiar;
    @ManyToOne
    @JoinColumn(name = "kategoria_id", referencedColumnName = "id_kategorii")
    private KategoriaEntity kategoria;
//    @Basic
//    @Column(name = "kategoria_id", nullable = true)
//    private Integer kategoriaId;
    @Basic
    @Column(name = "urlzdjecia", nullable = true, length = 255)
    private String urlzdjecia;

//    @ManyToOne
//    @JoinColumn(name = "id_produktu", referencedColumnName = "produkt_id")
//    private ProduktZamowienieEntity produktZamowienie;

    @Basic
    @Column(name = "opis", nullable = true)
    private String opis;

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(String rozmiar) {
        this.rozmiar = rozmiar;
    }

//    public Integer getKategoriaId() {
//        return kategoriaId;
//    }
//
//    public void setKategoriaId(Integer kategoriaId) {
//        this.kategoriaId = kategoriaId;
//    }
    public KategoriaEntity getKategoriaByKategoriaId() {
    return kategoria;
}

    public void setKategoriaByKategoriaId(KategoriaEntity kategoriaByKategoriaId) {
        this.kategoria = kategoriaByKategoriaId;
    }

    public String getUrlzdjecia() {
        return urlzdjecia;
    }

    public void setUrlzdjecia(String urlzdjecia) {
        this.urlzdjecia = urlzdjecia;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }


}
