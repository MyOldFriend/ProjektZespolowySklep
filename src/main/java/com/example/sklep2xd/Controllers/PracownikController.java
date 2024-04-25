package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Service.KlientService;
import com.example.sklep2xd.Service.PracownikService;
import com.example.sklep2xd.Service.ProduktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/Pracownik")
public class PracownikController {
    private PracownikService pracownikService;

    @Autowired
    public PracownikController(PracownikService pracownikService){
        this.pracownikService = pracownikService;
    }



    @GetMapping("/lista")
    public String listPracownicy(Model model) {
        List<PracownikDto> pracownicy = pracownikService.findAllPracownicy();
        model.addAttribute("header", "Lista wszystkich Pracowników");
        model.addAttribute("pracownikList", pracownicy);
        return "ListaPracownikow";
    }

    @GetMapping("/dodajform")
    public String createPracownikForm(Model model) {
        PracownikEntity pracownik = new PracownikEntity();
        model.addAttribute("pracownik", pracownik);
        return "NowyPracownik";
    }

    @PostMapping("/dodajform")
    public String savePracownik(@ModelAttribute("pracownik") PracownikEntity pracownik) {
        pracownikService.savePracownik(pracownik);
        return "redirect:/Pracownik/lista";
    }

    @GetMapping("/edytuj/{pracownikId}")
    public String editPracownikForm(@PathVariable("pracownikId") int pracownikId, Model model) {
        PracownikDto pracownik = pracownikService.findPracownikById(pracownikId);
        model.addAttribute("pracownikEdit", pracownik);
        return "EdytujDanePracownika";
    }

    @PostMapping("/edytuj/{pracownikId}")
    public String updatePracownik(@PathVariable("pracownikId") int pracownikId, @ModelAttribute("pracownik") PracownikDto pracownikDto) {
        pracownikDto.setIdPracownika(pracownikId);
        pracownikService.updatePracownik(pracownikDto);
        return "redirect:/Pracownik/lista";
    }
}
