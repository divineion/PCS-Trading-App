package com.pcs.tradingapp.dto.request.rating;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateRatingDto {
	@Pattern(regexp = "^(Aaa|Aa1|Aa2|Aa3|A1|A2|A3|Baa1|Baa2|Baa3|Ba1|Ba2|Ba3|B1|B2|B3|Caa1|Caa2|Caa3|Ca|C)$",
	         message = "{moodys.rating.format}")
	private String moodysRating;
	
	@Pattern(regexp = "^(AAA|AA\\+|AA|AA\\-|A\\+|A|A\\-|BBB\\+|BBB|BBB\\-|BB\\+|BB|BB\\-|B\\+|B|B\\-|CCC\\+|CCC|CCC\\-|D)$",
	         message = "{sandp.rating.format}")
	private String sandPRating;
	
	@Pattern(regexp = "^(AAA|AA\\+|AA|AA\\-|A\\+|A|A\\-|BBB\\+|BBB|BBB\\-|BB\\+|BB|BB\\-|B\\+|B|B\\-|CCC\\+|CCC|CCC\\-|D)$",
	         message = "{fitch.rating.format}")
	private String fitchRating;
	
	@Min(value = 1)
	@NotNull(message = "{rating.order.digits}")
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
