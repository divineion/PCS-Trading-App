package com.pcs.tradingapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "rating")
public class Rating {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String moodysRating;
	private String sandPRating;
	private String fitchRating;
	private Integer orderNumber;

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMoodysRating() {
		return moodysRating;
	}
	
	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}
	
	@Column(name = "sand_p_rating")
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
	
	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
}
