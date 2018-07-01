package br.com.sistema.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.app.entidade.Funcionario;

public interface IFuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

}