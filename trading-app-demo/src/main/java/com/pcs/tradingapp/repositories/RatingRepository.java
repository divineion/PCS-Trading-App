package com.pcs.tradingapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcs.tradingapp.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	Optional<Rating> findByOrderNumber(Integer oderNumber);
}
