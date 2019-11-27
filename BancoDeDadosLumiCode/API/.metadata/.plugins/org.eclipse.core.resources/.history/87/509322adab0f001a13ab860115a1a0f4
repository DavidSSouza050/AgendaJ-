package lumicode.agendaja.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.ClienteDTO;
import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;

@Entity
@Table(name = "tbl_avaliacao_estabalecimento")
public class AvaliacaoEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAvaliacaoEstabelecimento;
	@ManyToOne
	@JoinColumn(name="id_estabelecimento")
	private EstabelecimentoDTO estabelecimento;
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private ClienteDTO cliente;
	@Column(name="avaliacao")
	private Integer avaliacao;
	@Column(name="comentario")
	private String comentario;
	
	public Long getIdAvaliacaoEstabelecimento() {
		return idAvaliacaoEstabelecimento;
	}

	public void setIdAvaliacaoEstabelecimento(Long idAvaliacaoEstabelecimento) {
		this.idAvaliacaoEstabelecimento = idAvaliacaoEstabelecimento;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
