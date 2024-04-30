package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.RejestracjaDto;
import com.example.sklep2xd.Models.UserEntity;
import com.example.sklep2xd.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/rejestracja")
    public String getRegisterForm(Model model){
        RejestracjaDto user = new RejestracjaDto();
        model.addAttribute("user", user);
        return "Rejestracja";


    }

    @PostMapping("/rejestracja/save")
    public String register(@Validated @ModelAttribute("user")RejestracjaDto user,
                           BindingResult result, Model model){

        UserEntity existingUserLogin = userService.findByLogin(user.getLogin());
        if (existingUserLogin != null && existingUserLogin.getLogin() != null && !existingUserLogin.getLogin().isEmpty()){
            result.rejectValue("login"," Isnieje już klient pod tym loginem");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "rejestracja";
        }
        userService.saveUser(user);
        return "redirect:/logowanie albo cos innego???";

    }




}
