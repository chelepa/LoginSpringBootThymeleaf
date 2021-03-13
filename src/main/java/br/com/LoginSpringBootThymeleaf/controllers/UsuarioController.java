package br.com.LoginSpringBootThymeleaf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
 
		/*LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA*/
		model.addAttribute("grupos", grupoService.consultarGrupos());
 
		/*OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS*/
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
		
		List<GrupoDTO> gruposModel =grupoService.consultarGrupos();
		
		gruposModel.forEach(grupo ->{
			 
			if(usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() > 0){

				usuarioModel.getGrupos().forEach(grupoSelecionado->{

					if(grupoSelecionado!= null){
						if(grupo.getCodigo().equals(grupoSelecionado))
							grupo.setChecked(true);
					}					
				});				
			}

		});
		
		model.addAttribute("grupos", gruposModel);
		
		model.addAttribute("usuarioModel", usuarioModel);
		
		return new ModelAndView("usuario/CadastroUsuarios");
		
	}

}
