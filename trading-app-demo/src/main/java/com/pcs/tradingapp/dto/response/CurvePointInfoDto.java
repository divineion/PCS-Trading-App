package com.pcs.tradingapp.dto.response;

import java.math.BigDecimal;

public record CurvePointInfoDto(Integer id, Integer curveId, BigDecimal term, BigDecimal value) {}
