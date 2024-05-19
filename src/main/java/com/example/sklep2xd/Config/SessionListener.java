package com.example.sklep2xd.Config;

import com.example.sklep2xd.Service.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {

//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) principal;
//            if (userDetails.getKlientId() != 0) {
//                event.getSession().setAttribute("klientId", userDetails.getKlientId());
//            } else {
//                event.getSession().setAttribute("pracownikId", userDetails.getPracownikId());
//            }
//        }
//    }

//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) principal;
//            event.getSession().setAttribute("klientId", userDetails.getKlientId());
//        }
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent event) {
//        // Cleanup if needed
//    }

//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) principal;
//            event.getSession().setAttribute("klientId", userDetails.getKlientId());
//            System.out.println("Session created, klientId: " + userDetails.getKlientId());
//        }
//    }

//    @Override
//    public void sessionCreated(HttpSessionEvent event) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) principal;
//            if (userDetails.getPracownikId() != null) {
//                event.getSession().setAttribute("pracownikId", userDetails.getPracownikId());
//                System.out.println("Session created, pracownikId: " + userDetails.getPracownikId());
//            } else if (userDetails.getKlientId() != null) {
//                event.getSession().setAttribute("klientId", userDetails.getKlientId());
//                System.out.println("Session created, klientId: " + userDetails.getKlientId());
//            }
//        }
//    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            if (userDetails.getKlientId() != null) {
                event.getSession().setAttribute("klientId", userDetails.getKlientId());
                System.out.println("Session created, klientId: " + userDetails.getKlientId());
            }
            if (userDetails.getPracownikId() != null) {
                event.getSession().setAttribute("pracownikId", userDetails.getPracownikId());
                System.out.println("Session created, pracownikId: " + userDetails.getPracownikId());
            }
        } else {
            System.out.println("Principal is not an instance of CustomUserDetails");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session destroyed");
    }

//    @Override
//    public void sessionDestroyed(HttpSessionEvent event) {
//        System.out.println("Session destroyed");
//    }
}
