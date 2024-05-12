package com.example.sklep2xd.Security;

import com.example.sklep2xd.Models.PracownikEntity;
import com.example.sklep2xd.Models.RolaPracownika;
import com.example.sklep2xd.Repositories.PracownikRep;
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
    private PracownikRep pracownikRep;

    public PracownikDetailsService(PracownikRep pracownikRep) {
        this.pracownikRep = pracownikRep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PracownikEntity pracownik = pracownikRep.findByLogin(username);
        return new User(pracownik.getLogin(), pracownik.getHaslo(), mapRoleToUprawnienia(pracownik.getRole()));
    }

    private Collection<GrantedAuthority> mapRoleToUprawnienia(List<RolaPracownika> role){
        return role.stream().map(rola -> new SimpleGrantedAuthority(rola.getNazwa())).collect(Collectors.toList());
    }
}
