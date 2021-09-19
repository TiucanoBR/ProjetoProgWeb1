package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Acessorio;
import br.com.senac.service.AcessorioService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/acessorio")
public class AcessorioController {
	
	@Autowired
	AcessorioService acessorioService;
	
	@GetMapping("/listar")
	public ModelAndView listarAcessorios() {
		ModelAndView mv = new ModelAndView("acessorio/listarAcessorio");
		mv.addObject("acessorios", acessorioService.buscarTodosAcessorios());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarAcessorio() {
		ModelAndView mv = new ModelAndView("acessorio/cadastrarAcessorio");
		mv.addObject("acessorioNovo", new Acessorio());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvar(Acessorio acessorio) {
		acessorioService.salvar(acessorio);
		return "redirect:/acessorio/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("acessorio/alterarAcessorio");
		mv.addObject("acessorio", acessorioService.buscarPorId(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterar(Acessorio acessorio) throws ObjectNotFoundException {
		acessorioService.salvarAlteracao(acessorio);
		return "redirect:/acessorio/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletar(@PathVariable("id") Integer id) {
		acessorioService.excluir(id);
		return "redirect:/acessorio/listar";
	}
}
