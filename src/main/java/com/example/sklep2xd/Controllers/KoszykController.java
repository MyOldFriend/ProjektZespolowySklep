package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KoszykDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.KoszykEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//    @GetMapping("/{idKlienta}")
//    public String koszykKlienta(@PathVariable("idKlienta") int idKlienta, Model model){
//        List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(idKlienta);
//        model.addAttribute("header", "Twój koszyk");
//        model.addAttribute("Koszyk", koszyk);
//        return "Koszyk";
//    }
        @GetMapping("/{idKlienta}")
        public String koszykKlienta(@PathVariable("idKlienta") int idKlienta, Model model){
            List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(idKlienta);
            double sumaCen = KoszykService.obliczCeneKoszyka(koszyk);
            model.addAttribute("header", "Twój koszyk");
            model.addAttribute("Koszyk", koszyk);
            model.addAttribute("sumaCen", sumaCen);
            return "Koszyk";
        }

    @PostMapping("/do wymyslenia bo nwm") //ma być wywoływane na stronach z produktami
    public String dodajDoKoszyka(int idKlienta, int idProduktu, int ilosc, Model model){
        KlientEntity klient = klientRep.findByIdKlienta(idKlienta);
        ProduktEntity produkt = produktRep.findByIdProduktu(idProduktu);
        model.addAttribute("Koszyk1",produkt);
        KoszykEntity koszykEntity = new KoszykEntity();
        koszykEntity.setIlosc(ilosc);
        koszykEntity.setKlient(klient);
        koszykEntity.setProdukt(produkt);
        koszykService.saveKoszyk(koszykEntity);
        return "nwm co chyba listę zakupów albo widok koszyka";
    }
    @PostMapping("/usun/{idk}/{idp}")
    public String usunZKoszyka(@PathVariable("idk") int idKlienta, @PathVariable("idp") int idProduktu, RedirectAttributes redirectAttributes) {
        koszykService.deleteKoszyk(idKlienta, idProduktu);
        redirectAttributes.addFlashAttribute("successMessage", "Produkt został usunięty z koszyka.");
        return "redirect:/koszyk/" + idKlienta;
    }

    @DeleteMapping("/{idKlienta}")
    public String wyczyscKosszykKlienta(@PathVariable("idKlienta") int idKlienta){
        koszykService.deleteKoszykKlienta(idKlienta);
        return "koszyk";
    }

}
