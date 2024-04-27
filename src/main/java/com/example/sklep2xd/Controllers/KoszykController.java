package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KoszykDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.KoszykEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/koszyk")
public class KoszykController {
    private final KoszykService koszykService;
    @Autowired
    private KlientRep klientRep; // Inject KlientRep repository

    @Autowired
    private ProduktRep produktRep; // Inject ProduktRep repository

    @Autowired
    public KoszykController(KoszykService koszykService){
        this.koszykService = koszykService;
    }
    //odnieść się do *komentarza* pod konstruktorem ProduktZamowienieController

    @GetMapping("/{idKlienta}")
    public String koszykKlienta(@PathVariable("idKlienta") int idKlienta, Model model){
        List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(idKlienta);
        model.addAttribute("header", "Twój koszyk");
        model.addAttribute("Koszyk", koszyk);
        return "Koszyk";
    }
    @GetMapping("/do wymyslenia bo nwm") //ma być wywoływane na stronach z produktami
    public String dodajDoKoszyka(int idKlienta, int idProduktu, int ilosc){
        KlientEntity klient = klientRep.findByIdKlienta(idKlienta);
        ProduktEntity produkt = produktRep.findByIdProduktu(idProduktu);
        KoszykEntity koszykEntity = new KoszykEntity();
        koszykEntity.setIlosc(ilosc);
        koszykEntity.setKlient(klient);
        koszykEntity.setProdukt(produkt);
        koszykService.saveKoszyk(koszykEntity);
        return "nwm co chyba listę zakupów albo widok koszyka";
    }
    @DeleteMapping("/{idk}/{idp}")
    public String usunZKoszyka(@PathVariable("idk") int idKlienta, @PathVariable("idp") int idProduktu){
        koszykService.deleteKoszyk(idKlienta, idProduktu);
        return "Widok koszyka tutaj";
    }
    @DeleteMapping("/{idKlienta}")
    public String wyczyscKosszykKlienta(@PathVariable("idKlienta") int idKlienta){
        koszykService.deleteKoszykKlienta(idKlienta);
        return "koszyk";
    }
}
