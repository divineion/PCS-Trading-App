package com.pcs.tradingapp.services.rating;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pcs.tradingapp.domain.Rating;
import com.pcs.tradingapp.dto.response.RatingInfoDto;

@Mapper(componentModel = "spring")
public interface RatingMapper {
	@Mapping(source = "orderNumber", target = "order")
	public RatingInfoDto ratingToRatingInfoDto(Rating rating);

	public List<RatingInfoDto> ratingsToRatingInfoDtos(List<Rating> ratings);
}
