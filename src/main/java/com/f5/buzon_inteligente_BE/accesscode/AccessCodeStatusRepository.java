package com.f5.buzon_inteligente_BE.accesscode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessCodeStatusRepository extends JpaRepository<AccessCodeStatus, Long> {
    Optional<AccessCodeStatus> findByAccessCodeStatusName(String name);
}