package com.f5.buzon_inteligente_BE.roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long>{

    Optional<Role> findByRoleName(String roleName);

    
}
