package com.f5.buzon_inteligente_BE.parcel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    Optional<Parcel> findTopByAccessCodeOrderByDeliveryDateDesc(AccessCode accessCode);

}
