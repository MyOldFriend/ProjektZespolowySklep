package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.*;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Produkt")
public class ProduktController {

    private final ProduktService produktService;
    private final RecenzjaService recenzjaService; // Define RecenzjaService
    private final KategoriaService kategoriaService;

    private final KoszykService koszykService;
    @Autowired
    private final KlientRep klientRep;
    @Autowired
    private final ProduktRep produktRep;



    @Autowired
    public ProduktController(ProduktService produktService, RecenzjaService recenzjaService, KategoriaService kategoriaService, KoszykService koszykService, KlientRep klientRep, ProduktRep produktRep) {
        this.produktService = produktService;
        this.recenzjaService = recenzjaService; // Assign RecenzjaService
        this.kategoriaService = kategoriaService;
        this.koszykService = koszykService;
        this.klientRep = klientRep;
        this.produktRep = produktRep;
    }

    @GetMapping("/lista")
    public String listProdukty(Model model) {
        List<ProduktDto> produkty = produktService.findAllProdukty();
        model.addAttribute("header", "Lista wszystkich Produktów");
        model.addAttribute("produktList", produkty);
        //return "Produktylista";
        return "ListaProduktowPracownik";
    }

    @GetMapping("/lista2")
    public String listProduktyForKlient(Model model) {
        List<ProduktDto> produkty = produktService.findAllProdukty();
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("header", "Lista wszystkich Produktów");
        model.addAttribute("produktList", produkty);
        model.addAttribute("kategorie", kategorie);
        return "ListaProduktowKlienci";
    }

    @GetMapping("/lista2/{idKat}")
    public String listProduktyByKategoria(@PathVariable("idKat") int idKat, Model model) {
        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("produktList", produkty);
        model.addAttribute("kategorie", kategorie); // Tutaj dodajesz kategorie do modelu
        return "ListaProduktowKlienci";
    }

    @GetMapping("/lista/{idKat}")
    public String listProduktyByKategoriaPracownik(@PathVariable("idKat") int idKat, Model model) {
        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("produktList", produkty);
        model.addAttribute("kategorie", kategorie); // Tutaj dodajesz kategorie do modelu
        return "ListaProduktowPracownik";
    }




//    @GetMapping("/dodajform")
//    public String createProduktForm(Model model) {
//        ProduktEntity produkt = new ProduktEntity();
//        model.addAttribute("produkt", produkt);
//        //return "NowyProdukt";
//        return "DodajProdukt";
//    }

    @GetMapping("/dodajprodukt")
    public String dodajProduktForm(Model model, CsrfToken csrfToken) {
        ProduktEntity produkt = new ProduktEntity();
        model.addAttribute("_csrf", csrfToken);
//        model.addAttribute("produkt", produkt);
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("kategorie", kategorie);
        model.addAttribute("produkt", new ProduktDto()); // tworzymy pusty obiekt produktu, który będzie wypełniany przez formularz
        return "DodajProdukt";
    }

//    @PostMapping("/dodaj")
//    public String dodajProdukt(@ModelAttribute("produkt") ProduktEntity produktDto) {
//        List<KategoriaDto> kategoria = kategoriaService.findAllKategories();
//        produktService.saveProdukt(produktDto);
//        kategoriaService.saveKategoria();
//        return "redirect:/Produkt/lista";
//    }

    @PostMapping("/dodaj")
    public String dodajProdukt(@ModelAttribute("produkt") ProduktEntity produkt, @RequestParam("kategoriaId") int kategoriaId) {
        KategoriaDto kategoriaDto = kategoriaService.findKategoriaByIdKategori(kategoriaId);
        if (kategoriaDto != null) {
            KategoriaEntity kategoria = kategoriaService.mapToKategoriaEntity(kategoriaDto);
            produkt.setKategoriaByKategoriaId(kategoria);
            produktService.saveProdukt(produkt);
            return "redirect:/Produkt/lista";
        } else {
            return "redirect:/error";
        }
    }


//    @PostMapping("/dodajform")
//    public String saveProdukt(@ModelAttribute("produkt") ProduktEntity produkt) {
//        produktService.saveProdukt(produkt);
//        return "redirect:/Produkt/lista";
//    }

    @GetMapping("/{id}")
    public String singleProdukt(@PathVariable("id") int produktId, Model model){
        ProduktDto produkt = produktService.findProduktById(produktId);
        List<RecenzjaDto> recenzje = recenzjaService.findRecenzjeByProductId(produktId);
        model.addAttribute("produkt", produkt);
        model.addAttribute("recenzjaList", recenzje);
        return "Produkt"; // nazwa widoku szczegółów produktu
    }


    @GetMapping("/edytuj/{id}")
    public String edytujProdukt(@PathVariable int id, Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
        ProduktDto produkt = produktService.findProduktById(id);
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("kategorie", kategorie);
        model.addAttribute("produkt", produkt);
        return "EdytujProdukt";
    }

//    @PostMapping("/edytuj")
//    public String updateProdukt(@ModelAttribute("produkt") ProduktDto produktDto, @RequestParam("produktId") int produktId) {
//        produktDto.setIdProduktu(produktId);
//        produktService.updateProdukt(produktDto);
//        return "redirect:/Produkt/lista";
//    }

    @PostMapping("/edytuj")
    public String updateProdukt(@ModelAttribute("produkt") ProduktDto produktDto, @RequestParam("produktId") int produktId, @RequestParam("kategoriaId") int kategoriaId) {
        produktDto.setIdProduktu(produktId);
        KategoriaDto kategoriaDto = kategoriaService.findKategoriaByIdKategori(kategoriaId);
        if (kategoriaDto != null) {
            KategoriaEntity kategoriaEntity = kategoriaService.mapToKategoriaEntity(kategoriaDto);
            produktDto.setKategoria(kategoriaEntity);
            produktService.updateProdukt(produktDto);
            return "redirect:/Produkt/lista";
        } else {
            return "redirect:/error";
        }
    }
    @PostMapping("/dodajDoKoszyka/{idKlienta}/{idProduktu}/{ilosc}") //ma być wywoływane na stronach z produktami
    public String dodajDoKoszyka(@PathVariable("idKlienta") int idKlienta,
                                 @PathVariable("idProduktu") int idProduktu,
                                 @PathVariable("ilosc") int ilosc, Model model){
        KlientEntity klient = klientRep.findByIdKlienta(idKlienta);
        ProduktEntity produkt = produktRep.findByIdProduktu(idProduktu);
        model.addAttribute("Koszyk1",produkt);
        KoszykPK id = new KoszykPK(idKlienta, idProduktu);
        KoszykEntity koszykEntity = new KoszykEntity();
        koszykEntity.setKpk(id);
        koszykEntity.setIlosc(ilosc);
        koszykEntity.setKlient(klient);
        koszykEntity.setProdukt(produkt);
        koszykService.saveKoszyk(koszykEntity);
        return "Koszyk";
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/usun/{produktId}")
    @ResponseBody
    public void deleteProdukt(@PathVariable("produktId") int produktId) {
        produktService.removeProduktById(produktId);
    }

//    @GetMapping("/{idKat}")
//    public String podgladKategorii(@PathVariable("idKat")int idKat, Model model){
//        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
//        model.addAttribute("header", "Lista Produktów");
//        model.addAttribute("produktList", produkty.subList(0, Math.min(produkty.size(), 2))); // Ogranicz do 4 produktów
//        return "Produktylista";
//    }
}