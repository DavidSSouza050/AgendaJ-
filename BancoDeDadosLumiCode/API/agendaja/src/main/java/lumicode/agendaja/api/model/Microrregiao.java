package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_microrregiao")
public class Microrregiao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMicrorregiao;
	private String microrregiao;
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private Estado idEstado;

	public Long getIdMicrorregiao() {
		return idMicrorregiao;
	}

	public void setIdMicrorregiao(Long idMicrorregiao) {
		this.idMicrorregiao = idMicrorregiao;
	}

	public String getMicrorregiao() {
		return microrregiao;
	}

	public void setMicrorregiao(String microrregiao) {
		this.microrregiao = microrregiao;
	}

	public Estado getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}
	
}
