package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Documento;
import br.com.senac.service.DocumentoService;
import javassist.tools.rmi.ObjectNotFoundException;

@Controller
@RequestMapping("/doc")
public class DocumentoController {
	
	@Autowired
	DocumentoService docService;
	
	@GetMapping("/listar")
	public ModelAndView listarDocumentos() {
		ModelAndView mv = new ModelAndView("documento/listarDoc");
		mv.addObject("documentos", docService.buscarTodosDocumentos());
		return mv;
	}
	
	@GetMapping("/cadastrar")
	public ModelAndView cadastrarDocumento() {
		ModelAndView mv = new ModelAndView("documento/cadastrarDoc");
		mv.addObject("documentoNovo", new Documento());
		return mv;
	}
	
	@PostMapping("/salvar")
	public String salvarDocumento(Documento documento) {
		docService.salvar(documento);
		return "redirect:/doc/listar";
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarDocumento(@PathVariable("id") Integer id) throws ObjectNotFoundException {
		ModelAndView mv = new ModelAndView("documento/alterarDoc");
		mv.addObject("documento", docService.buscarPorIdDoc(id));
		return mv;
	}
	
	@PostMapping("/alterar")
	public String alterarDocumento(Documento documento) throws ObjectNotFoundException {
		docService.salvarAlteracaoDoc(documento);
		return "redirect:/doc/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String deletarDocumento(@PathVariable("id") Integer id) {
		docService.excluirDoc(id);
		return "redirect:/doc/listar";
	}
}
