package com.example.sklep2xd.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final Integer pracownikId;
    private final Integer klientId;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer pracownikId, Integer klientId) {
        super(username, password, authorities);
        this.pracownikId = pracownikId;
        this.klientId = klientId;
    }

    public Integer getPracownikId() {
        return pracownikId;
    }

    public Integer getKlientId() {
        return klientId;
    }

//    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, int klientId) {
//        super(username, password, authorities);
//        this.klientId = klientId;
//    }
//
//    public int getKlientId() {
//        return klientId;
//    }
}
