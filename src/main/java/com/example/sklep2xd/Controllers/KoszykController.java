package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KoszykDto;
import com.example.sklep2xd.Service.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/koszyk")
public class KoszykController {
    private final KoszykService koszykService;
    @Autowired
    public KoszykController(KoszykService koszykService){
        this.koszykService = koszykService;
    }
    //odnieść się do *komentarza* pod konstruktorem ProduktZamowienieController

    @GetMapping("/{idKlienta}")
    public String koszykKlienta(@PathVariable("idKlienta") int idKlienta, Model model){
        List<KoszykDto> koszyk = koszykService.findKoszykByKlientId(idKlienta);
        model.addAttribute("header", "Twój koszyk");
        model.addAttribute("Koszyk", koszyk);
        return "koszyk";
    }
}
