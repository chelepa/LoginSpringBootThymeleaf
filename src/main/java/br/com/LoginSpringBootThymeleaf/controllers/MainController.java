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
 
		return "/home/home.html";
	}
	
	@GetMapping(value="/acessoNegado")
	public String acessoNegado(){
 
		return "/erros/AcessoNegado.html";
	}
	
	@GetMapping(value="/layout")
	public String layout(){
 
		return "/layout/layout.html";
	}
 
}
