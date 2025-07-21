package com.pcs.tradingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	@GetMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	@GetMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidlist/list";
	}
	
	@GetMapping("/accessdenied")
    public String accessDenied() {
        return "403";
    }
}
