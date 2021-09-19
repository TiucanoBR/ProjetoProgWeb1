package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Chave;
import br.com.senac.repository.ChaveRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ChaveService {

	@Autowired
	ChaveRepository chaveRepo;
	
	public Chave salvar(Chave chave) {
		return chaveRepo.save(chave);
	}
	
	public List<Chave> buscarTodasChaves(){
		return chaveRepo.findAll();
	}
	
	public Chave buscarPorIdChave(Integer id) throws ObjectNotFoundException {
		Optional<Chave> chave = chaveRepo.findById(id);
		return chave.orElseThrow(() -> new ObjectNotFoundException("Chave não encontrada!"));
	}
	
	public Chave salvarAlteracaoChave(Chave chaveAlterada) throws ObjectNotFoundException {
		Chave chaveAtual = buscarPorIdChave(chaveAlterada.getId());
		chaveAtual.setCodigo(chaveAlterada.getCodigo());
		return salvar(chaveAtual);
	}
	
	public void excluirChave(Integer idChave) {
		chaveRepo.deleteById(idChave);
	}
}
