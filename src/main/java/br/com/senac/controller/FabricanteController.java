package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Fabricante;
import br.com.senac.service.FabricanteService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/fabricante")
public class FabricanteController {
	
	@Autowired
	FabricanteService fabService;
	
	@GetMapping("/listar")
	public ModelAndView listarFabricantes() {
		ModelAndView mv = new ModelAndView("fab/listarFab");
		mv.addObject("fabricantes", fabService.buscarTodosFabricantes());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarFabricante() {
		ModelAndView mv = new ModelAndView("fab/cadastrarFab");
		mv.addObject("fabNovo", new Fabricante());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvarFabricante(Fabricante fabricante) {
		fabService.salvar(fabricante);
		return "redirect:/fabricante/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarFabricante(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("fab/alterarFab");
		mv.addObject("fabricante", fabService.buscarPorIdFab(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterarFabricante(Fabricante fabricante) throws ObjectNotFoundException {
		fabService.salvarAlteracaoFab(fabricante);
		return "redirect:/fabricante/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletarFabricante(@PathVariable("id") Integer id) {
		fabService.excluirFab(id);
		return "redirect:/fabricante/listar";
	}
}
