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

import com.pcs.tradingapp.dto.request.bidlist.CreateBidListDto;
import com.pcs.tradingapp.dto.request.bidlist.UpdateBidListDto;
import com.pcs.tradingapp.dto.response.BidListInfoDto;
import com.pcs.tradingapp.exceptions.BidListNotFoundException;
import com.pcs.tradingapp.services.bidlist.BidListService;

@Controller
public class BidListController {
    private final BidListService service;
    
    public BidListController(BidListService service) {
        this.service = service;
    }
    
    @GetMapping("/bidlist/list")
    public String list(Model model) {
        List<BidListInfoDto> bidLists = service.getAllBidLists();
        model.addAttribute("bidLists", bidLists);
        return "bidlist/list";
    }

    @GetMapping("/bidlist/add")
    public String addBidListForm(Model model) {
        model.addAttribute("bidList", new CreateBidListDto());
        return "bidlist/add";
    }

    @PostMapping("/bidlist/add")
    public String validate(@Valid @ModelAttribute("bidList") CreateBidListDto bidListDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        
        service.createNewBidList(bidListDto);
        return "redirect:/bidlist/list";
    }

    @GetMapping("/bidlist/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model,  RedirectAttributes redirectAttributes) {
        UpdateBidListDto bidList;
        try {
            bidList = service.fetchUpdateBidListDtoById(id);
            model.addAttribute("bidList", bidList);
            
            return "bidList/update";
        } catch (BidListNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());

            return "redirect:/bidlist/list";
        }
    }

    @PostMapping("/bidlist/update/{id}")
    public String updateBid(@PathVariable Integer id, @Valid @ModelAttribute("bidList") UpdateBidListDto bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        
        service.updateBidList(bidList);

        return "redirect:/bidlist/list";
    }

    @GetMapping("/bidlist/delete/{id}")
    public String deleteBidList(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            service.deleteBidList(id);
        } catch (BidListNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/bidlist/list";
    }
}
