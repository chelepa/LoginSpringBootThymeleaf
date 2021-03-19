package br.com.LoginSpringBootThymeleaf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoDTO;
import br.com.LoginSpringBootThymeleaf.services.Permissoes.PermissoesService;

@Controller
public class PermissoesController {
	
	@Autowired
	private PermissoesService permissoesService;
	
	@GetMapping(value="/permissoes")
	public ModelAndView consultarPermissoes(Model model) {
 
		List<PermissaoDTO> permissoes = permissoesService.getAllPermissao();
		
		model.addAttribute("PermissaoDTO", permissoes);
 
	    return new ModelAndView("permissao/ListagemPermissao");
	}
	
	@GetMapping(value="/permissoes/cadastro")
	public ModelAndView cadastroPermissoes(Model model) {
		
		model.addAttribute("permissaoDTO", new PermissaoDTO());
 
		return new ModelAndView("permissao/CadastroPermissao");
	}
	
	@PostMapping(value="/permissoes/salvarPermissoes")
	public ModelAndView salvarPermissoes(@ModelAttribute @Valid PermissaoDTO permissaoDTO, final BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()){
			
			permissoesService.savePermissao(permissaoDTO);
			
			redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
			
			return new ModelAndView("redirect:/permissoes");
			
		}
		
		model.addAttribute("permissaoDTO", permissaoDTO);
		
		return new ModelAndView("permissao/CadastroPermissao");
	}
	
	@PostMapping(value="/permissoes/excluir/{codigoUsuario}")
	public ModelAndView excluir(@PathVariable("codigoUsuario") Integer codigoUsuario){
 
		permissoesService.delete(codigoUsuario);
 
		return new ModelAndView("redirect:/permissoes");
	}

}
