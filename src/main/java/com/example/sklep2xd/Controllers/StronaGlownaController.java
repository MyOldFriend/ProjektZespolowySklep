package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.ProduktDto;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class StronaGlownaController {

    private final ProduktService produktService;
    private final KlientService klientService;

    @Autowired
    public StronaGlownaController(ProduktService produktService, KlientService klientService) {
        this.produktService = produktService;
        this.klientService = klientService;
    }

    @GetMapping
    public String homepage(Model model) {
        List<ProduktDto> koszulki = produktService.findProdukyByKategoria_KategoriaId(1);
        List<ProduktDto> spodnie = produktService.findProdukyByKategoria_KategoriaId(2);
        List<ProduktDto> bluzy = produktService.findProdukyByKategoria_KategoriaId(4);
        List<ProduktDto> kurtki = produktService.findProdukyByKategoria_KategoriaId(3);

        model.addAttribute("koszulki", koszulki.subList(0, Math.min(koszulki.size(), 5)));
        model.addAttribute("spodnie", spodnie.subList(0, Math.min(spodnie.size(), 5)));
        model.addAttribute("bluzy", bluzy.subList(0, Math.min(bluzy.size(), 5)));
        model.addAttribute("kurtki", kurtki.subList(0, Math.min(kurtki.size(), 5)));

        return "StronaGlowna";
    }
}