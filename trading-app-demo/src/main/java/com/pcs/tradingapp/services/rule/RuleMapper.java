package com.pcs.tradingapp.services.rule;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.CreateRuleDto;
import com.pcs.tradingapp.dto.request.rule.UpdateRuleDto;
import com.pcs.tradingapp.dto.response.RuleInfoDto;

@Mapper(componentModel = "spring")
public interface RuleMapper {
	@Mapping(source = "sqlStr", target = "sql")
	public RuleInfoDto ruleToRuleInfoDto(Rule rule);

	public List<RuleInfoDto> rulesToRuleInfoDtos(List<Rule> rule);

	@Mapping(target = "id", ignore = true)
	@Mapping(source = "sql", target = "sqlStr")
	public Rule createRuleDtoToRule(CreateRuleDto ruleDto);

	@Mapping(source = "sql", target = "sqlStr")
	public Rule updateRuleDtoToRule(UpdateRuleDto ruleDto);
}
