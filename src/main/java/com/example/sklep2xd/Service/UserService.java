package com.example.sklep2xd.Service;

import com.example.sklep2xd.Dto.RejestracjaDto;
import com.example.sklep2xd.Models.UserEntity;
import jakarta.servlet.Registration;

public interface UserService {

    void saveUser(RejestracjaDto rejestracjaDto);

    UserEntity findByLogin(String login);
}
