package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.AdresEntity;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.PracownikEntity;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ZamowienieDto {
    private int idZamowienia;
    private Date dataZlozenia;
    private Double wartoscZamowienia;
    private Boolean czyZaplacone;
    private String status;
    private AdresEntity adres;
    private KlientEntity klient;
    private PracownikEntity pracownik;
}
