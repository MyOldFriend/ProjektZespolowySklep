package com.example.sklep2xd.Security;

import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.RolaPracownika;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
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
