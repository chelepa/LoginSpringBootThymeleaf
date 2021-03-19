package br.com.LoginSpringBootThymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
 
	@GetMapping(value="/")	
	public String login(){
	    return "/login/Login.html";
	}

	@GetMapping(value="/home")
	public String home(){
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//	    System.out.println(auth.getAuthorities());
		return "/home/home.html";
	}
	
	@GetMapping(value="/acessoNegado")
	public String acessoNegado(){
 
		return "/erros/AcessoNegado.html";
	}
 
}
