package com.pcs.tradingapp.dto.response;

import java.math.BigDecimal;

public record TradeInfoDto(int id, String account, String type, BigDecimal buyQuantity) {

}
