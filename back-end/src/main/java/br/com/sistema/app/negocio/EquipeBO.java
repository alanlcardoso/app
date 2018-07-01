package br.com.sistema.app.negocio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.app.entidade.Equipe;
import br.com.sistema.app.repositorio.IEquipeRepositorio;

@Service
public class EquipeBO {

	@Autowired
	private IEquipeRepositorio repositorio;

	public Equipe salvar(Equipe cargo) {
		return repositorio.save(cargo);
	}

	public void excluir(Long id) {
		repositorio.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Equipe> buscarTodos() {
		return repositorio.findAll();
	}

	@Transactional(readOnly = true)
	public Equipe buscarPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Equipe atualizar(Long id, Equipe grupo) {
		Equipe entidadeSalvo = buscarPorId(id);
		BeanUtils.copyProperties(grupo, entidadeSalvo, "id");
		return salvar(grupo);
	}
}
