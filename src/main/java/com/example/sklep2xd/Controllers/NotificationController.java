package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String showSendNotificationForm() {
        return "email"; // Nazwa widoku formularza (sendNotificationForm.html)
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam("email") String email,
                                   @RequestParam("subject") String subject,
                                   @RequestParam("message") String message,
                                   Model model) {
        emailService.sendSimpleMessage(email, subject, message);
        model.addAttribute("message", "Notification sent successfully");
        return "emailwynik"; // Nazwa widoku wyniku (notificationResult.html)
    }
}
