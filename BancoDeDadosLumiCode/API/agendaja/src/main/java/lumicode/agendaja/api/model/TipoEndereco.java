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
	private Long id_tipo_endereco;
	private String tipo;

	public Long getId_tipo_endereco() {
		return id_tipo_endereco;
	}

	public void setId_tipo_endereco(Long id_tipo_endereco) {
		this.id_tipo_endereco = id_tipo_endereco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
