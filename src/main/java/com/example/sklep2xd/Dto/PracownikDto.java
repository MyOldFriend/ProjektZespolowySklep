package com.example.sklep2xd.Dto;

import com.example.sklep2xd.Models.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PracownikDto {
    private int idPracownika;
    private String login;
    private String haslo;
    private String dzial;
    private String imie;
    private String nazwisko;
    private List<Role> roles;

    // Method to get role name as a string
    public String getRoleName() {
        if (roles != null && !roles.isEmpty()) {
            return roles.get(0).getName(); // assuming one role per employee
        }
        return "No Role";
    }
}
