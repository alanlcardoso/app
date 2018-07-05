package br.com.sistema.app.rs;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.sistema.app.entidade.Empresa;
import br.com.sistema.app.negocio.EmpresaBO;

public class EmpresaFacade {

	@Autowired
	private EmpresaBO entidadeBO;

	public List<Empresa> getTodos() {
		return entidadeBO.buscarTodos();
	}

	@GetMapping("/rs/empresa/{id}")
	public Empresa buscarPorId(@PathParam("id") Long id) {
		return entidadeBO.buscarPorId(id);
	}

	@PostMapping
	public void salvar(Empresa entidade) {
		entidadeBO.salvar(entidade);
	}

	@PutMapping
	public void atualizar(Empresa entidade) {
		entidadeBO.atualizar(entidade.getId(), entidade);
	}

	@DeleteMapping("/rs/empresa/{id}")
	public void excluir(@PathParam("id") Long id) {
		entidadeBO.excluir(id);
	}
}
