package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.ProduktZamowieniePK;
import com.example.sklep2xd.Models.ZamowienieEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduktZamowienieDto {
    private Integer ilosc;
    private ZamowienieEntity zamowienie;
    private ProduktEntity produkt;
    private ProduktZamowieniePK idpz;
}
