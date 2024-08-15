package com.turf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turf.entities.PaymentEntity;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
