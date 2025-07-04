package com.pcs.tradingapp.servicestrade;

import java.util.List;

import org.mapstruct.Mapper;

import com.pcs.tradingapp.domain.Trade;
import com.pcs.tradingapp.dto.request.trade.CreateTradeDto;
import com.pcs.tradingapp.dto.response.TradeInfoDto;

@Mapper(componentModel = "spring")
public interface TradeMapper {
	public TradeInfoDto tradeToTradeInfoDto(Trade trade);
	
	public List<TradeInfoDto> tradesToTradeInfoDtos(List<Trade> trade);

	public Trade createTradeDtoToTrade(CreateTradeDto tradeDto);
}
