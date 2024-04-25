package com.example.sklep2xd.Dto;

import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdresDto {

    private int idAdresu;
    private String kraj;
    private String miejscowosc;
    private String kodPocztowy;
    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
}
