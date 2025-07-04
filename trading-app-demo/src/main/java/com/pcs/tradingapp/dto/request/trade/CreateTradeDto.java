package com.pcs.tradingapp.dto.request.trade;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTradeDto {
	@Size(max=30)
	@NotBlank(message = "{trade.account.required}")
	private String account;
	
	@Size(max=30, message = "{trade.type.characters}")
	private String type;
	
	@Digits(integer = 8, fraction = 2, message = "{typeMismatch.trade.buyQuantity}")
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