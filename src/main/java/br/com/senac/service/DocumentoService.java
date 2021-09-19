package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Documento;
import br.com.senac.repository.DocumentoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class DocumentoService {
	
	@Autowired
	DocumentoRepository docRepo;
	
	public Documento salvar(Documento documento) {
		return docRepo.save(documento);
	}
	
	public List<Documento> salvarTodos(List<Documento> documentos){
		return docRepo.saveAll(documentos);
	}
	
	public List<Documento> buscarTodosDocumentos(){
		return docRepo.findAll();
	}
	
	public Documento buscarPorIdDoc(Integer id) throws ObjectNotFoundException {
		Optional<Documento> doc = docRepo.findById(id);
		return doc.orElseThrow(() -> new ObjectNotFoundException("Documento não encontrado!"));
	}
	
	public Documento salvarAlteracaoDoc(Documento docAlterado) throws ObjectNotFoundException {
		Documento docAtual = buscarPorIdDoc(docAlterado.getId());
		docAtual.setNome(docAlterado.getNome());
		docAtual.setCodigo(docAlterado.getCodigo());
		return salvar(docAtual);
	}
	
	public void excluirDoc(Integer idDoc) {
		docRepo.deleteById(idDoc);
	}
}
