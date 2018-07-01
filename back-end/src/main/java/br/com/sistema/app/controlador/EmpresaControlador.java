package br.com.sistema.app.controlador;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.app.entidade.Empresa;
import br.com.sistema.app.eventos.RecursoCriadoEvento;
import br.com.sistema.app.negocio.EmpresaBO;

@RestController
@RequestMapping("/empresa")
public class EmpresaControlador {

	@Autowired
	private EmpresaBO entidadeBO;

	@Autowired
	private ApplicationEventPublisher publicador;

	@GetMapping
	public List<Empresa> listar() {
		return entidadeBO.buscarTodos();
	}
	
	@PostMapping
	public ResponseEntity<Empresa> salvar(@Valid @RequestBody Empresa entidade, HttpServletResponse response) {
		Empresa entidadeSalvo = entidadeBO.salvar(entidade);
		publicador.publishEvent(new RecursoCriadoEvento(this, response, entidadeSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(entidadeSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
		Empresa entidade = entidadeBO.buscarPorId(id);
		return entidade != null ? ResponseEntity.ok(entidade) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		entidadeBO.excluir(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizarPorId(@PathVariable Long id, @Valid @RequestBody Empresa entidade) {
		return ResponseEntity.ok(entidadeBO.atualizar(id, entidade));
	}
}