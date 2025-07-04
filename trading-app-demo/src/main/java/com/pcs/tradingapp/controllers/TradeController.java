package com.pcs.tradingapp.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pcs.tradingapp.domain.Trade;
import com.pcs.tradingapp.dto.request.trade.CreateTradeDto;
import com.pcs.tradingapp.dto.response.TradeInfoDto;
import com.pcs.tradingapp.servicestrade.TradeService;

import jakarta.validation.Valid;

@Controller
public class TradeController {
    private final TradeService service;
    
    public TradeController(TradeService service) {
    	this.service = service;
    }

    @GetMapping("/trade/list")
    public String home(Model model) {
        List<TradeInfoDto> trades = service.getAllTrades();
        model.addAttribute("trades", trades);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(@ModelAttribute("trade") CreateTradeDto trade) {
        return "trade/add";
    }

    @PostMapping("/trade/add")
    public String createTrade(@Valid @ModelAttribute("trade") CreateTradeDto trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	return "trade/add";
        }
        
        service.createTrade(trade);
        
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        return "redirect:/trade/list";
    }
}
