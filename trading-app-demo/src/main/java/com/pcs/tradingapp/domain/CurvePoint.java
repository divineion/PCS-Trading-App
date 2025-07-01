package com.pcs.tradingapp.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "curve_point")
public class CurvePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer curveId;
	private LocalDateTime asOfDate;
	private BigDecimal term;
	private BigDecimal value;
	private LocalDateTime creationDate;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCurveId() {
		return curveId;
	}
	
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	
	public LocalDateTime getAsOfDate() {
		return asOfDate;
	}
	
	public void setAsOfDate(LocalDateTime asOfDate) {
		this.asOfDate = asOfDate;
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
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
