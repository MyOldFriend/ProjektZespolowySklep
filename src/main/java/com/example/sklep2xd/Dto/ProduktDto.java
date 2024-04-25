package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.KategoriaEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduktDto {

    private int idProduktu;
    private String nazwa;
    private Double cena;
    private Integer rozmiar;
    private String urlzdjecia;
    private KategoriaEntity kategoria;
}
