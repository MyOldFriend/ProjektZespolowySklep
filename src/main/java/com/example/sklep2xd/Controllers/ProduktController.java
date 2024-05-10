package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.*;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "ListaProduktow";
    }

    @GetMapping("/lista2")
    public String listProduktyForKlient(Model model) {
        List<ProduktDto> produkty = produktService.findAllProdukty();
        model.addAttribute("header", "Lista wszystkich Produktów");
        model.addAttribute("produktList", produkty);
        return "Produktylista";
    }

    @GetMapping("/lista/{idKat}")
    public String listProdukty(@PathVariable("idKat") int idKat, Model model) {
        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
        model.addAttribute("header", "Lista Produktów");
        model.addAttribute("produktList", produkty);
        return "Produkty";
    }


//    @GetMapping("/dodajform")
//    public String createProduktForm(Model model) {
//        ProduktEntity produkt = new ProduktEntity();
//        model.addAttribute("produkt", produkt);
//        //return "NowyProdukt";
//        return "DodajProdukt";
//    }

    @GetMapping("/dodajform")
    public String dodajProduktForm(Model model) {
        ProduktEntity produkt = new ProduktEntity();
//        model.addAttribute("produkt", produkt);
        List<KategoriaDto> kategorie = kategoriaService.findAllKategories();
        model.addAttribute("kategorie", kategorie);
        model.addAttribute("produkt", new ProduktDto()); // tworzymy pusty obiekt produktu, który będzie wypełniany przez formularz
        return "DodajProdukt";
    }

    @PostMapping("/dodaj")
    public String dodajProdukt(@ModelAttribute("produkt") ProduktEntity produktDto) {
        produktService.saveProdukt(produktDto);
        return "redirect:/Produkt/lista";
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
        return "Produkt";
    }


    @GetMapping("/edytuj/{produktId}")
    public String editProduktForm(@PathVariable("produktId") int produktId, Model model) {
        ProduktDto produkt = produktService.findProduktById(produktId);
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


    @DeleteMapping("/usun/{produktId}")
    @ResponseBody
    public void deleteProdukt(@PathVariable("produktId") int produktId) {
        produktService.removeProduktById(produktId);
    }

    @GetMapping("/{idKat}")
    public String podgladKategorii(@PathVariable("idKat")int idKat, Model model){
        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
        model.addAttribute("header", "Lista Produktów");
        model.addAttribute("produktList", produkty.subList(0, Math.min(produkty.size(), 2))); // Ogranicz do 4 produktów
        return "Produktylista";
    }
}