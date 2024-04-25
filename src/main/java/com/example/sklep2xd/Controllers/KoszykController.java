package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Service.KoszykService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/koszyk")
public class KoszykController {
    private final KoszykService koszykService;
    @Autowired
    public KoszykController(KoszykService koszykService){
        this.koszykService = koszykService;
    }
    //odnieść się do *komentarza* pod konstruktorem ProduktZamowienieController
}
