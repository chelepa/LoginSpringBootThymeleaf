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

import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResponseDTO;
import br.com.LoginSpringBootThymeleaf.dto.Grupo.GrupoResquestDTO;
import br.com.LoginSpringBootThymeleaf.dto.Permissoes.PermissaoResponseDTO;
import br.com.LoginSpringBootThymeleaf.services.Grupo.GrupoService;
import br.com.LoginSpringBootThymeleaf.services.Permissoes.PermissoesService;

@Controller
public class GrupoController {
	
	@Autowired
	private GrupoService service;
	
	@Autowired
	private PermissoesService permissoesService;
	
	@GetMapping(value="/grupos")
	public ModelAndView consultarGrupos(Model model) {
 
		List<GrupoResponseDTO> permissoes = service.findAllGrupos();
		
		model.addAttribute("GrupoResponseDTO", permissoes);
 
	    return new ModelAndView("grupo/ListagemGrupo");
	}
	
	@GetMapping(value="/grupo/cadastro")
	public ModelAndView cadastroGrupo(Model model) {
		
		model.addAttribute("GrupoResquestDTO", new GrupoResquestDTO());
		model.addAttribute("permissoes", permissoesService.getAllPermissao());
 
		return new ModelAndView("grupo/CadastroGrupo");
	}
	
	@PostMapping(value="/grupo/salvarGrupo")
	public ModelAndView salvarGrupo(@ModelAttribute @Valid GrupoResquestDTO request, final BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		
		if(!result.hasErrors()){
			
			service.saveGrupo(request);
			
			redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
			
			return new ModelAndView("redirect:/grupos");
			
		}
		
		model.addAttribute("GrupoResquestDTO", new GrupoResquestDTO());
		
		return new ModelAndView("grupo/CadastroGrupo");
	}
	
	@PostMapping(value="/grupo/excluir/{codigoGrupo}")
	public ModelAndView excluir(@PathVariable("codigoGrupo") Integer codigoGrupo){
 
		service.delete(codigoGrupo);
 
		return new ModelAndView("redirect:/grupos");
	}
	
	@GetMapping(value="/grupo/editar/{codigoGrupo}")
	public ModelAndView getViewUpdateGrupo(@PathVariable("codigoGrupo") Integer codigoGrupo, Model model) {

		GrupoResponseDTO response = service.getGrupoById(codigoGrupo);
		
		List<PermissaoResponseDTO> responsePermissao = service.permissaoSelected(response.getPermissoes());
		
		model.addAttribute("permissoes", responsePermissao);
		
		model.addAttribute("GrupoResponseDTO", response);

	    return new ModelAndView("grupo/UpdateGrupo");
	 }
	
	@PostMapping(value="/grupo/saveUpdateGrupo")
	public ModelAndView salvarAlteracao(@ModelAttribute @Valid GrupoResquestDTO request, Model model, RedirectAttributes redirectAttributes){

		service.saveUpdateGrupo(request);
		
		return new ModelAndView("redirect:/grupos");
	}

}
