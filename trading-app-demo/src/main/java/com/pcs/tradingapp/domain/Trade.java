package com.pcs.tradingapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "trade")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String account;
	private String type;
	private BigDecimal buyQuantity;
	private BigDecimal sellQuantity;
	private BigDecimal buyPrice;
	private BigDecimal sellPrice;
	private String benchmark;
	private LocalDateTime tradeDate;
	private String security;
	private String status;
	private String trader;
	private String book;
	private String creationName;
	private LocalDateTime creationDate;
	private String revisionName;
	private LocalDateTime revisionDate;
	private String dealName;
	private String dealType;
	private String sourceListId;
	private String side;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public BigDecimal getSellQuantity() {
		return sellQuantity;
	}
	
	public void setSellQuantity(BigDecimal sellQuantity) {
		this.sellQuantity = sellQuantity;
	}
	
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public String getBenchmark() {
		return benchmark;
	}
	
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	
	public LocalDateTime getTradeDate() {
		return tradeDate;
	}
	
	public void setTradeDate(LocalDateTime tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	public String getSecurity() {
		return security;
	}
	
	public void setSecurity(String security) {
		this.security = security;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTrader() {
		return trader;
	}
	
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	public String getBook() {
		return book;
	}
	
	public void setBook(String book) {
		this.book = book;
	}
	
	public String getCreationName() {
		return creationName;
	}
	
	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getRevisionName() {
		return revisionName;
	}
	
	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}
	
	public LocalDateTime getRevisionDate() {
		return revisionDate;
	}
	
	public void setRevisionDate(LocalDateTime revisionDate) {
		this.revisionDate = revisionDate;
	}
	
	public String getDealName() {
		return dealName;
	}
	
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	
	public String getDealType() {
		return dealType;
	}
	
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	public String getSourceListId() {
		return sourceListId;
	}
	
	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}
	
	public String getSide() {
		return side;
	}
	
	public void setSide(String side) {
		this.side = side;
	}
}
