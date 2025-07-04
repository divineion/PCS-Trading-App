package com.pcs.tradingapp.dto.response;

public record RuleInfoDto(int id, String name, String description, String json,
		String template, String sql, String sqlPart) {}
