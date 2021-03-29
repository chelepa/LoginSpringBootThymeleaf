package br.com.LoginSpringBootThymeleaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResponseDTO;
import br.com.LoginSpringBootThymeleaf.services.Grupo.GrupoService;

@Controller
public class GrupoController {
	
	@Autowired
	private GrupoService service;
	
	@GetMapping(value="/grupos")
	public ModelAndView consultarPermissoes(Model model) {
 
		List<GrupoResponseDTO> permissoes = service.findAllGrupos();
		
		model.addAttribute("GrupoResponseDTO", permissoes);
 
	    return new ModelAndView("grupo/ListagemGrupo");
	}

}
