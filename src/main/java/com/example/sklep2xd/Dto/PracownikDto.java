package com.example.sklep2xd.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracownikDto {
    private int idPracownika;
    private String login;
    private String haslo;
    private String dzial;
    private String imie;
    private String nazwisko;
}
