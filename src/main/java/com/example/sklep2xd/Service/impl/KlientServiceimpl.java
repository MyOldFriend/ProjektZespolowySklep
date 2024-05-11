package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.KlientDto;
import com.example.sklep2xd.Models.KlientEntity;
import com.example.sklep2xd.Repositories.AdresRep;
import com.example.sklep2xd.Repositories.KlientRep;
import com.example.sklep2xd.Service.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KlientServiceimpl implements KlientService, UserDetailsService {

    private KlientRep klientRep;
    private AdresRep adresRep;

    @Autowired
    public KlientServiceimpl(KlientRep klientRep, AdresRep adresRep){
        this.adresRep = adresRep;
        this.klientRep = klientRep;

    }



    private KlientDto mapToKlientDto(KlientEntity klient){
        KlientDto klientDto  =KlientDto.builder()
                .idKlienta(klient.getIdKlienta())
                .login(klient.getLogin())
                .email(klient.getEmail())
                .haslo(klient.getHaslo())
                .telefon(klient.getTelefon())
                .imie(klient.getImie())
                .nazwisko(klient.getNazwisko())
                .adresId(klient.getAdresId())
                .build();
                return klientDto;
    }

    @Override
    public List<KlientDto> findAllKlients() {
        List<KlientEntity> klienci = klientRep.findAll();
        return klienci.stream().map((klient) -> mapToKlientDto(klient)).collect(Collectors.toList());
    }

    @Override
    public KlientDto findKlientById(int id) {
        KlientEntity klient = klientRep.findByIdKlienta(id);
        return mapToKlientDto(klient);
    }

    @Override
    public KlientEntity saveKlient(KlientEntity klient) {
        // Sprawdzenie czy użytkownik o podanym e-mailu już istnieje
        KlientEntity existingEmailUser = klientRep.findByEmail(klient.getEmail());
        if (existingEmailUser != null) {
            throw new RuntimeException("Użytkownik o podanym e-mailu już istnieje");
        }

        // Sprawdzenie czy użytkownik o podanym loginie już istnieje
        KlientEntity existingLoginUser = klientRep.findByLogin(klient.getLogin());
        if (existingLoginUser != null) {
            throw new RuntimeException("Użytkownik o podanym loginie już istnieje");
        }

        // Zapis nowego użytkownika do bazy danych
        return klientRep.save(klient);
    }

    @Override
    public KlientEntity findKlientByEmail(String email) {
        return klientRep.findByEmail(email);
    }

    @Override
    public KlientEntity findKlientByLogin(String login) {
        return klientRep.findByLogin(login);
    }

    @Override
    public KlientDto zalogujKlienta(String login, String haslo) {
        KlientDto klient = mapToKlientDto(klientRep.findByLogin(login));
        if (klient != null && klient.getHaslo().equals(haslo)) {
            return klient;
        }
        return null;
    }


    @Override
    public void updateKlient(KlientDto klientDto) {
        KlientEntity klient = klientRep.findByIdKlienta(klientDto.getIdKlienta());
        if (klient != null) {
            klient.setImie(klientDto.getImie());
            klient.setNazwisko(klientDto.getNazwisko());
            klient.setEmail(klientDto.getEmail());
            klient.setHaslo(klientDto.getHaslo());
            klient.setTelefon(klientDto.getTelefon());
            klient.setAdresId(klientDto.getAdresId()); // Przypisanie obiektu adresu
            klientRep.save(klient);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        KlientEntity klient = klientRep.findByLogin(login);
        if(klient==null)
            throw new UsernameNotFoundException("User not found");
        return new User(klient.getLogin(), klient.getHaslo(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_KLIENT")));
    }
}
