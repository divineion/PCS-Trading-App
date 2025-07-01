package com.pcs.tradingapp.dto.response;

import java.math.BigDecimal;

public record CurvePointInfoDto(Integer id, Integer CurveId, BigDecimal term, BigDecimal value) {}
