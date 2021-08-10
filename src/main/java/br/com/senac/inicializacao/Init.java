package br.com.senac.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.domain.Aluno;
import br.com.senac.domain.Professor;
import br.com.senac.service.AlunoService;
import br.com.senac.service.ProfessorService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoService alunoService;
	
	@Autowired
	ProfessorService profService;
	
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
