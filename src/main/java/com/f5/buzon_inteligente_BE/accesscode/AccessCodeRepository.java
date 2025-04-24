package com.f5.buzon_inteligente_BE.accesscode;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessCodeRepository extends JpaRepository<AccessCode, Long> {

    boolean existsByAccessCode(String accessCode);  

    Optional<AccessCode> findByAccessCode(String accessCode);
    
    List<AccessCode> findAllByProfile_Id(Long profileId);


}