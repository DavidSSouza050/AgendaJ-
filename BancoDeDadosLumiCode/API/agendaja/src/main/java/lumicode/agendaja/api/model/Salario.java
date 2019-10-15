package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_salario")
public class Salario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSalario;
	private float salario;
	private Integer percentual;
	@ManyToOne
	@JoinColumn(name = "id_tipo_salario")
	private TipoSalario tipoSalario;
	private String criadoEm;
	private String atualizadoEm;

	public Long getIdSalario() {
		return idSalario;
	}

	public void setIdSalario(Long idSalario) {
		this.idSalario = idSalario;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	

	public Integer getPercentual() {
		return percentual;
	}

	public void setPercentual(Integer percentual) {
		this.percentual = percentual;
	}

	public TipoSalario getTipoSalario() {
		return tipoSalario;
	}

	public void setTipoSalario(TipoSalario tipoSalario) {
		this.tipoSalario = tipoSalario;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
}
