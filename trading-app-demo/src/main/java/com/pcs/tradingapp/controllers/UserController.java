package com.pcs.tradingapp.controllers;

import com.pcs.tradingapp.dto.request.user.CreateUserDto;
import com.pcs.tradingapp.dto.request.user.UpdateUserDto;
import com.pcs.tradingapp.dto.response.UserInfoDto;
import com.pcs.tradingapp.exceptions.RoleNotFoundException;
import com.pcs.tradingapp.exceptions.UserNotFoundException;
import com.pcs.tradingapp.exceptions.UsernameAlreadyExistsException;
import com.pcs.tradingapp.services.user.UserService;

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

@Controller
public class UserController {
    private final UserService service;
    
    public UserController(UserService service) {
    	this.service = service;
    }

    @GetMapping("/user/list")
    public String listUsers(Model model) {
    	List<UserInfoDto> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/user/add")
    // pas besoin de données pour l'affichage du formulaire
    //mais le template attend un objet pour binder les champs,
    // donc fournir un objet vide
    public String showAddUserForm(Model model) {
    	model.addAttribute("user", new CreateUserDto());
        return "user/add";
    }
    
    //https://www.baeldung.com/spring-thymeleaf-error-messages
    // https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    // @ModelAttribute("user") synchronise de mon dto entre le contrôleur et le template.
    // la route est appelée au submit du form du template add
    // donc valider le dto puis redirect si OK, sinon
    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute("user") CreateUserDto userDto, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		return "user/add";
    	}

    	try {
			service.createNewUser(userDto);
		    
			return "redirect:/user/list";
    	} catch (UsernameAlreadyExistsException e) {
    		//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/BindingResult.html
    		//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/Errors.html#rejectValue(java.lang.String,java.lang.String)
			result.rejectValue("username", null, e.getMessage());
			return "user/add";
    	} catch (RoleNotFoundException e) {
	    	//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/BindingResult.html
	    	//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/Errors.html#rejectValue(java.lang.String,java.lang.String)
	    	result.rejectValue("role", null, e.getMessage());
	    	return "user/add";
    	}
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        UpdateUserDto user;
		try {
			user = service.fetchUpdateUserDto(id);
			user.setPassword("");
			model.addAttribute("user", user);
			
	        return "user/update";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
			
			return "redirect:/user/list";
		}
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable Integer id, @Valid @ModelAttribute("user") UpdateUserDto user,
    		BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/update";
        }
        
    	try {
			service.updateUser(user);
			
			return "redirect:/user/list";
		} catch (UsernameAlreadyExistsException e) {
			result.rejectValue("username", null, e.getMessage());
			
			return "user/update";
	    } catch (RoleNotFoundException e) {
	    	result.rejectValue("role", null, e.getMessage());
	    	
	    	return "user/update";
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
			
			return "redirect:/user/list";
		}
	}

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
    	try {
			service.deleteUser(id);
		} catch (UserNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMsg",e.getMessage());
		} 
        
        return "redirect:/user/list";
    }
}
