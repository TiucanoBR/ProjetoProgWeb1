package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {
	
//	localhost:8080/aluno/listarAlunos
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping("/listarAlunos")
	public ModelAndView listaTodosAlunos() {
		//Vai pro html
		ModelAndView mv = new ModelAndView("aluno/listarAluno");
		mv.addObject("alunos",alunoService.buscarTodosAlunos());
		return mv;
	}
	
}
