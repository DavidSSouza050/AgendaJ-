package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;

@Entity
@Table(name = "tbl_foto")
public class Foto {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long idFoto;
	private String foto;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private EstabelecimentoDTO estabelecimento;

	public Long getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(Long idFoto) {
		this.idFoto = idFoto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	
	
	
	
}
