package com.pcs.tradingapp.services.rule;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.response.RuleInfoDto;

@Mapper(componentModel = "spring")
public interface RuleMapper {
	@Mapping(source = "sqlStr", target = "sql")
	public RuleInfoDto ruleToRuleInfoDto(Rule rule);

	public List<RuleInfoDto> rulesToRuleInfoDtos(List<Rule> rule);
}
