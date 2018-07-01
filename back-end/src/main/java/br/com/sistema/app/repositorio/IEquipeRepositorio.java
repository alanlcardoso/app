package br.com.sistema.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.app.entidade.Equipe;

public interface IEquipeRepositorio extends JpaRepository<Equipe, Long> {

	
}