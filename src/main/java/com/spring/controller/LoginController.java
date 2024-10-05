package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.LoginBean;
import com.spring.model.UserBean;

import Repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping(value="/")
	public ModelAndView showLogin() {
		return new ModelAndView("login" , "loginObj" , new LoginBean());
	}
	
	@PostMapping("/doLogin")
	  public String doLogin(@ModelAttribute("loginObj") LoginBean login , UserBean user , Model model,HttpSession session) {
	   
		if(user.getEmail() == null) {
			model.addAttribute("error", "Fill the blanks");
			return "/bookmanagement_crud_with_spring/";
		}
		else {
			
			boolean result = repo.checkEmail(user.getEmail());
			
			if(result) {
				
				UserBean obj = repo.loginUser(login);
				if(obj==null) {
					model.addAttribute("error", "Incorrect Password!!");
					return "/bookmanagement_crud_with_spring/";
				}
				else {
					
					 session.setAttribute("user",obj);
					session.setAttribute("email", obj.getEmail());
					 session.setAttribute("loggedInUser",obj);
					
					return "redirect:/book/showBook";
					
				}
				
			}
			else {
				model.addAttribute("error", "Invalid Email!!");
				return "/bookmanagement_crud_with_spring/";
			}
		}
	  }
	}
