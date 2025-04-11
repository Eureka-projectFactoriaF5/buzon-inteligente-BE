package com.f5.buzon_inteligente_BE.roles;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + id));
        return role;
    }

    private Role findByRoleName(String roleName) {
        return repository.findByRoleName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + roleName));
        } 

    public Set<Role> assignDefaultRole() {
        Role defaultRole = this.findByRoleName("USER");

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }

}
