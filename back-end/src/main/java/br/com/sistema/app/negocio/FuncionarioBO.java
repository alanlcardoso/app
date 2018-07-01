package br.com.sistema.app.negocio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.app.entidade.Funcionario;
import br.com.sistema.app.repositorio.IFuncionarioRepositorio;

@Service
public class FuncionarioBO {

	@Autowired
	private IFuncionarioRepositorio repositorio;

	public Funcionario salvar(Funcionario entidade) {
		return repositorio.save(entidade);
	}

	public void excluir(Long id) {
		repositorio.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return repositorio.findAll();
	}

	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Funcionario atualizar(Long id, Funcionario grupo) {
		Funcionario entidadeSalvo = buscarPorId(id);
		BeanUtils.copyProperties(grupo, entidadeSalvo, "id");
		return salvar(grupo);
	}
}
