package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_cidade")
public class Cidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCidade;
	private String cidade;
	@ManyToOne
	@JoinColumn(name = "id_microrregiao")
	private Microrregiao idMicrorregiao;

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Microrregiao getIdMicrorregiao() {
		return idMicrorregiao;
	}

	public void setIdMicrorregiao(Microrregiao idMicrorregiao) {
		this.idMicrorregiao = idMicrorregiao;
	}

	

}
