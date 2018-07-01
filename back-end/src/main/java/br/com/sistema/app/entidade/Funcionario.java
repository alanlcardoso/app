package br.com.sistema.app.entidade;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 201803282042L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "matricula", nullable = true, unique = false)
	private Long matricula;

	@Column(name = "nome", nullable = false, unique = false)
	@NotBlank(message = "Nome do Colaborador é obrigatório.")
	private String nome;

	@Column(name = "cpf", nullable = false, unique = false)
	@NotBlank(message = "CPF é obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	@Column(name = "nascimento_data", nullable = false, unique = false)
	@NotNull(message = "Data de nascimento do colaborador é obrigatório")
	private LocalDate nascimentoData;

	@Email(message = "Informe um e-mail válido.")
	@Column(name = "email", nullable = true, unique = false)
	private String email;

	@NotBlank(message = "Nome da mãe é obrigatório.")
	@Column(name = "mae_nome", nullable = true, unique = false)
	private String maeNome;

	@Column(name = "pai_nome", nullable = true, unique = false)
	private String paiNome;

	@NotBlank(message = "Telefone celular é obrigatório.")
	@Column(name = "telefone_celular", nullable = true, unique = false)
	private String telefoneCelular;

	@Column(name = "admissao_data", nullable = false, unique = false)
	@NotNull(message = "Data de admissão é obrigatório")
	private LocalDate admissaoData;

	@Column(name = "desligamento_data", nullable = true, unique = false)
	private LocalDate desligamentoData;

	@ManyToOne
	@JoinColumn(name = "id_equipe", nullable = false, unique = false)
	private Equipe equipe;

	@ManyToOne
	@JoinColumn(name = "id_cargo", nullable = false, unique = false)
	@NotNull(message = "Cargo é obrigatório")
	private Cargo cargo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getNascimentoData() {
		return nascimentoData;
	}

	public void setNascimentoData(LocalDate nascimentoData) {
		this.nascimentoData = nascimentoData;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaeNome() {
		return maeNome;
	}

	public void setMaeNome(String maeNome) {
		this.maeNome = maeNome;
	}

	public String getPaiNome() {
		return paiNome;
	}

	public void setPaiNome(String paiNome) {
		this.paiNome = paiNome;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public LocalDate getAdmissaoData() {
		return admissaoData;
	}

	public void setAdmissaoData(LocalDate admissaoData) {
		this.admissaoData = admissaoData;
	}

	public LocalDate getDesligamentoData() {
		return desligamentoData;
	}

	public void setDesligamentoData(LocalDate desligamentoData) {
		this.desligamentoData = desligamentoData;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}