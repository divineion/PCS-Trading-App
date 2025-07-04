package com.pcs.tradingapp.servicestrade;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pcs.tradingapp.domain.Trade;
import com.pcs.tradingapp.dto.request.trade.CreateTradeDto;
import com.pcs.tradingapp.dto.request.trade.UpdateTradeDto;
import com.pcs.tradingapp.dto.response.TradeInfoDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TradeMapper {
	public TradeInfoDto tradeToTradeInfoDto(Trade trade);
	
	public List<TradeInfoDto> tradesToTradeInfoDtos(List<Trade> trade);

	public Trade createTradeDtoToTrade(CreateTradeDto tradeDto);

	public Trade updateTradeDtoToTrade(UpdateTradeDto tradeDto);
}
