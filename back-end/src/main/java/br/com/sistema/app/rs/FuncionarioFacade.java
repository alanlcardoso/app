package br.com.sistema.app.rs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.sistema.app.entidade.Funcionario;
import br.com.sistema.app.negocio.FuncionarioBO;

public class FuncionarioFacade {

	@Autowired
	private FuncionarioBO entidadeBO;

	public List<Funcionario> getTodos() {
		return entidadeBO.buscarTodos();
	}

	@GetMapping("/rs/funcionario/{id}")
	public Funcionario buscarPorId(@PathParam("id") Long id) {
		return entidadeBO.buscarPorId(id);
	}

	@PostMapping
	public void salvar(Funcionario entidade) {
		entidadeBO.salvar(entidade);
	}

	@PutMapping
	public void atualizar(Funcionario	 entidade) {
		entidadeBO.atualizar(entidade.getId(), entidade);
	}

	@DeleteMapping("/rs/funcionario/{id}")
	public void excluir(@PathParam("id") Long id) {
		entidadeBO.excluir(id);
	}
}
