package com.pcs.tradingapp.services.rule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.CreateRuleDto;
import com.pcs.tradingapp.dto.response.RuleInfoDto;
import com.pcs.tradingapp.exceptions.RuleNameAlreadyExistsException;
import com.pcs.tradingapp.exceptions.RuleNotFoundException;
import com.pcs.tradingapp.repositories.RuleRepository;

@Service
public class RuleService {
	
	private final RuleMapper mapper;
	private RuleRepository repository;
	
	public RuleService(RuleMapper mapper, RuleRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	public List<RuleInfoDto> getAllRules() {
		List<Rule> rules = repository.findAll();
		return mapper.rulesToRuleInfoDtos(rules);
	}
	
	public RuleInfoDto getRuleById(int id) throws RuleNotFoundException {
		Rule rule = repository.findById(id)
				.orElseThrow(() -> new RuleNotFoundException(ApiMessages.RULE_NOT_FOUND));
		
		return mapper.ruleToRuleInfoDto(rule);
	}

	public void createRule(CreateRuleDto ruleDto) throws RuleNameAlreadyExistsException {
		if (repository.findByName(ruleDto.getName()).isPresent()) {
			throw new RuleNameAlreadyExistsException(ApiMessages.RULE_NAME_ALREADY_EXISTS);
		}
		
		Rule rule = mapper.createRatingDtoToRating(ruleDto);
		repository.save(rule);
		
	}

}
