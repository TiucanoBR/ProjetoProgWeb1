package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Carro;
import br.com.senac.service.CarroService;
import br.com.senac.service.ChaveService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("carro")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@Autowired
	private ChaveService chaveService;
	
	@GetMapping("/listar")
	public ModelAndView ListaTodosCarros() {
		ModelAndView mv = new ModelAndView("carro/listarCarro");
		mv.addObject("carros",carroService.buscarTodosCarros());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarCarro() {
		ModelAndView mv = new ModelAndView("carro/CadastrarCarro");
		mv.addObject("carroNovo", new Carro());
		mv.addObject("chaves", chaveService.buscarTodasChaves());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvarCarro(Carro carro) {
		carroService.salvar(carro);
		return "redirect:/carro/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarcarro(@PathVariable("id") Integer idCarro) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("carro/alterarCarro");
		mv.addObject("carro", carroService.buscaPorIdCarro(idCarro));
		mv.addObject("chaves", chaveService.buscarTodasChaves());
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterarcarro(Carro carro) throws ObjectNotFoundException {
		carroService.salvarAlteracaoCarro(carro);
		return "redirect:/carro/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluircarro(@PathVariable("id") Integer idcarro) {
		carroService.excluirCarro(idcarro);
		return "redirect:/carro/listar";
	}
	
}
