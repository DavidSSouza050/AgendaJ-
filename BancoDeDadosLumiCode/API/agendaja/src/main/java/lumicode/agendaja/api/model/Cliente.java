package lumicode.agendaja.api.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;


@Entity
@Table(name = "tbl_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@NotNull
	@Size(min = 3, max = 50, message = "O Nome deve conter no Minimo 3 Carateres!")
	private String nome;
	@NotNull
	@Size(min = 3, max = 50, message = "O Sobrenome deve conter no Minimo 3 Caracteres!")
	private String sobrenome;
	@NotNull
	@Size(max = 15, message = "Digite o Numero do Celular corretamente!")
	private String celular;
	@CPF
	@NotNull
	@Size(max = 14, message = "Digite CPF corretemente!")
	private String cpf;
	@NotNull
	@Size(max = 1, message = "Permitido 1 caracter!")
	private String sexo;
	@NotNull
	private String dataNascimento;
	@NotNull
	@Size(min = 10, max = 50 , message = "O E-mail deve conter no Minimo 10 catacteres!")
	private String email;
	@NotNull
	@Size(min = 8, max = 255, message="a senha deve conter no minimo 8 caracteres")
	private String senha;
	private String fotoCliente;
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoCliente() {
		return fotoCliente;
	}

	public void setFotoCliente(String fotoCliente) {
		this.fotoCliente = fotoCliente;
	}



	
		

}