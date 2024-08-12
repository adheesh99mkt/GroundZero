package com.turf.repositories;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.turf.entities.BookingEntity;
import com.turf.entities.UserEntity;
@Repository
@EnableJpaRepositories
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
	
	@Modifying
	@Transactional
	@Query("update BookingEntity b set b.no_of_players=:no_of_players where b.id=:bookingId")
	void updateBooking(@Param(value = "bookingId") Long bookingId, @Param(value = "no_of_players") Long no_of_players);

	
	@Query("SELECT b FROM BookingEntity b WHERE b.turf.id = :turfId ORDER BY b.date ASC")
	List<BookingEntity> findByTurfId(@Param("turfId") Long turfId);


}
