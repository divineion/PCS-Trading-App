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

import com.pcs.tradingapp.domain.Rating;
import com.pcs.tradingapp.dto.request.rating.CreateRatingDto;
import com.pcs.tradingapp.dto.response.RatingInfoDto;
import com.pcs.tradingapp.exceptions.RatingOrderNumberAlreadyExistsException;
import com.pcs.tradingapp.services.rating.RatingService;

@Controller
public class RatingController {
    private final RatingService service;
    
    public RatingController(RatingService service) {
    	this.service = service;
    }

    @GetMapping("/rating/list")
    public String index(Model model) {
    	List<RatingInfoDto> ratings = service.getAllRatings();
    	model.addAttribute("ratings", ratings);
        
    	return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(@ModelAttribute("rating") CreateRatingDto rating) {
        return "rating/add";
    }

    @PostMapping("/rating/add")
    public String createRating(@Valid @ModelAttribute("rating") CreateRatingDto rating, BindingResult result, Model model) {
    	if (result.hasFieldErrors()) {
    		return "rating/add";
    	}
    	
        try {
			service.createRating(rating);
			return "redirect:/rating/list";
		} catch (RatingOrderNumberAlreadyExistsException e) {
			result.rejectValue("order", "error.order", e.getMessage());
			return "rating/add";
		}
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
    	try {
			RatingInfoDto rating = service.getRatingById(id);
			model.addAttribute("rating", rating);
			
			return "rating/update";
		} catch (RatingNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());

		    return "redirect:/rating/list";
		}        
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable Integer id, @Valid @ModelAttribute("rating") UpdateRatingDto rating,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    	if (result.hasErrors()) {
    		return "rating/update";
    	}
		service.updateRating(rating);

    	return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}
