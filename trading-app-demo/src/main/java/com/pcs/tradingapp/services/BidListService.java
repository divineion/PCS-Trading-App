package com.pcs.tradingapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.BidList;
import com.pcs.tradingapp.dto.request.bidlist.CreateBidListDto;
import com.pcs.tradingapp.dto.request.bidlist.UpdateBidListDto;
import com.pcs.tradingapp.dto.response.BidListInfoDto;
import com.pcs.tradingapp.exceptions.BidListNotFoundException;
import com.pcs.tradingapp.repositories.BidListRepository;

@Service
public class BidListService {
	
	private final BidListRepository repository;
	private final BidListMapper mapper;
	
	public BidListService(BidListRepository repository, BidListMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<BidListInfoDto> getAllBidLists() {
		List<BidList> bidLists = repository.findAll();
		return mapper.bidListsToBidListInfoDtos(bidLists);
	}

	public void createNewBidList(CreateBidListDto bidListDto) {
		BidList bidList = mapper.createBidListDtoToBidList(bidListDto);
		repository.save(bidList);
	}

	public UpdateBidListDto fetchUpdateBidListDtoById(Integer id) throws BidListNotFoundException {
		BidList bidList = repository.findById(id)
				.orElseThrow(() -> new BidListNotFoundException(ApiMessages.BIDLIST_NOT_FOUND));
		
		return mapper.bidListToUpdateBidListDto(bidList);
	}

	public void updateBidList(UpdateBidListDto bidList) {
		BidList bidListToUpdate = mapper.updateBidListDtoToBidList(bidList);
		repository.save(bidListToUpdate);
	}

	public void deleteBidList(Integer id) throws BidListNotFoundException {
		if (repository.findById(id).isEmpty()) {
			throw new BidListNotFoundException(ApiMessages.BIDLIST_NOT_FOUND);
		}
		repository.deleteById(id);		
	}

}
