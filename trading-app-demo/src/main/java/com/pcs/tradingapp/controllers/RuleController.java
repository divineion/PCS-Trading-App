package com.pcs.tradingapp.controllers;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcs.tradingapp.domain.Rule;
import com.pcs.tradingapp.dto.request.rule.CreateRuleDto;
import com.pcs.tradingapp.dto.response.RuleInfoDto;
import com.pcs.tradingapp.exceptions.RuleNameAlreadyExistsException;
import com.pcs.tradingapp.exceptions.RuleNotFoundException;
import com.pcs.tradingapp.services.rule.RuleService;

@Controller
public class RuleController {
	private final RuleService service;
	
	public RuleController(RuleService service) {
		this.service = service;
	}
	
    @GetMapping("/rule/list")
    public String index(Model model) {
    	List<RuleInfoDto> rules = service.getAllRules();
    	model.addAttribute("rules", rules);
    	
        return "rule/list";
    }

    @GetMapping("/rule/add")
    public String addRuleForm(@ModelAttribute("rule") CreateRuleDto rule) {
        return "rule/add";
    }

    @PostMapping("/rule/add")
    public String createRule(@Valid @ModelAttribute("rule") CreateRuleDto rule, BindingResult result, Model model) {
    	try {
			service.createRule(rule);
			
			return "redirect:/rule/list";
		} catch (RuleNameAlreadyExistsException e) {
			result.rejectValue("name", null, e.getMessage());
			return "rule/add";
		}
    }

    @GetMapping("/rule/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model,
    		RedirectAttributes redirectAttributes) {
    	try {
			RuleInfoDto rule = service.getRuleById(id);
			model.addAttribute("rule", rule);
			return "/rule/update";
		} catch (RuleNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
			return "redirect:/rule/list";
		}
    }

    @PostMapping("/rule/update/{id}")
    public String updateRule(@PathVariable Integer id, @Valid Rule rule,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/rule/list";
    }

    @GetMapping("/rule/delete/{id}")
    public String deleteRule(@PathVariable Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/rule/list";
    }
}
