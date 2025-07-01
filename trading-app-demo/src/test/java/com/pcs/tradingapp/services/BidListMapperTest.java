package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.pcs.tradingapp.domain.BidList;
import com.pcs.tradingapp.dto.response.BidListInfoDto;

public class BidListMapperTest {
	private final BidListMapper mapper = Mappers.getMapper(BidListMapper.class);
	
	@Test
	public void testBidListToBidListInfoDto() {
		BidList bidList = new BidList();
		bidList.setAccount("anyACC");
		bidList.setType("anyType");
		bidList.setBidQuantity(5);
		
		BidListInfoDto dto = mapper.bidListToBidListInfoDto(bidList);
		
		assertAll("BidList to BidListInfoDto mapping", 
				() -> assertEquals("anyACC", dto.account()),
		        () -> assertEquals(5, dto.bidQuantity()),
		        () -> assertEquals("anyType", dto.type())		
			);
	}
}
