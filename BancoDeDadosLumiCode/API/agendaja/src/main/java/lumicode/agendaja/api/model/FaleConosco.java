package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_fale_conosco")
public class FaleConosco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFaleConosco;
	@NotNull
	@Size(min = 3, max = 50, message = "O Nome deve conter no Minimo 3 Carateres!")
	private String nome;
	@NotNull
	@Size(min = 10, max = 50, message = "O E-mail deve conter no Minimo 10 catacteres!")
	private String email;
	@NotNull
	private String comentario;
	@ManyToOne
	@JoinColumn(name = "id_assunto")
	private Assunto assunto;
	private String criadoEm;

	public Long getIdFaleConosco() {
		return idFaleConosco;
	}

	public void setIdFaleConosco(Long idFaleConosco) {
		this.idFaleConosco = idFaleConosco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	
	
}
