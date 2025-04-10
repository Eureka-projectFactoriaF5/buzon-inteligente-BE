package com.f5.buzon_inteligente_BE.roles;
import java.util.HashSet;
import java.util.Set;


public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow();
        return role;
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = this.getById(1L);

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }

}
