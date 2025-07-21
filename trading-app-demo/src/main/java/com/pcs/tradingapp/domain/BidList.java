package com.pcs.tradingapp.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "bid_list")
public class BidList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(max=30)
	@Column(nullable=false)
	private String account;
	
	@Size(max=30)
	@Column(nullable=false)
	private String type;
	
	private int bidQuantity;
	
	private int askQuantity;
	
	@Column(columnDefinition = "DECIMAL(10,4")
	private BigDecimal bid;
	
	@Column(columnDefinition = "DECIMAL(10,4")
	private BigDecimal ask;
	
	@Size(max=125)
	private String benchmark;
	
	private LocalDateTime bidListDate;
	
	@Size(max=125)
	private String commentary;
	
	@Size(max=125)
	private String security;
	
	@Size(max=10)
	private String status;
	
	@Size(max=20)
	private String trader;
	
	@Size(max=125)
	private String book;
	
	@Size(max=125)
	private String creationName;
	
	private LocalDateTime creationDate;
	
	@Size(max=125)
	private String revisionName;
	
	private LocalDateTime revisionDate;
	
	@Size(max=125)
	private String dealName;
	
	@Size(max=125)
	private String dealType;
	
	@Size(max=125)
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
	
	public int getBidQuantity() {
		return bidQuantity;
	}
	
	public void setBidQuantity(int bidQuantity) {
		this.bidQuantity = bidQuantity;
	}
	
	public int getAskQuantity() {
		return askQuantity;
	}
	
	public void setAskQuantity(int askQuantity) {
		this.askQuantity = askQuantity;
	}
	
	public BigDecimal getBid() {
		return bid;
	}
	
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	
	public BigDecimal getAsk() {
		return ask;
	}
	
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	
	public String getBenchmark() {
		return benchmark;
	}
	
	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}
	
	public LocalDateTime getBidListDate() {
		return bidListDate;
	}
	
	public void setBidListDate(LocalDateTime bidListDate) {
		this.bidListDate = bidListDate;
	}
	
	public String getCommentary() {
		return commentary;
	}
	
	public void setCommentary(String commentary) {
		this.commentary = commentary;
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
