package com.pcs.tradingapp.services.rule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pcs.tradingapp.constants.ApiMessages;
import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.CreateRuleDto;
import com.pcs.tradingapp.dto.request.rule.UpdateRuleDto;
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
	
	public boolean validateRuleNameIsAvailable(String name) throws RuleNameAlreadyExistsException {
		if (repository.findByName(name).isPresent()) {
			throw new RuleNameAlreadyExistsException(ApiMessages.RULE_NAME_ALREADY_EXISTS);
		}
		
		return true;
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
		validateRuleNameIsAvailable(ruleDto.getName());
		
		Rule rule = mapper.createRuleDtoToRule(ruleDto);
		repository.save(rule);
		
	}

	public void updateRule(UpdateRuleDto ruleDto) throws RuleNameAlreadyExistsException, RuleNotFoundException {
		Rule rule = repository.findById(ruleDto.getId()).orElseThrow(() -> new RuleNotFoundException(ApiMessages.RULE_NOT_FOUND));
	        
		if (!ruleDto.getName().equals(rule.getName())) {
			validateRuleNameIsAvailable(ruleDto.getName());
		}
	    
        Rule ruleToUpdate = mapper.updateRuleDtoToRule(ruleDto);

        repository.save(ruleToUpdate);
    }

	public void deleteRule(Integer id) throws RuleNotFoundException {
		if (repository.findById(id).isEmpty()) {
			throw new RuleNotFoundException(ApiMessages.RULE_NOT_FOUND);
		}
		
		repository.deleteById(id);
	}
}
