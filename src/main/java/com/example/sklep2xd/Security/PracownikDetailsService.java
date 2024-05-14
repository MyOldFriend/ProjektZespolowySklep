package com.example.sklep2xd.Security;

import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.RolaPracownika;
import com.example.sklep2xd.Repositories.PracownikRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PracownikDetailsService implements UserDetailsService {
    private final PracownikRep pracownikRep;

//    public PracownikDetailsService(PracownikRep pracownikRep) {
//        this.pracownikRep = pracownikRep;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        PracownikEntity pracownik = pracownikRep.findByLogin(username);
//        return new User(pracownik.getLogin(), pracownik.getHaslo(), mapRoleToUprawnienia(pracownik.getRole()));
//    }

    @Autowired
    public PracownikDetailsService(PracownikRep pracownikRepository) {
        this.pracownikRep = pracownikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PracownikEntity pracownik = pracownikRep.findByLogin(username);
        if (pracownik == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie: " + username);
        }
        return new CustomUserDetails(pracownik);
    }

    private static class CustomUserDetails extends org.springframework.security.core.userdetails.User {
        private final int id;

        public CustomUserDetails(PracownikEntity pracownik) {
            super(pracownik.getLogin(), pracownik.getHaslo(), mapRoleToAuthorities(pracownik.getRole()));
            this.id = pracownik.getIdPracownika();
        }

        public int getId() {
            return id;
        }

        private static Collection<? extends GrantedAuthority> mapRoleToAuthorities(List<RolaPracownika> roles) {
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNazwa())).collect(Collectors.toList());
        }
    }
}
