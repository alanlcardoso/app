package br.com.sistema.app.negocio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.app.entidade.Cargo;
import br.com.sistema.app.repositorio.ICargoRepositorio;

@Service
public class CargoBO {

	@Autowired
	private ICargoRepositorio repositorio;

	public Cargo salvar(Cargo cargo) {
		return repositorio.save(cargo);
	}

	public void excluir(Long id) {
		repositorio.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		return repositorio.findAll();
	}

	@Transactional(readOnly = true)
	public Cargo buscarPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Cargo atualizar(Long id, Cargo grupo) {
		Cargo entidadeSalvo = buscarPorId(id);
		BeanUtils.copyProperties(grupo, entidadeSalvo, "id");
		return salvar(grupo);
	}
	
	public void atualizarPropriedadeDescricao(Long id, String descricao) {
		Cargo grupo = buscarPorId(id);
		grupo.setDescricao(descricao);
		repositorio.save(grupo);
	}
}
