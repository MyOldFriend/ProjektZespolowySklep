package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.AdresEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KlientDto {

    private int idKlienta;
    private String login;
    private String email;
    private String haslo;
    private String telefon;
    private String imie;
    private String nazwisko;
    private AdresEntity adresId;
}
