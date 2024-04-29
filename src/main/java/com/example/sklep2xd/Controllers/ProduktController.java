package com.example.sklep2xd.Controllers;
import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Dto.RozmiaryDto;
import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.PracownikService;
import com.example.sklep2xd.Service.ProduktService;
import com.example.sklep2xd.Service.RozmiaryService;
import com.example.sklep2xd.Service.RecenzjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Produkt")
public class ProduktController {




    @Autowired
    private ProduktService produktService;

    @Autowired
    private RozmiaryService rozmiaryService;
    private final RecenzjaService recenzjaService;



    @Autowired
    public ProduktController(ProduktService produktService, RecenzjaService recenzjaService) {
        this.produktService = produktService;
        this.recenzjaService = recenzjaService; // Assign RecenzjaService
    }

    @GetMapping("/lista")
    public String listProdukty(Model model) {
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


    @GetMapping("/dodajform")
    public String createProduktForm(Model model) {
        ProduktEntity produkt = new ProduktEntity();
        model.addAttribute("produkt", produkt);
        return "NowyProdukt";
    }

    @PostMapping("/dodajform")
    public String saveProdukt(@ModelAttribute("produkt") ProduktEntity produkt) {
        produktService.saveProdukt(produkt);
        return "redirect:/Produkt/lista";
    }

    @GetMapping("/{id}")
    public String singleProdukt(@PathVariable("id") int produktId,Model model){
        ProduktDto produkt = produktService.findProduktById(produktId);
        List<RozmiaryDto> rozmiary = rozmiaryService.findByProduktuId(produktId);
        List<RecenzjaDto> recenzje = recenzjaService.findRecenzjeByProductId(produktId);
        model.addAttribute("produkt", produkt);
        model.addAttribute("rozmiary", rozmiary);
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
}