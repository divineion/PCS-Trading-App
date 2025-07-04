package com.pcs.tradingapp.dto.request.trade;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTradeDto {
	@Size(max=30)
	@NotBlank(message = "Account is mandatory.")
	private String account;
	
	@Size(max=30, message = "max 30 characters")
	private String type;
	
	private BigDecimal buyQuantity;
	
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
	
	public BigDecimal getBuyQuantity() {
		return buyQuantity;
	}
	
	public void setBuyQuantity(BigDecimal buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
}