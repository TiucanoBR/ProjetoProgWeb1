package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Professor;
import br.com.senac.repository.ProfessorRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepository repoProfessor;
	
	public Professor salvar(Professor professor) {
		return repoProfessor.save(professor);
	}
	
	public List<Professor> buscarTodosProfessores(){
		return repoProfessor.findAll();
	}
	
	public Professor buscaPorIDProf(Integer id) throws ObjectNotFoundException {
		Optional<Professor> prof = repoProfessor.findById(id);
		return prof.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado: "+ id));
	}
	
	public Professor salvarAlteracaoProf(Professor profAlterado) throws ObjectNotFoundException {
		Professor profAtual = buscaPorIDProf(profAlterado.getId());
		profAtual.setNome(profAlterado.getNome());
		return salvar(profAtual);
	}
	
	public void excluirProf(Integer idProf) {
		repoProfessor.deleteById(idProf);
	}
	
}
