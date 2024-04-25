package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.KoszykPK;
import com.example.sklep2xd.Models.ProduktEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KoszykDto {
    private Integer ilosc;
    private KlientEntity klient;
    private ProduktEntity produkt;
    private KoszykPK kpk;
}
