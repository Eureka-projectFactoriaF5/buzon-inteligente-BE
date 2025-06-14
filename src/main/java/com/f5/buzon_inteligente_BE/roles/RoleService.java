package com.f5.buzon_inteligente_BE.roles;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + id));
        return role;
    }

    private Role findByRoleName(String roleName) {
        return repository.findByRoleName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + roleName));
    }

    public Role getDefaultRole() {
        return this.findByRoleName("USER");
    }
}
