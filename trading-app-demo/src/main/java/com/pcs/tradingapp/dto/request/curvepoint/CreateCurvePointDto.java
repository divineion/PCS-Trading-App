package com.pcs.tradingapp.dto.request.curvepoint;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateCurvePointDto {
	@Min(value = 1)
	@NotNull(message = "must not be null")
	private Integer curveId;
	
	@NotNull(message = "must not be null")
	@Digits(integer = 2, fraction = 1, message = "{curvePoint.term.digits}")
	private BigDecimal term;

	@NotNull(message = "must not be null")
	@Digits(integer = 2, fraction = 1, message = "{curvePoint.value.digits}")
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
