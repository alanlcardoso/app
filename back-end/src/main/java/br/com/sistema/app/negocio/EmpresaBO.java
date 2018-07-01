package br.com.sistema.app.negocio;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.app.entidade.Empresa;
import br.com.sistema.app.repositorio.IEmpresaRepositorio;

@Service
public class EmpresaBO {

	@Autowired
	private IEmpresaRepositorio repositorio;

	public Empresa salvar(Empresa empresa) {
		empresa.setCnpj(empresa.getCnpj().replace(".", "").replace("-", "").replace("/", ""));
		return repositorio.save(empresa);
	}

	public void excluir(Long id) {
		repositorio.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return repositorio.findAll();
	}

	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return repositorio.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

	public Empresa atualizar(Long id, Empresa grupo) {
		Empresa entidadeSalvo = buscarPorId(id);
		BeanUtils.copyProperties(grupo, entidadeSalvo, "id");
		return salvar(grupo);
	}
}