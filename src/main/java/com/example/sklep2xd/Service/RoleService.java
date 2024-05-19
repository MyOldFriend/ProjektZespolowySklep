package com.example.sklep2xd.Service;

import com.example.sklep2xd.Models.Role;
import com.example.sklep2xd.Repositories.RoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRep roleRepository;

    @Autowired
    public RoleService(RoleRep roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(int id) {
        return roleRepository.findById((long) id).orElse(null);
    }
}
