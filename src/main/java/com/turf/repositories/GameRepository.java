package com.turf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turf.entities.GameEntity;
@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
