package com.example.sklep2xd.Config;

import com.example.sklep2xd.Service.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if (userDetails.getKlientId() != null) {
                session.setAttribute("klientId", userDetails.getKlientId());
                System.out.println("Set session attribute klientId: " + userDetails.getKlientId());
            }
            if (userDetails.getPracownikId() != null) {
                session.setAttribute("pracownikId", userDetails.getPracownikId());
                System.out.println("Set session attribute pracownikId: " + userDetails.getPracownikId());
            }
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            response.sendRedirect("/Pracownik/administrator");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
            response.sendRedirect("/Pracownik/dzialzamowien");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KLIENT"))) {
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/home");
        }
    }
}
