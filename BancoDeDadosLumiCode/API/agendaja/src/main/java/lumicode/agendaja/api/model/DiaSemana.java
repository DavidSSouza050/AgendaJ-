package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dia_semana")
public class DiaSemana {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDiaSemana;
	private String diaSemana;

	public Long getIdDiaSemana() {
		return idDiaSemana;
	}

	public void setIdDiaSemana(Long idDiaSemana) {
		this.idDiaSemana = idDiaSemana;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
}
