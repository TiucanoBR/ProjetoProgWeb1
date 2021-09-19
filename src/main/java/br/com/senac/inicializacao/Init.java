package br.com.senac.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Aluno;
import br.com.senac.domain.Carro;
import br.com.senac.domain.Chave;
import br.com.senac.domain.Documento;
import br.com.senac.domain.Professor;
import br.com.senac.service.AlunoService;
import br.com.senac.service.CarroService;
import br.com.senac.service.ChaveService;
import br.com.senac.service.DocumentoService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService profService;
	
	@Autowired
	ChaveService chaveService;
	
	@Autowired
	CarroService carroService;
	
	@Autowired
	DocumentoService docService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Roger");
		alunoService.salvar(aluno1);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Maria");
		alunoService.salvar(aluno2);
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Rafael");
		alunoService.salvar(aluno3);
		
		Professor professor1 = new Professor();
		professor1.setNome("Edivan");
		profService.salvar(professor1);
		
		Professor professor2 = new Professor();
		professor2.setNome("Struc");
		profService.salvar(professor2);
		
		Professor professor3 = new Professor();
		professor3.setNome("Carlinho Prey Preyson");
		profService.salvar(professor3);
		
		Chave chave1 = new Chave();
		chave1.setCodigo("sajnwqneuowqhe");
		chaveService.salvar(chave1);
		
		Chave chave2 = new Chave();
		chave2.setCodigo("2ajqweqwewqee");
		chaveService.salvar(chave2);
		
		Carro carro1 = new Carro();
		carro1.setModelo("Tesla model X");
		carro1.setChave(chave2);
		carroService.salvar(carro1);
		
		Carro carro2 = new Carro();
		carro2.setModelo("Tesla model S");
		carro2.setChave(chave1);
		carroService.salvar(carro2);
		
		Documento doc1 = new Documento();
		doc1.setCodigo("19278364123");
		doc1.setNome("Doc232728");
		docService.salvar(doc1);
		
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		
		List<Professor> listaProfessor = profService.buscarTodosProfessores();
		
		for(Aluno aluno:listaAlunos) {
			System.out.println(aluno.getNome());
		}
		
		for(Professor professor:listaProfessor) {
			System.out.println(professor.getNome());
		}
	}
	
}
