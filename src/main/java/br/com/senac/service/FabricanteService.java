package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Fabricante;
import br.com.senac.repository.FabricanteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class FabricanteService {
	
	@Autowired
	FabricanteRepository FabricanteRepo;
	
	public Fabricante salvar(Fabricante fabricante) {
		return FabricanteRepo.save(fabricante);
	}
	
	public List<Fabricante> salvarTodos(List<Fabricante> fabricantes){
		return FabricanteRepo.saveAll(fabricantes);
	}
	
	public List<Fabricante> buscarTodosFabricantes(){
		return FabricanteRepo.findAll();
	}
	
	public Fabricante buscarPorIdFab(Integer id) throws ObjectNotFoundException {
		Optional<Fabricante> fab = FabricanteRepo.findById(id);
		return fab.orElseThrow(() -> new ObjectNotFoundException("Fabricante não encontrado!"));
	}
	
	public Fabricante salvarAlteracaoFab(Fabricante fabricanteAlterado) throws ObjectNotFoundException {
		Fabricante fabricanteAtual = buscarPorIdFab(fabricanteAlterado.getId());
		fabricanteAtual.setNome(fabricanteAlterado.getNome());
		return salvar(fabricanteAtual);
	}
	
	public void excluirFab(Integer idfab) {
		FabricanteRepo.deleteById(idfab);
	}
}
