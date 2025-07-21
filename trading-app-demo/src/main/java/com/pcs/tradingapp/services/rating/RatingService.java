package com.pcs.tradingapp.services.rating;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Rating;
import com.pcs.tradingapp.dto.request.rating.CreateRatingDto;
import com.pcs.tradingapp.dto.request.rating.UpdateRatingDto;
import com.pcs.tradingapp.dto.response.RatingInfoDto;
import com.pcs.tradingapp.exceptions.RatingNotFoundException;
import com.pcs.tradingapp.exceptions.RatingOrderNumberAlreadyExistsException;
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

	public void createRating(CreateRatingDto ratingDto) throws RatingOrderNumberAlreadyExistsException {
		if (repository.findByOrderNumber(ratingDto.getOrder()).isPresent()) {
			throw new RatingOrderNumberAlreadyExistsException(ApiMessages.RATING_ORDER_NUMBER_EXISTS);
		}
		
		Rating rating = mapper.createRatingDtoToRating(ratingDto);
		repository.save(rating);
	}

	public void updateRating(UpdateRatingDto ratingDto) {
		Rating rating = mapper.updateRatingDtoToRating(ratingDto);
		
		repository.save(rating);
	}

	public void deleteRating(Integer id) throws RatingNotFoundException {
		if (repository.findById(id).isEmpty()) {
			throw new RatingNotFoundException(ApiMessages.RATING_NOT_FOUND);
		}
		
		repository.deleteById(id);
	}
}
