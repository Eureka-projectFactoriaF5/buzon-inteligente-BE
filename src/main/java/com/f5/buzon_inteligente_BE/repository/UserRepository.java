package com.f5.buzon_inteligente_BE.repository;

import com.f5.buzon_inteligente_BE.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPermanentCredential(String permanentCredential);
}
