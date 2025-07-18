package com.pcs.tradingapp.dto.request.user;

public class UpdateUserDto extends CreateUserDto {
	private int id;
	
	public UpdateUserDto(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}