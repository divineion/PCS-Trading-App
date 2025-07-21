package com.pcs.tradingapp.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.UpdateRuleDto;
import com.pcs.tradingapp.exceptions.RuleNameAlreadyExistsException;
import com.pcs.tradingapp.exceptions.RuleNotFoundException;
import com.pcs.tradingapp.repositories.RuleRepository;
import com.pcs.tradingapp.services.rule.RuleService;

@ExtendWith(MockitoExtension.class)
public class RuleServiceTest {
	@Mock
	private RuleRepository repository;
	
	@InjectMocks
	private RuleService service;
	
	@Test
	void testFetchRule_withUnknownId_shouldThrowException() {
	    when(repository.findById(999)).thenReturn(Optional.empty());

	    assertThrows(RuleNotFoundException.class, () -> {
	        service.getRuleById(999);
	    });
	}
	
	@Test
	void testUpdateRule_withExistingName_shouldThrowException() {
	    Rule existingRule = new Rule();
	    existingRule.setId(1);
	    existingRule.setName("Old Name");

	    Rule duplicateRule = new Rule();
	    duplicateRule.setId(2);
	    duplicateRule.setName("Any Name");

	    UpdateRuleDto dto = new UpdateRuleDto();
	    dto.setId(1);
	    dto.setName("Any Name");

	    when(repository.findById(1)).thenReturn(Optional.of(existingRule));
	    when(repository.findByName("Any Name")).thenReturn(Optional.of(duplicateRule));

	    assertThrows(RuleNameAlreadyExistsException.class, () -> {
	        service.updateRule(dto);
	    });
	}
}
