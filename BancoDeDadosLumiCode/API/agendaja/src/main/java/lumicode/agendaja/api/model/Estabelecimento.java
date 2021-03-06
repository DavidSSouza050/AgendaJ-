package lumicode.agendaja.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "tbl_estabelecimento")
public class Estabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long idEstabelecimento;
	@NonNull
	@CNPJ 
	@Size(max = 20, message = "O CNPJ tem que conter 20 caracteres!")
	private String cnpj;
	@NonNull
	@Size(min = 5, max = 150, message = "A Razão social tem que conter no minimo 5 caracteres!")
	private String razaoSocial;
	@NonNull
	@Size(min = 5, max = 100, message = "O nome do estabelecimeto deve conter no minimo 5 caracteres!")
	private String nomeEstabelecimento;
	@Size(max = 15, message = "Digite o celular corretamente!")
	private String celular;
	private String foto;
	@NonNull
	@Size(max = 15, message = "Digite o número telefone corretamente!")
	private String telefone;
	@NonNull
	@Size(min = 10, max = 50, message = "O E-mail Tem que conter mais de 10 caracteres!")
	private String email;
	@NonNull
	@Size(min = 8, max = 255, message = "A senha que conter 8 ou mais caracteres!")
	private String senha;
	@OneToMany(mappedBy = "estabelecimento")
	private List<HorarioEstabelecimento> horarioEstabelecimento;
	
	
	
	public Long getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Long idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<HorarioEstabelecimento> getHorarioEstabelecimento() {
		return horarioEstabelecimento;
	}

	public void setHorarioEstabelecimento(List<HorarioEstabelecimento> horarioEstabelecimento) {
		this.horarioEstabelecimento = horarioEstabelecimento;
	}



	




}
