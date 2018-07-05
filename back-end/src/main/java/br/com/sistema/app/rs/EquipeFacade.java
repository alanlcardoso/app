package br.com.sistema.app.rs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.sistema.app.entidade.Equipe;
import br.com.sistema.app.negocio.EquipeBO;

public class EquipeFacade {

	@Autowired
	private EquipeBO entidadeBO;

	public List<Equipe> getTodos() {
		return entidadeBO.buscarTodos();
	}

	@GetMapping("/rs/equipe/{id}")
	public Equipe buscarPorId(@PathParam("id") Long id) {
		return entidadeBO.buscarPorId(id);
	}

	@PostMapping
	public void salvar(Equipe entidade) {
		entidadeBO.salvar(entidade);
	}

	@PutMapping
	public void atualizar(Equipe entidade) {
		entidadeBO.atualizar(entidade.getId(), entidade);
	}

	@DeleteMapping("/rs/equipe/{id}")
	public void excluir(@PathParam("id") Long id) {
		entidadeBO.excluir(id);
	}
}
