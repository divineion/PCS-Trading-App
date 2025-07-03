package com.pcs.tradingapp.services.rating;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Rating;
import com.pcs.tradingapp.dto.response.RatingInfoDto;
import com.pcs.tradingapp.exceptions.RatingNotFoundException;
import com.pcs.tradingapp.repositories.RatingRepository;

@Service
public class RatingService {
	private final RatingRepository repository;
	private final RatingMapper mapper;
	
	public RatingService(RatingRepository repository, RatingMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public List<RatingInfoDto> getAllRatings() {
		List<Rating> ratings = repository.findAll();

		return mapper.ratingsToRatingInfoDtos(ratings);
	}
	
	public RatingInfoDto getRatingById(int id) throws RatingNotFoundException {
		Rating rating = repository.findById(id)
				.orElseThrow(() -> new RatingNotFoundException(ApiMessages.RATING_NOT_FOUND));
		
		return mapper.ratingToRatingInfoDto(rating);
	}
}
