package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_horario_estabelecimento")
public class HorarioEstabelecimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHorarioEstabelecimento;
	private String fechaAs;
	private String abreAs;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private Estabelecimento estabelecimento;
	@ManyToOne
	@JoinColumn(name = "id_dia_semana")
	private DiaSemana diaSemana;
	private String criadoEm;
	private String atualizadoEm;

	public Long getIdHorarioEstabelecimento() {
		return idHorarioEstabelecimento;
	}

	public void setIdHorarioEstabelecimento(Long idHorarioEstabelecimento) {
		this.idHorarioEstabelecimento = idHorarioEstabelecimento;
	}

	public String getFechaAs() {
		return fechaAs;
	}

	public void setFechaAs(String fechaAs) {
		this.fechaAs = fechaAs;
	}

	public String getAbreAs() {
		return abreAs;
	}

	public void setAbreAs(String abreAs) {
		this.abreAs = abreAs;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
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