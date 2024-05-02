package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Service.AdresService;
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
    private final AdresService adresService;

    @Autowired
    public KlientController(KlientService klientService, AdresService adresService) {
        this.klientService = klientService;
        this.adresService = adresService;
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
        return "Rejestracja";
    }

    @PostMapping("/dodajform")
    public String saveKlient(@ModelAttribute("klient") KlientEntity klient, Model model) {
        // Sprawdzenie czy istnieje użytkownik o podanym e-mailu
        KlientEntity existingEmailUser = klientService.findKlientByEmail(klient.getEmail());
        if (existingEmailUser != null) {
            model.addAttribute("errorMessage", "Użytkownik z podanym adresem e-mail już istnieje.");
            return "Rejestracja";
        }

        // Sprawdzenie czy istnieje użytkownik o podanym loginie
        KlientEntity existingLoginUser = klientService.findKlientByLogin(klient.getLogin());
        if (existingLoginUser != null) {
            model.addAttribute("errorMessage", "Użytkownik z podanym loginem już istnieje.");
            return "Rejestracja";
        }

        // Jeśli nie istnieje, zapisujemy nowego użytkownika
        klientService.saveKlient(klient);
        return "redirect:/klienci/lista";
    }


    @GetMapping("/edytuj/{klientId}")
    public String editKlientForm(@PathVariable("klientId") int klientId, Model model) {
        KlientDto klient = klientService.findKlientById(klientId);
        model.addAttribute("klient", klient);
//        return "EdytujKlienta";
        return "TwojeKonto";
    }

    @PostMapping("/edytuj/{klientId}")
    public String updateKlient(@PathVariable("klientId") int klientId, @ModelAttribute("klient") KlientDto klientDto) {
        klientDto.setIdKlienta(klientId);
        klientService.updateKlient(klientDto);
        return "redirect:/Pracownik/lista";
    }
}