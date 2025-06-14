package com.f5.buzon_inteligente_BE.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUserId(Long id);
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserDni(String userDni);
}
