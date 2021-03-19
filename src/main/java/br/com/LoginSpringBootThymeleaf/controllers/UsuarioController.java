package br.com.LoginSpringBootThymeleaf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.LoginSpringBootThymeleaf.dto.GrupoDTO;
import br.com.LoginSpringBootThymeleaf.dto.UsuarioDTO;
import br.com.LoginSpringBootThymeleaf.services.GrupoService;
import br.com.LoginSpringBootThymeleaf.services.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private GrupoService grupoService;
 
	@Autowired 
	private UsuarioService usuarioService;
	
	@GetMapping(value="/usuario")
	public ModelAndView consultar(Model model) {
 
		List<UsuarioDTO> usuariosModel = usuarioService.consultarUsuarios();
		
		model.addAttribute("usuariosModel", usuariosModel);
 
	    return new ModelAndView("usuario/ListagenUsuarios");
	}
	
	@GetMapping(value="/usuario/cadastro")	
	public ModelAndView novoCadastro(Model model) {
 
		model.addAttribute("grupos", grupoService.consultarGrupos());
		model.addAttribute("usuarioModel", new UsuarioDTO());
 
	    return new ModelAndView("usuario/CadastroUsuarios");
	}
	
	@PostMapping(value="/usuario/salvarUsuario")
	public ModelAndView salvarUsuario(@ModelAttribute @Valid UsuarioDTO usuarioModel, final BindingResult result,	Model model, RedirectAttributes redirectAttributes){
		
		if(!result.hasErrors()){
			
			usuarioService.salvarUsuario(usuarioModel);
			
			ModelAndView modelAndView = new ModelAndView("redirect:/usuario");
			
			redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
			
			return modelAndView;
			
		}
		
		List<GrupoDTO> gruposModel = usuarioService.setGruposModel(usuarioModel);
		
		model.addAttribute("grupos", gruposModel);
		
		model.addAttribute("usuarioModel", usuarioModel);
		
		return new ModelAndView("usuario/CadastroUsuarios");
		
	}
	
	@GetMapping(value="/usuario/editar/{codigoUsuario}")
	public ModelAndView editarCadastro(@PathVariable("codigoUsuario") Long codigoUsuario, Model model) {

		UsuarioDTO usuarioModel = usuarioService.consultarUsuariosbyId(codigoUsuario);
		
		List<GrupoDTO> gruposModel = usuarioService.setGruposModel(usuarioModel);
  
		model.addAttribute("grupos", gruposModel);
 
		model.addAttribute("usuarioModel", usuarioModel);
 
	    return new ModelAndView("usuario/UpdateUsuarios");
	 }
	
	@PostMapping(value="/usuario/salvarAlteracao")
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid UsuarioDTO usuarioModel, final BindingResult result, Model model, RedirectAttributes redirectAttributes){
 
		boolean isErroNullCampos = false;
 
		for (FieldError fieldError : result.getFieldErrors()) {
			
		    if(!fieldError.getField().equals("senha")){
		    	isErroNullCampos = true;	
		    }	
		}
 
		if(isErroNullCampos){
 
			List<GrupoDTO> gruposModel = usuarioService.setGruposModel(usuarioModel);		
 
		     model.addAttribute("grupos", gruposModel);
 
		     model.addAttribute("usuarioModel", usuarioModel);
 
		     return new ModelAndView("usuario/UpdateUsuarios");	
		}
		else{

		     usuarioService.alterarUsuario(usuarioModel);
 
		}
 
		return new ModelAndView("redirect:/usuario");
	}
}
