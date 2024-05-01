package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.LogowanieForm;
import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.PracownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogowanieController {

    @Autowired
    private KlientService klientService;

    @Autowired
    private PracownikService pracownikService;

    @GetMapping("/logowanie")
    public String wyswietlFormularzLogowania(Model model) {
        model.addAttribute("logowanieForm", new LogowanieForm());
        return "Logowanie";
    }

    @PostMapping("/logowanie")
    public String zalogujUzytkownika(@ModelAttribute("logowanieForm") LogowanieForm form, Model model) {
        String login = form.getLogin();
        String haslo = form.getHaslo();
        String rodzajUzytkownika = form.getRodzajUzytkownika();

        if (rodzajUzytkownika.equals("klient")) {
            if (klientService.zalogujKlienta(login, haslo)) {
                return "redirect:/panelKlienta";
            } else {
                model.addAttribute("errorMessage", "Niepoprawny login lub hasło dla klienta");
                return "Logowanie";
            }
        } else if (rodzajUzytkownika.equals("pracownik")) {
            PracownikDto pracownik = pracownikService.zalogujPracownika(login, haslo);
            if (pracownik != null) {
                if (pracownikService.czyAdmin(pracownik)) {
                    return "redirect:/admin";
                } else {
                    return "redirect:/employee";
                }
            } else {
                model.addAttribute("errorMessage", "Niepoprawny login lub hasło dla pracownika");
                return "Logowanie";
            }
        } else {
            model.addAttribute("errorMessage", "Niepoprawny rodzaj użytkownika");
            return "Logowanie";
        }
    }
}
