package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KoszykDto;
import com.example.sklep2xd.Models.*;
import com.example.sklep2xd.Repositories.*;
import com.example.sklep2xd.Service.KoszykService;
import com.example.sklep2xd.Service.ProduktZamowienieService;
import com.example.sklep2xd.Service.ZamowienieService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/koszyk")
public class KoszykController {
    private final KoszykService koszykService;
    private final ZamowienieService zamowienieService;
    private final ProduktZamowienieService produktZamowienieService; //te dwa potrzeben do tworzenia zamowienia na podstawie koszyka
    @Autowired
    private KlientRep klientRep; // Inject KlientRep repository

    @Autowired
    private ProduktRep produktRep; // Inject ProduktRep repository
    @Autowired
    private ZamowienieRep zamowienieRep;
    @Autowired
    private AdresRep adresRep;
    @Autowired
    private ProduktZamowienieRep produktZamowienieRep;

    @Autowired
    public KoszykController(KoszykService koszykService, ZamowienieService zamowienieService, ProduktZamowienieService produktZamowienieService){
        this.koszykService = koszykService;
        this.zamowienieService = zamowienieService;
        this.produktZamowienieService = produktZamowienieService;
    }
    //odnieść się do *komentarza* pod konstruktorem ProduktZamowienieController

//    @GetMapping("/{idKlienta}")
//    public String koszykKlienta(@PathVariable("idKlienta") int idKlienta, Model model){
//        List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(idKlienta);
//        model.addAttribute("header", "Twój koszyk");
//        model.addAttribute("Koszyk", koszyk);
//        return "Koszyk";
//    }
        @GetMapping()
        public String koszykKlienta(HttpSession session, Model model){
            List<KoszykDto> koszyk = koszykService.findKoszykByKlientId((int) session.getAttribute("klientId"));
            double sumaCen = KoszykService.obliczCeneKoszyka(koszyk);
            model.addAttribute("header", "Twój koszyk");
            model.addAttribute("Koszyk", koszyk);
            model.addAttribute("sumaCen", sumaCen);
            return "Koszyk";
        }

    @PostMapping("/dodajDoKoszyka/{idProduktu}/{ilosc}")
    public String dodajDoKoszyka(HttpSession session,
                                 @PathVariable("idProduktu") int idProduktu,
                                 @PathVariable("ilosc") int ilosc, Model model) {
        Integer klientId = (Integer) session.getAttribute("klientId");
        if (klientId == null) {
            return "redirect:/logowanie"; // Redirect to login if klientId is not found in the session
        }
        KlientEntity klient = klientRep.findByIdKlienta(klientId);
        ProduktEntity produkt = produktRep.findByIdProduktu(idProduktu);
        model.addAttribute("Koszyk1", produkt);
        KoszykPK id = new KoszykPK(klientId, idProduktu);
        KoszykEntity koszykEntity = new KoszykEntity();
        koszykEntity.setKpk(id);
        koszykEntity.setIlosc(ilosc);
        koszykEntity.setKlient(klient);
        koszykEntity.setProdukt(produkt);
        koszykService.saveKoszyk(koszykEntity);
        return "redirect:/koszyk";
    }

//
//    @PostMapping("/dodajDoKoszyka")
//    public String dodajDoKoszyka(HttpSession session,
//                                 @RequestParam("idProduktu") int idProduktu,
//                                 Model model) {
//        // Tutaj dodaj logikę dodawania produktu do koszyka
//        KlientEntity klient = klientRep.findByIdKlienta((int) session.getAttribute("userId"));
//        ProduktEntity produkt = produktRep.findByIdProduktu(idProduktu);
//        //jak dodać ilość?
//        return "redirect:/lista-produktow"; // Przekierowanie do listy produktów lub innego widoku
//    }


    @PostMapping("/usun/{idp}")
    public String usunZKoszyka(HttpSession session, @PathVariable("idp") int idProduktu, RedirectAttributes redirectAttributes) {
        int idKlienta = (int) session.getAttribute("klientId");
        koszykService.deleteKoszyk(idKlienta, idProduktu);
        redirectAttributes.addFlashAttribute("successMessage", "Produkt został usunięty z koszyka.");
        return "redirect:/koszyk";
    }

    @DeleteMapping("/wyczyscKoszyk")
    public String wyczyscKosszykKlienta(HttpSession session){
        int idKlienta = (int) session.getAttribute("klientId");
        koszykService.deleteKoszykKlienta(idKlienta);
        return "redirect:/koszyk";
    }
    @PostMapping("/zlozzamowienie")
    @Transactional
    public String zlozZamowienie(HttpSession session, Model model){
        Integer klientId = (Integer) session.getAttribute("klientId");
        if (klientId == null) {
            return "redirect:/logowanie"; // Redirect to login if klientId is not found in the session
        }
        KlientEntity klient = klientRep.findByIdKlienta(klientId);
        ZamowienieEntity zamowienie = new ZamowienieEntity();
        zamowienie.setCzyZaplacone(false);
        zamowienie.setStatus("Złożone");
        zamowienie.setKlientByKlientId(klientRep.findByIdKlienta(klientId));

        // Check if the klient has an address
        AdresEntity adresZamowienia = null;
        if (klient.getAdresId() != null) {
            AdresEntity adresKlienta = adresRep.findByIdAdresu(klient.getAdresId().getIdAdresu());
            adresZamowienia = new AdresEntity();
            adresZamowienia.setKraj(adresKlienta.getKraj());
            adresZamowienia.setMiejscowosc(adresKlienta.getMiejscowosc());
            adresZamowienia.setKodPocztowy(adresKlienta.getKodPocztowy());
            adresZamowienia.setUlica(adresKlienta.getUlica());
            adresZamowienia.setNrDomu(adresKlienta.getNrDomu());
            if (adresKlienta.getNrMieszkania() != null) {
                adresZamowienia.setNrMieszkania(adresKlienta.getNrMieszkania());
            }
            adresRep.save(adresZamowienia);
        }

        // Assign address to order if it exists
        if (adresZamowienia != null) {
            zamowienie.setAdresByAdresId(adresZamowienia);
        }
        zamowienieRep.save(zamowienie);

        // Fill ProduktZamowienie with cart records for the client
        List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(klientId);
        for (KoszykDto koszykDto : koszyk) {
            ProduktZamowienieEntity produktZamowienie = new ProduktZamowienieEntity();
            ProduktZamowieniePK id = new ProduktZamowieniePK(zamowienie.getIdZamowienia(), koszykDto.getProdukt().getIdProduktu());
            produktZamowienie.setIdpz(id);
            produktZamowienie.setProduktByProduktId(koszykDto.getProdukt());
            produktZamowienie.setZamowienieByZamowienieId(zamowienie);
            produktZamowienie.setIlosc(koszykDto.getIlosc());
            produktZamowienieRep.save(produktZamowienie);
        }

        model.addAttribute("klient", klient);
        model.addAttribute("adres", adresZamowienia);
        koszykService.deleteKoszykKlienta(klientId);
        return "DaneDostawy"; // Return to the appropriate view
    }
}
