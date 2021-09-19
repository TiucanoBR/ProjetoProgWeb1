package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Chave;
import br.com.senac.service.ChaveService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("chave")
public class ChaveController {
	
	@Autowired
	private ChaveService chaveService;
	
	@GetMapping("/listar")
	public ModelAndView ListaTodasChaves() {
		ModelAndView mv = new ModelAndView("chave/listarChave");
		mv.addObject("chaves",chaveService.buscarTodasChaves());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarChave() {
		ModelAndView mv = new ModelAndView("chave/cadastrarChave");
		mv.addObject("chaveNova", new Chave());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvarChave(Chave chave) {
		chaveService.salvar(chave);
		return "redirect:/chave/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarChave(@PathVariable("id") Integer idChave) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("chave/alterarChave");
		mv.addObject("chave", chaveService.buscarPorIdChave(idChave));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterarChave(Chave chav) throws ObjectNotFoundException {
		chaveService.salvarAlteracaoChave(chav);
		return "redirect:/chave/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirChave(@PathVariable("id") Integer idChave) {
		chaveService.excluirChave(idChave);
		return "redirect:/chave/listar";
	}
	
}
