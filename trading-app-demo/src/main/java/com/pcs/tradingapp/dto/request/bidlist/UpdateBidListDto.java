package com.pcs.tradingapp.dto.request.bidlist;

public class UpdateBidListDto extends CreateBidListDto {
	private int id;
	
	public UpdateBidListDto(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
