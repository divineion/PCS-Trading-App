package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.UpdateRuleDto;
import com.pcs.tradingapp.dto.response.RuleInfoDto;
import com.pcs.tradingapp.services.rule.RuleMapper;

public class RuleMapperTest {
	private final RuleMapper mapper = Mappers.getMapper(RuleMapper.class);
	
	@Test
	public void testRuleToRuleInfoDto() {
	    Rule rule = new Rule();
	    String string = "INSERT INTO...";
	    rule.setSqlStr(string);

	    RuleInfoDto dto = mapper.ruleToRuleInfoDto(rule);

	    assertEquals(string, rule.getSqlStr());
	    assertEquals(rule.getSqlStr(), dto.sql());
	}

	@Test
	public void testUpdateRuleDtoToRule() {
	    String string = "WHERE NOT EXISTS...";

	    UpdateRuleDto ruleDto = new UpdateRuleDto();
	    ruleDto.setSqlStr(string);

	    Rule rule = mapper.updateRuleDtoToRule(ruleDto);

	    assertEquals(string, ruleDto.getSql());
	    assertEquals(ruleDto.getSql(), rule.getSqlStr());
	}
}
