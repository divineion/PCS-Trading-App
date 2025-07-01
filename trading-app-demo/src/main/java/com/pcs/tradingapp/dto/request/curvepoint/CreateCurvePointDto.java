package com.pcs.tradingapp.dto.request.curvepoint;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class CreateCurvePointDto {
	@NotNull(message = "must not be null")
	private Integer curveId;
	
	@NotNull(message = "must not be null")
	private BigDecimal term;

	@NotNull(message = "must not be null")
	private BigDecimal value;
	
	public Integer getCurveId() {
		return curveId;
	}
	
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	
	public BigDecimal getTerm() {
		return term;
	}
	
	public void setTerm(BigDecimal term) {
		this.term = term;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
