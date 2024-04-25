package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.AdresDto;
import com.example.sklep2xd.Models.AdresEntity;
import com.example.sklep2xd.Service.AdresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Adres")
public class AdresController {
//Git test

    private final AdresService adresService;

    @Autowired
    public AdresController(AdresService adresService){
        this.adresService = adresService;
    }




    @GetMapping("/lista")
    public String listAddress(Model model) {
        List<AdresDto> adresy = adresService.findallAdress();
        model.addAttribute("header", "Lista wszystkich Adresów");
        model.addAttribute("adresList", adresy);
        return "Adresy";
    }

    @GetMapping("/dodajform")
    public String createAdresForm(Model model){
        AdresEntity adres= new AdresEntity();
        model.addAttribute("adres", adres);
        return "Adresy/NowyAdres";
    } //wyświetlenie formularza dodania

    @PostMapping("/dodajform")
    public String saveAdres(@ModelAttribute("adres") AdresEntity adres) {
        adresService.saveAdres(adres);
        return "Adresy/NowyAdres"; //zrobiłem nowy katalog na strony do edycji i dodawania adresów żeby templates nie zaśmiecać
    }

    @GetMapping("/edytuj/{adresId}")
    public String editAdresForm(@PathVariable("adresId") int adresId, Model model){
        AdresDto adres = adresService.findAdresByIdAdresu(adresId); //w tutorialu tu daje jeszcze .get() ale to chyba nie ma znaczenia
        model.addAttribute("adres", adres);
        return "Adresy/EdytujAdres";
    }
    @PostMapping("/edytuj/{adresId}")
    public String updateAdres(@PathVariable("adresId") int adresId, @ModelAttribute("adres") AdresDto adresDto){
        adresDto.setIdAdresu(adresId);
        adresService.updateAdres(adresDto);
        return "redirect:/Adres/lista";
    }
//@GetMapping("/id")
//public ResponseEntity<AdresDto> listAddress(@PathVariable("id") int adres ){
//    AdresDto adresDto = adresService.findallAdress(i)
//    return ResponseEntity.ok(adresDto);

}
