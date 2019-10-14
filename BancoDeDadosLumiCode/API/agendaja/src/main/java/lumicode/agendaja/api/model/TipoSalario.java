package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_tipo_salario")
public class TipoSalario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoSalario;
	private String tipoSalario;

	public Long getIdTipoSalario() {
		return idTipoSalario;
	}

	public void setIdTipoSalario(Long idTipoSalario) {
		this.idTipoSalario = idTipoSalario;
	}

	public String getTipoSalario() {
		return tipoSalario;
	}

	public void setTipoSalario(String tipoSalario) {
		this.tipoSalario = tipoSalario;
	}
	

}
