package com.example.sklep2xd.Controllers;

import com.example.sklep2xd.Dto.LoginDto;
import com.example.sklep2xd.Dto.RegisterPrDto;
import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.RolaPracownika;
import com.example.sklep2xd.Repositories.PracownikRep;
import com.example.sklep2xd.Repositories.RolaPracownikaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Security;
import java.util.Collections;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private PracownikRep pracownikRep;
    private RolaPracownikaRep rolaPracownikaRep;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PracownikRep pracownikRep, RolaPracownikaRep rolaPracownikaRep, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.pracownikRep = pracownikRep;
        this.rolaPracownikaRep = rolaPracownikaRep;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/rejestracjaPracownika")
    public ResponseEntity<String> registgerPracownik(@RequestBody RegisterPrDto registerPrDto){
        if(pracownikRep.existsByLogin(registerPrDto.getLogin())){
            return new ResponseEntity<>("Login zajety", HttpStatus.BAD_REQUEST);
        }
        PracownikEntity pracownik = new PracownikEntity();
        pracownik.setLogin(registerPrDto.getLogin());
        pracownik.setHaslo(passwordEncoder.encode(registerPrDto.getHaslo())); //zakodowanie hasła przy rejestracji dla flexu
        RolaPracownika role = rolaPracownikaRep.findByNazwa("PRACOWNIK").get();
        pracownik.setRole(Collections.singletonList(role));
        pracownikRep.save(pracownik);

        return new ResponseEntity<>("Pracownik zarejestrowany", HttpStatus.OK);
    }
    @PostMapping("/rejestracjaAdmina")
    public ResponseEntity<String> registgerAdmin(@RequestBody RegisterPrDto registerPrDto){
        if(pracownikRep.existsByLogin(registerPrDto.getLogin())){
            return new ResponseEntity<>("Login zajety", HttpStatus.BAD_REQUEST);
        }
        PracownikEntity pracownik = new PracownikEntity();
        pracownik.setLogin(registerPrDto.getLogin());
        pracownik.setHaslo(passwordEncoder.encode(registerPrDto.getHaslo())); //zakodowanie hasła przy rejestracji dla flexu
        RolaPracownika role = rolaPracownikaRep.findByNazwa("Admin").get();
        pracownik.setRole(Collections.singletonList(role));
        pracownikRep.save(pracownik);

        return new ResponseEntity<>("Pracownik zarejestrowany", HttpStatus.OK);
    }

    @PostMapping("/logowaniePracownik")
    public ResponseEntity<String> pracownikLogin(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getHaslo()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Zalogowano pomyślnie", HttpStatus.OK);
    }
}
