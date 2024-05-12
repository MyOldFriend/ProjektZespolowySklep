package com.example.sklep2xd.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RolePracownikow")
public class RolaPracownika {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id_roli;
    private String nazwa;
}
