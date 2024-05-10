package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.LogowanieForm;
import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.PracownikService;
import com.example.sklep2xd.ssecurity.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
    public String zalogujUzytkownika(@ModelAttribute("logowanieForm") LogowanieForm form, Model model, HttpServletResponse response) {
        String login = form.getLogin();
        String haslo = form.getHaslo();
        String rodzajUzytkownika = form.getRodzajUzytkownika();

        if (rodzajUzytkownika.equals("klient")) {
            if (klientService.zalogujKlienta(login, haslo)) {
                // Generate JWT for client
                String token = JwtUtil.generateToken(login, klientService.findKlientByLogin(login).getIdKlienta());
                response.addHeader("Authorization", "Bearer " + token);
                return "redirect:/home";
            } else {
                model.addAttribute("errorMessage", "Niepoprawny login lub hasło dla klienta");
                return "Logowanie";
            }
        } else if (rodzajUzytkownika.equals("pracownik")) {
            PracownikDto pracownik = pracownikService.zalogujPracownika(login, haslo);
            if (pracownik != null) {
                // Generate JWT for employee
                String token = JwtUtil.generateToken(login, pracownikService.findPracownikByLogin(login).getIdPracownika());
                response.addHeader("Authorization", "Bearer " + token);
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
