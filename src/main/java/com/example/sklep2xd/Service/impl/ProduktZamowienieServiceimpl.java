package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.ProduktZamowienieDto;
import com.example.sklep2xd.Models.ProduktZamowienieEntity;
import com.example.sklep2xd.Repositories.ProduktZamowienieRep;
import com.example.sklep2xd.Service.ProduktZamowienieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;
@Controller
public class ProduktZamowienieServiceimpl implements ProduktZamowienieService {

    private ProduktZamowienieRep produktZamowienieRep;


    @Autowired
    public  ProduktZamowienieServiceimpl(ProduktZamowienieRep produktZamowienieRep){
        this.produktZamowienieRep  =produktZamowienieRep;

        }

    private  ProduktZamowienieDto mapToAdresDto(ProduktZamowienieEntity produktZamowienie){
        ProduktZamowienieDto produktZamowienieDto = ProduktZamowienieDto.builder()
                .ilosc(produktZamowienie.getIlosc())
                .produkt(produktZamowienie.getProduktByProduktId())
                .zamowienie(produktZamowienie.getZamowienieByZamowienieId())
                .build();
        return produktZamowienieDto;
    }

    @Override
    public List<ProduktZamowienieDto> findAllProduktZamowienia() {
        List<ProduktZamowienieEntity> produktzamowienia  =produktZamowienieRep.findAll();
        return produktzamowienia.stream().map((produktZamowienie) -> mapToAdresDto(produktZamowienie)).collect(Collectors.toList());
    }

    @Override
    public List<ProduktZamowienieDto> findProduktZamowieniaByIdZamowienia(int id) {
        return null;
    } //trzeba przerobić aby zwrócić listę rekordów jakoś zamiast jednego rekordu bo
    // tabela dla jednego zamówienia ma wiele rekordów

    @Override
    public ProduktZamowienieEntity saveProduktZamowienie(ProduktZamowienieEntity produktZamowienie) {
        //ma zwrócić nowe ProduktZamowienieEntity(id_zamowienia, id_produktu, ilosc);
        return null;
    }
   //do dodawania nowych produktów do zamówienia,
   //pewnie będzie trzeba w pętli dla każdego elementu danego koszyka wywołać.
   //Pozostaje problem tego jak odnieść się do nowego id zamówienia?
   //Zapytanie o ostatnie zamówienie danego klienta?
   //   Co jeśli za dużo klientów na raz spróbuje utworzyć zamówienie i się wypieprzy bo do złego zamówienia produkt doda?
   //Czy istnieje sposób żeby metoda tworząca nowe zamówienie mogła jakoś pozyskać id zamówienia żeby je tu przekazać?
   //   To chyba trzeba będzie na poziomie controllera pozyskać
    @Override
    public void updateProduktZamowienie(ProduktZamowienieDto produktZamowienieDto) {

    } //to raczej trzeba będzie wywoływać w pętli dla każdego zmienionego rekordu
    //(albo, np. wywoływać za każdym razem gdy klient zmieni ilość produktu w swoim zamówieniu?)


}
