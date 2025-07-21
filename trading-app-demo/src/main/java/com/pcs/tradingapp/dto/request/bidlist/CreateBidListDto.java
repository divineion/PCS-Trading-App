package com.pcs.tradingapp.dto.request.bidlist;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateBidListDto {
	@NotBlank(message = "account is mandatory")
	@Size(max=30)
	@Column(nullable=false)
	private String account;
	
	@NotBlank(message = "type is mandatory")
	@Size(max=30)
	@Column(nullable=false)
	private String type;
	
	@Min(value = 1, message = "quantity must be a positive integer")
	private int bidQuantity;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(int bidQuantity) {
		this.bidQuantity = bidQuantity;
	}
}
