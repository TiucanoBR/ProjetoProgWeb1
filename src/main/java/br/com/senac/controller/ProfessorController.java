package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Professor;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("prof")
public class ProfessorController {
	
//	localhost:8080/prof/listarAlunos
	
	@Autowired
	private ProfessorService profService;
	
	@GetMapping("/listar")
	public ModelAndView listaTodosProfessores() {
		//Vai pro html
		ModelAndView mv = new ModelAndView("prof/listarProf");
		mv.addObject("professores",profService.buscarTodosProfessores());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarProfessor() {
		ModelAndView mv = new ModelAndView("prof/cadastrarProfessor");
		mv.addObject("profFaculdade", new Professor());
		return mv;
	}
	
	//criar rota para incluir o aluno
	@PostMapping("/salvar")
	public String salvarProfessor(Professor professor) {
		profService.salvar(professor);
		return "redirect:/prof/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarProf(@PathVariable("id") Integer idProf) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("prof/alterarProf");
		mv.addObject("prof", profService.buscaPorIDProf(idProf));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterarProf(Professor prof) throws ObjectNotFoundException {
		profService.salvarAlteracaoProf(prof);
		return "redirect:/prof/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirProf(@PathVariable("id") Integer idProf) {
		profService.excluirProf(idProf);
		return "redirect:/prof/listar";
	}
	
}
