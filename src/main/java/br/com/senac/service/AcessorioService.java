package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Acessorio;
import br.com.senac.repository.AcessorioRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AcessorioService {
	
	@Autowired
	AcessorioRepository acessorioRepo;
	
	public Acessorio salvar(Acessorio acessorio) {
		return acessorioRepo.save(acessorio);
	}
	
	public List<Acessorio> salvarTodos(List<Acessorio> acessorios){
		return acessorioRepo.saveAll(acessorios);
	}
	
	public List<Acessorio> buscarTodosAcessorios(){
		return acessorioRepo.findAll();
	}
	
	public Acessorio buscarPorId(Integer id) throws ObjectNotFoundException {
		Optional<Acessorio> acessorio = acessorioRepo.findById(id);
		return acessorio.orElseThrow(() -> new ObjectNotFoundException("Acessorio não encontrado!"));
	}
	
	public Acessorio salvarAlteracao(Acessorio acessorioAlterado) throws ObjectNotFoundException {
		Acessorio acessorioAtual = buscarPorId(acessorioAlterado.getId());
		acessorioAtual.setNome(acessorioAlterado.getNome());
		return salvar(acessorioAtual);
	}
	
	public void excluir(Integer idAcess) {
		acessorioRepo.deleteById(idAcess);
	}
}
