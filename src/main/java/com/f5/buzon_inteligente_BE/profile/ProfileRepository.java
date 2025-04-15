package com.f5.buzon_inteligente_BE.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByUserUserId(Long userId);
    
    boolean existsByUserUserId(Long userId);

    Optional<Profile> findByPermanentCredential(String permanentCredential);

    boolean existsByPermanentCredential(String permanentCredential);
}
