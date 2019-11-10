package lumicode.agendaja.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tbl_salario")
public class Salario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSalario;
	@Column(name = "salario")
	private Double salario;
	private Double percentual;

	public Long getIdSalario() {
		return idSalario;
	}

	public void setIdSalario(Long idSalario) {
		this.idSalario = idSalario;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	
	
	
	
}
