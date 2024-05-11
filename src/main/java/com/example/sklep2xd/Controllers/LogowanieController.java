package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.LogowanieForm;
import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.PracownikService;
import com.example.sklep2xd.ssecurity.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> zalogujUzytkownika(@ModelAttribute("logowanieForm") LogowanieForm form) {
        String login = form.getLogin();
        String haslo = form.getHaslo();

        // Attempt to authenticate as Klient
        KlientDto klient = klientService.zalogujKlienta(login, haslo);
        if (klient != null) {
            String token = JwtUtil.generateToken(login, "ROLE_KLIENT", klient.getIdKlienta());
            ResponseCookie jwtCookie = createJwtCookie(token);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body("Logged in successfully");
        }

        // Attempt to authenticate as Pracownik
        PracownikDto pracownik = pracownikService.zalogujPracownika(login, haslo);
        if (pracownik != null) {
            String token = JwtUtil.generateToken(login, "ROLE_PRACOWNIK", pracownik.getIdPracownika());
            ResponseCookie jwtCookie = createJwtCookie(token);
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .body("Logged in successfully");
        }

        // If authentication fails
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Niepoprawny login lub hasło");
    }

    private ResponseCookie createJwtCookie(String token) {
        return ResponseCookie.from("AUTH-TOKEN", token)
                .httpOnly(true) // not accessible via JavaScript
                .secure(true)  // send only over HTTPS
                .path("/")     // available to entire domain
                .maxAge(7 * 24 * 60 * 60) // max age in seconds, 7 days here
                .sameSite("Strict")  // strict same site policy
                .build();
    }



}
