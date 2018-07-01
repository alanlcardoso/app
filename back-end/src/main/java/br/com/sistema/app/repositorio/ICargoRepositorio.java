package br.com.sistema.app.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.app.entidade.Cargo;

public interface ICargoRepositorio extends JpaRepository<Cargo, Long> {

}