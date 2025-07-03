package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.pcs.tradingapp.domain.Rating;
import com.pcs.tradingapp.dto.request.rating.UpdateRatingDto;
import com.pcs.tradingapp.dto.response.RatingInfoDto;
import com.pcs.tradingapp.services.rating.RatingMapper;

public class RatingMapperTest {
	private final RatingMapper mapper = Mappers.getMapper(RatingMapper.class);
	
	@Test
	public void testRatingToRatingInfoDto() {
		Rating rating = new Rating();
		rating.setOrderNumber(1);
		
		RatingInfoDto dto = mapper.ratingToRatingInfoDto(rating);
		
		assertEquals(1, dto.order());
	}
	
	@Test
	public void testUpdateRatingDtoToRating() {
		UpdateRatingDto ratingDto = new UpdateRatingDto();
		ratingDto.setOrder(1);
		
		Rating rating = mapper.updateRatingDtoToRating(ratingDto);
		
		assertEquals(1, rating.getOrderNumber());
	}
}
