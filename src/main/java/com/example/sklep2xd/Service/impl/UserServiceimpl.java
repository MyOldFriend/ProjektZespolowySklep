package com.example.sklep2xd.Service.impl;

import com.example.sklep2xd.Dto.RejestracjaDto;
import com.example.sklep2xd.Models.RoleEntity;
import com.example.sklep2xd.Models.UserEntity;
import com.example.sklep2xd.Repositories.RoleRep;
import com.example.sklep2xd.Repositories.UserRep;
import com.example.sklep2xd.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceimpl implements UserService {

    private UserRep userRep;
    private RoleRep roleRep;

    @Autowired
    public UserServiceimpl(UserRep userRep, RoleRep roleRep) {
        this.userRep = userRep;
        this.roleRep = roleRep;
    }

    @Override
    public void saveUser(RejestracjaDto rejestracjaDto) {
        UserEntity user = new UserEntity();
        user.setLogin(rejestracjaDto.getLogin());
        user.setHaslo(rejestracjaDto.getHaslo());
        RoleEntity role = roleRep.findByName("KLIENT");
        user.setRoles(Arrays.asList(role));
        userRep.save(user);

    }

    @Override
    public UserEntity findByLogin(String login) {
        return userRep.findByLogin(login);
    }
}
