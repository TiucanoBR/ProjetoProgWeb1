package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Carro;
import br.com.senac.repository.CarroRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository repoCarro;
	
	public Carro salvar(Carro carro) {
		return repoCarro.save(carro);
	}
	
	public List<Carro> buscarTodosCarros(){
		return repoCarro.findAll();
	}
	
	public Carro buscaPorIdCarro(Integer id) throws ObjectNotFoundException {
		Optional<Carro> carro = repoCarro.findById(id);
		return carro.orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));
	}
	
	public Carro salvarAlteracaoCarro(Carro carroAlterado) throws ObjectNotFoundException {
		Carro carroAtual = buscaPorIdCarro(carroAlterado.getId());
		carroAtual.setModelo(carroAlterado.getModelo());
		carroAtual.setChave(carroAlterado.getChave());
		return salvar(carroAtual);
	}
	
	public void excluirCarro(Integer idCarro){
		repoCarro.deleteById(idCarro);
	}
}
