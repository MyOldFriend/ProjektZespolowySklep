package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recenzja", schema = "public", catalog = "Sklep")
public class RecenzjaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recenzji", nullable = false)
    private int idRecenzji;
    @Basic
    @Column(name = "ocena", nullable = false)
    private int ocena;
    @Basic
    @Column(name = "tresc", nullable = true, length = 200)
    private String tresc;
//    @Basic
//    @Column(name = "produkt_id", nullable = true)
//    private Integer produktId;
//    @Basic
//    @Column(name = "klient_id", nullable = true)
//    private Integer klientId;
    @ManyToOne
    @JoinColumn(name = "produkt_id", referencedColumnName = "id_produktu")
    private ProduktEntity produkt;
    @ManyToOne
    @JoinColumn(name = "klient_id", referencedColumnName = "id_klienta")
    private KlientEntity klient;

    public int getIdRecenzji() {
        return idRecenzji;
    }

    public void setIdRecenzji(int idRecenzji) {
        this.idRecenzji = idRecenzji;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

//    public Integer getProduktId() {
//        return produktId;
//    }
//
//    public void setProduktId(Integer produktId) {
//        this.produktId = produktId;
//    }
//
//    public Integer getKlientId() {
//        return klientId;
//    }
//
//    public void setKlientId(Integer klientId) {
//        this.klientId = klientId;
//    }

    public ProduktEntity getProduktByProduktId() {
        return produkt;
    }

    public void setProduktByProduktId(ProduktEntity produktByProduktId) {
        this.produkt = produktByProduktId;
    }

    public KlientEntity getKlientByKlientId() {
        return klient;
    }

    public void setKlientByKlientId(KlientEntity klientByKlientId) {
        this.klient = klientByKlientId;
    }
}
