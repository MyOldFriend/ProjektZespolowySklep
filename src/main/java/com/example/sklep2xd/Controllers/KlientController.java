package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/klienci")
public class KlientController {
    private final KlientService klientService;

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping("/lista")
    public String listKlients(Model model) {
        List<KlientDto> klienci = klientService.findAllKlients();
        model.addAttribute("header", "Lista wszystkich Klientów");
        model.addAttribute("klientList", klienci);
        return "Klienci";
    }


    @GetMapping("/{klientId}")
    public String singleKlient(@PathVariable("klientId") int klientId, Model model) {
        KlientDto klient = klientService.findKlientById(klientId);
        model.addAttribute("klient", klient);
        return "/Klient/single";
    }

    @GetMapping("/dodajform")
    public String createKlientForm(Model model) {
        KlientEntity klient = new KlientEntity();
        model.addAttribute("klient", klient);
        return "NowyKlient";
    }

    @PostMapping("/dodajform")
    public String saveKlient(@ModelAttribute("klient") KlientEntity klient) {
        klientService.saveKlient(klient);
        return "redirect:/Klient/lista";
    }

    @GetMapping("/edytuj/{klientId}")
    public String editKlientForm(@PathVariable("klientId") int klientId, Model model) {
        KlientDto klient = klientService.findKlientById(klientId);
        model.addAttribute("klient", klient);
        return "EdytujKlienta";
    }

    @PostMapping("/edytuj/{klientId}")
    public String updateKlient(@PathVariable("klientId") int klientId, @ModelAttribute("klient") KlientDto klientDto) {
        klientDto.setIdKlienta(klientId);
        klientService.updateKlient(klientDto);
        return "redirect:/Klient/lista";
    }
}