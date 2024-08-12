package com.turf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turf.entities.TurfEntity;
@Repository
public interface TurfRepository extends JpaRepository<TurfEntity, Long> {
//	@Query("select t from com.turf.entities.TurfEntity where t.turf_name=:turf_name")
//	TurfEntity getByName(@Param("turf_name") String turf_name);
}
