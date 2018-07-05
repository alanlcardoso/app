package br.com.sistema.app.rs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.sistema.app.entidade.Cargo;
import br.com.sistema.app.negocio.CargoBO;

public class CargoFacade {

	@Autowired
	private CargoBO entidadeBO;

	public List<Cargo> getTodos() {
		return entidadeBO.buscarTodos();
	}

	@GetMapping("/rs/cargo/{id}")
	public Cargo buscarPorId(@PathParam("id") Long id) {
		return entidadeBO.buscarPorId(id);
	}

	@PostMapping
	public void salvar(Cargo entidade) {
		entidadeBO.salvar(entidade);
	}

	@PutMapping
	public void atualizar(Cargo entidade) {
		entidadeBO.atualizar(entidade.getId(), entidade);
	}

	@DeleteMapping("/rs/cargo/{id}")
	public void excluir(@PathParam("id") Long id) {
		entidadeBO.excluir(id);
	}
}
