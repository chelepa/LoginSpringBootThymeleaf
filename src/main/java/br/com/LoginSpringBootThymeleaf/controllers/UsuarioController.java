package br.com.LoginSpringBootThymeleaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.LoginSpringBootThymeleaf.dto.UsuarioDTO;
import br.com.LoginSpringBootThymeleaf.services.GrupoService;
import br.com.LoginSpringBootThymeleaf.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private GrupoService grupoService;
 
	@Autowired 
	private UsuarioService usuarioService;
	
	@GetMapping(value="/consultar")
	public ModelAndView consultar(Model model) {
 
		List<UsuarioDTO> usuariosModel = usuarioService.consultarUsuarios();
		
		model.addAttribute("usuariosModel", usuariosModel);
 
	    return new ModelAndView("usuario/ListagenUsuarios");
	}

}
