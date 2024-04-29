package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.ProduktEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RozmiaryDto {

    private int idRozmiaru;
    private String rozmiar;
    private ProduktEntity produkt;

}
