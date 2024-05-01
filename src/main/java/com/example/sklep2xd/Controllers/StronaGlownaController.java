package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class StronaGlownaController {


    private final ProduktService produktService;
    @Autowired
    public StronaGlownaController(ProduktService produktService) {
        this.produktService = produktService;
    }

    @GetMapping
    public String homepage(){
        return "StronaGlowna";
    }

    @GetMapping("/{idKat}") //wywołać na stronie głównej dla kilku kategorii
    public String podgladKategorii(@PathVariable("idKat")int idKat, Model model){
        List<ProduktDto> produkty = produktService.findProdukyByKategoria_KategoriaId(idKat);
        model.addAttribute("header", "Lista Produktów");
        model.addAttribute("produktList", produkty);
        return "miniLista"; //trza dorobić mały widok html do wyświetlenia w iframe - taką tabelę małą
    }
}
