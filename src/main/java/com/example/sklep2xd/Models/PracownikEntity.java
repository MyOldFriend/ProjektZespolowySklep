package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "pracownik", schema = "public", catalog = "Sklep")
public class PracownikEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pracownika", nullable = false)
    private int idPracownika;
    @Basic
    @Column(name = "login", nullable = true, length = 30)
    private String login;
    @Basic
    @Column(name = "haslo", nullable = false, length = 100)
    private String haslo;
    @Basic
    @Column(name = "dzial", nullable = true, length = 50)
    private String dzial;
    @Basic
    @Column(name = "imie", nullable = true, length = 50)
    private String imie;
    @Basic
    @Column(name = "nazwisko", nullable = true, length = 50)
    private String nazwisko;

    @Builder.Default
    private String someField = "default value";


    public List<RolaPracownika> getRole() {
        return role;
    }

    public void setRole(List<RolaPracownika> role) {
        this.role = role;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Role_pracownicy", joinColumns = @JoinColumn(name = "id_pr", referencedColumnName = "id_pracownika"),
    inverseJoinColumns = @JoinColumn(name = "id_roli", referencedColumnName = "id_roli"))
    private List<RolaPracownika> role = new ArrayList<>();

    public int getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(int idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getDzial() {
        return dzial;
    }

    public void setDzial(String dzial) {
        this.dzial = dzial;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
