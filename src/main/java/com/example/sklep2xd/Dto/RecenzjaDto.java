package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecenzjaDto {
    private int idRecenzji;
    private int ocena;
    private String tresc;
    private ProduktEntity produkt;
    private KlientEntity klient;
}
