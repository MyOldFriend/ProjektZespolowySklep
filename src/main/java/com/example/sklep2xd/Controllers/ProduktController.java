package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KategoriaDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.KategoriaEntity;
import com.example.sklep2xd.Models.ProduktEntity;
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


    @Autowired
    public ProduktController(ProduktService produktService, RecenzjaService recenzjaService, KategoriaService kategoriaService) {
        this.produktService = produktService;
        this.recenzjaService = recenzjaService; // Assign RecenzjaService
        this.kategoriaService = kategoriaService;
    }

    @GetMapping("/lista")
    public String listProdukty(Model model) {
        List<ProduktDto> produkty = produktService.findAllProdukty();
        model.addAttribute("header", "Lista wszystkich Produktów");
        model.addAttribute("produktList", produkty);
        //return "Produktylista";
        return "ListaProduktow";
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
        model.addAttribute("produkt", produkt);
        return "EdytujProdukt";
    }

    @PostMapping("/edytuj/{produktId}")
    public String updateProdukt(@PathVariable("produktId") int produktId, @ModelAttribute("produkt") ProduktDto produktDto) {
        produktDto.setIdProduktu(produktId);
        produktService.updateProdukt(produktDto);
        return "redirect:/Produkt/lista";
    }

    @DeleteMapping("/usun/{produktId}")
    @ResponseBody
    public void deleteProdukt(@PathVariable("produktId") int produktId) {
        produktService.removeProduktById(produktId);
    }

}