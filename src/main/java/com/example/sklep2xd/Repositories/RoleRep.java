package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRep  extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByName(String name);
}
