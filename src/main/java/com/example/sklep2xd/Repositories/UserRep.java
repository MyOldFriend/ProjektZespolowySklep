package com.example.sklep2xd.Repositories;

import com.example.sklep2xd.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String Login);
}
