package com.pcs.tradingapp.dto.response;

import java.math.BigDecimal;

public record TradeInfoDto( String account, String type, BigDecimal buyQuantity) {

}
