package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_tipo_endereco")
public class TipoEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoEndereco;
	private String tipo;

	

	public Long getIdTipoEndereco() {
		return idTipoEndereco;
	}

	public void setIdTipoEndereco(Long idTipoEndereco) {
		this.idTipoEndereco = idTipoEndereco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
