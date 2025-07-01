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

import com.pcs.tradingapp.domain.CurvePoint;
import com.pcs.tradingapp.dto.request.curvepoint.CreateCurvePointDto;
import com.pcs.tradingapp.dto.response.CurvePointInfoDto;
import com.pcs.tradingapp.services.curvepoint.CurvePointService;

@Controller
public class CurvePointController {
    private final CurvePointService service;
    
    public CurvePointController(CurvePointService service) {
    	this.service = service;
    }

    @GetMapping("/curvepoint/list")
    public String index(Model model) {
    	List<CurvePointInfoDto> curvePoints = service.findAllCurvePoints();
    	model.addAttribute("curvePoints", curvePoints);
    	
        return "curvePoint/list";
    }

    @GetMapping("/curvepoint/add")
    public String addCurvePointForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvepoint/add")
    public String createCurvePoint(@Valid @ModelAttribute("curvePoint") CreateCurvePointDto curvePoint, BindingResult result, Model model) {
    	if (result.hasFieldErrors()) {
    		return "curvePoint/add";
    	}
    	service.createCurvePoint(curvePoint);
    	
        return "redirect:/curvepoint/list";
    }

    @GetMapping("/curvepoint/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        return "curvePoint/update";
    }

    @PostMapping("/curvepoint/update/{id}")
    public String updateCurvePoint(@PathVariable Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvepoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        return "redirect:/curvePoint/list";
    }
}
