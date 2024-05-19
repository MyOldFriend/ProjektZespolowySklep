package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.RecenzjaDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Models.ProduktEntity;
import com.example.sklep2xd.Models.RecenzjaEntity;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Repositories.ProduktRep;
import com.example.sklep2xd.Service.RecenzjaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Recenzja")
public class RecenzjaController {

    private final RecenzjaService recenzjaService;

    @Autowired
    private KlientRep klientRep;

    @Autowired
    private ProduktRep produktRep;

    @Autowired
    public RecenzjaController(RecenzjaService recenzjaService) {
        this.recenzjaService = recenzjaService;
    }

    @GetMapping("/lista")
    public String listRecenzje(Model model) {
        List<RecenzjaDto> recenzje = recenzjaService.findAllRecenzje();
        model.addAttribute("header", "Lista wszystkich Recenzji");
        model.addAttribute("recenzjaList", recenzje);
        return "ZarzadzanieRecenzjami";
    }

    @GetMapping("/dodajform")
    public String createRecenzjaForm(Model model) {
        RecenzjaDto recenzja = new RecenzjaDto();
        model.addAttribute("recenzja", recenzja);
        return "NowaRecenzja";
    }

    @PostMapping("/dodajform")
    public String saveRecenzja(@ModelAttribute("recenzja") RecenzjaDto recenzjaDto,
                               @RequestParam("review") String review,
                               @RequestParam("rating") int rating,
                               HttpSession session,
                               @RequestParam("idProd") int idProd) {
        KlientEntity klient = klientRep.findByIdKlienta((int) session.getAttribute("klientId"));
        ProduktEntity produkt = produktRep.findByIdProduktu(idProd);

        RecenzjaEntity recenzja = new RecenzjaEntity();
        recenzja.setTresc(review);
        recenzja.setOcena(rating);
        recenzja.setKlientByKlientId(klient);
        recenzja.setProduktByProduktId(produkt);
        recenzjaService.saveRecenzja(recenzja);

        return "redirect:/Produkt/" + idProd;
    }

    @GetMapping("/edytuj/{recenzjaId}")
    public String editRecenzjaForm(@PathVariable("recenzjaId") int recenzjaId, Model model) {
        RecenzjaDto recenzja = recenzjaService.findRecenzjaById(recenzjaId);
        model.addAttribute("recenzja", recenzja);
        return "EdytujRecenzje";
    }

    @GetMapping("/lista/{idProduktu}")
    public String listRecenzjeForProduct(@PathVariable("idProduktu") int idProduktu, Model model) {
        List<RecenzjaDto> recenzje = recenzjaService.findRecenzjeByProductId(idProduktu);
        model.addAttribute("header", "Lista recenzji dla produktu");
        model.addAttribute("recenzjaList", recenzje);
        return "Recenzje";
    }

    @PostMapping("/edytuj/{recenzjaId}")
    public String updateRecenzja(@PathVariable("recenzjaId") int recenzjaId, @ModelAttribute("recenzja") RecenzjaDto recenzjaDto) {
        recenzjaDto.setIdRecenzji(recenzjaId);
        recenzjaService.updateRecenzja(recenzjaDto);
        return "redirect:/Recenzja/lista";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/usun/{recenzjaId}")
    @ResponseBody
    public String deleteRecenzja(@PathVariable("recenzjaId") int recenzjaId) {
        recenzjaService.removeRecenzja(recenzjaId);
        return "Recenzja usunięta";
    }
}
