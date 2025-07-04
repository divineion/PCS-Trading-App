package com.pcs.tradingapp.servicestrade;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Trade;
import com.pcs.tradingapp.dto.response.TradeInfoDto;
import com.pcs.tradingapp.exceptions.TradeNotFoundException;
import com.pcs.tradingapp.repositories.TradeRepository;

@Service
public class TradeService {
	private final TradeRepository repository;
	final TradeMapper mapper;
	
	public TradeService(TradeRepository repository, TradeMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	public List<TradeInfoDto> getAllTrades() {
		List<Trade> trades = repository.findAll();
		
		return mapper.tradesToTradeInfoDtos(trades);
	}
	
	public TradeInfoDto getTradeById(int id) throws TradeNotFoundException {
		Trade trade = repository.findById(id)
				.orElseThrow(() -> new TradeNotFoundException(ApiMessages.RULE_NOT_FOUND));

		return mapper.tradeToTradeInfoDto(trade);
	}
	
}
