package com.pcs.tradingapp.dto.response;

public record RatingInfoDto(Integer id, String moodysRating, String sandPRating, 
		String fitchRating, Integer order) {}
