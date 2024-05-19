package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sendNewsletter")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    @ResponseBody
    public String sendNewsletterNotification() {
        try {
            String email = "fashion.forward2137@gmail.com";
            String subject = "Newsletter Subscription";
            String message = "Dziękujemy za zapisanie się do NEWSLETTERA";

            emailService.sendSimpleMessage(email, subject, message);
            return "Dziekujemy za zapisanie się do NEWSLETTERA";
        } catch (Exception e) {
            e.printStackTrace(); // Wyświetlenie błędu w konsoli
            return "Błąd podczas wysyłania powiadomienia: " + e.getMessage();
        }
    }
}
