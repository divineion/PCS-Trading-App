package com.pcs.tradingapp.services;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pcs.tradingapp.domain.BidList;
import com.pcs.tradingapp.dto.request.bidlist.CreateBidListDto;
import com.pcs.tradingapp.dto.request.bidlist.UpdateBidListDto;
import com.pcs.tradingapp.dto.response.BidListInfoDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BidListMapper {
	public BidList createBidListDtoToBidList(CreateBidListDto dto);
	
	public BidListInfoDto bidListToBidListInfoDto(BidList bidList);
	public List<BidListInfoDto> bidListsToBidListInfoDtos(List<BidList> bidLists);
	
	public UpdateBidListDto bidListToUpdateBidListDto(BidList bildList);

	public BidList updateBidListDtoToBidList(UpdateBidListDto bidList);
}
