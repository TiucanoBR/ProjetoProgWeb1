package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Aluno;
import br.com.senac.repository.AlunoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AlunoService {
	
	@Autowired
	AlunoRepository repoAluno;
	
	public Aluno salvar(Aluno aluno) {
		return repoAluno.save(aluno);
	}
	
	public List<Aluno> buscarTodosAlunos(){
		return repoAluno.findAll();
	}
	
	public Aluno buscaPorIdAluno(Integer id) throws ObjectNotFoundException{
		Optional<Aluno> aluno = repoAluno.findById(id);
		return aluno.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado: "+ id));
	}
	
	public Aluno salvarAltaracaoAluno(Aluno alunoAlterado) throws ObjectNotFoundException {
		Aluno alunoAtual = buscaPorIdAluno(alunoAlterado.getId());
		alunoAtual.setNome(alunoAlterado.getNome());
		return salvar(alunoAtual);
	}
	
	public void excluirAluno(Integer idAluno) {
		repoAluno.deleteById(idAluno);
	}
}
