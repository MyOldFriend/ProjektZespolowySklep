package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.KategoriaEntity;
import com.example.sklep2xd.Models.RozmiaryEntity;
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
    private String urlzdjecia;
    private KategoriaEntity kategoria;

}
