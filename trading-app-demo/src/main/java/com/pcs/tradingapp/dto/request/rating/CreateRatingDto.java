package com.pcs.tradingapp.dto.request.rating;

import jakarta.validation.constraints.NotNull;

public class CreateRatingDto {
	private String moodysRating;
	
	private String sandPRating;
	
	private String fitchRating;
	
	@NotNull
	private Integer order;

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
