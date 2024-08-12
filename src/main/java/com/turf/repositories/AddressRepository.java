package com.turf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turf.entities.AddressEntity;
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
