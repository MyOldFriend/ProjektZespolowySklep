package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.PracownikDto;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.Role;
import com.example.sklep2xd.Service.PracownikService;
import com.example.sklep2xd.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/Pracownik")
public class PracownikController {
    private final PracownikService pracownikService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PracownikController(PracownikService pracownikService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.pracownikService = pracownikService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("dzialzamowien")
    public String homeEmployePage() {
        return "DzialZamowien";
    }

    @GetMapping("administrator")
    public String homeAdminPage() {
        return "StronaAdmin";
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
        model.addAttribute("roles", roleService.findAllRoles());
        return "NowyPracownik";
    }

    @PostMapping("/dodajform")
    public String savePracownik(@ModelAttribute("pracownik") PracownikEntity pracownik, @RequestParam("role") int roleId) {
        String pass = pracownik.getHaslo();
        pass = passwordEncoder.encode(pass);
        pracownik.setHaslo(pass);

        Role role = roleService.findRoleById(roleId);
        pracownik.setRoles(Collections.singletonList(role));

        pracownikService.savePracownik(pracownik);
        return "redirect:/Pracownik/lista";
    }

    @GetMapping("/edytuj/{pracownikId}")
    public String editPracownikForm(@PathVariable("pracownikId") int pracownikId, Model model) {
        PracownikDto pracownik = pracownikService.findPracownikById(pracownikId);
        model.addAttribute("pracownikEdit", pracownik);
        model.addAttribute("roles", roleService.findAllRoles());
        return "EdytujDanePracownika";
    }

//    @PostMapping("/edytuj/{pracownikId}")
//    public String updatePracownik(@PathVariable("pracownikId") int pracownikId, @ModelAttribute("pracownik") PracownikDto pracownikDto, @RequestParam("role") int roleId) {
//        if (pracownikDto.getHaslo() != null && !pracownikDto.getHaslo().isEmpty()) {
//            String pass = passwordEncoder.encode(pracownikDto.getHaslo());
//            pracownikDto.setHaslo(pass);
//        }
//
//        PracownikEntity pracownik = pracownikService.mapToPracownikEntity(pracownikDto);
//        Role role = roleService.findRoleById(roleId);
//        pracownik.setRoles(Collections.singletonList(role));
//
//        pracownik.setIdPracownika(pracownikId);
//        pracownikService.updatePracownik(pracownikDto);
//        return "redirect:/Pracownik/lista";
//    }

    @PostMapping("/edytuj/{pracownikId}")
    public String updatePracownik(@PathVariable("pracownikId") int pracownikId,
                                  @ModelAttribute("pracownikEdit") PracownikDto pracownikDto,
                                  @RequestParam("role") int roleId) {
        pracownikDto.setIdPracownika(pracownikId);

        if (pracownikDto.getHaslo() != null && !pracownikDto.getHaslo().isEmpty()) {
            String pass = passwordEncoder.encode(pracownikDto.getHaslo());
            pracownikDto.setHaslo(pass);
        }

        PracownikEntity pracownik = pracownikService.mapToPracownikEntity(pracownikDto);
        Role role = roleService.findRoleById(roleId);
        pracownik.setRoles(Collections.singletonList(role));

        pracownikService.updatePracownik(pracownik);
        return "redirect:/Pracownik/lista";
    }


    @DeleteMapping("/usun/{pracownikId}")
    public String deletePracownik(@PathVariable("pracownikId") int pracownikId) {
        pracownikService.removePracownikById(pracownikId);
        return "redirect:/Pracownik/lista";
    }
}