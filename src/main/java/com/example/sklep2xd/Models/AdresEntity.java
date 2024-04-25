package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adres", schema = "public", catalog = "Sklep")
public class AdresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_adresu", nullable = false)
    private int idAdresu;
    @Basic
    @Column(name = "kraj", nullable = false, length = 100)
    private String kraj;
    @Basic
    @Column(name = "miejscowosc", nullable = false, length = 100)
    private String miejscowosc;
    @Basic
    @Column(name = "kod_pocztowy", nullable = true, length = 6)
    private String kodPocztowy;
    @Basic
    @Column(name = "ulica", nullable = true, length = 100)
    private String ulica;
    @Basic
    @Column(name = "nr_domu", nullable = true, length = 4)
    private String nrDomu;
    @Basic
    @Column(name = "nr_mieszkania", nullable = true, length = 4)
    private String nrMieszkania;

    public int getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(int idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(String nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

}
