package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.FuncionarioDTO;

@Entity
@Table(name = "tbl_horario_funcionario")
public class HorarioFuncionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHorarioFuncionario;
	private String horaEntrada;
	private String horaSaida;
	@ManyToOne
	@JoinColumn(name = "id_dia_semana")
	private DiaSemana diaSemana;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private FuncionarioDTO funcionario;

	public Long getIdHorarioFuncionario() {
		return idHorarioFuncionario;
	}

	public void setIdHorarioFuncionario(Long idHorarioFuncionario) {
		this.idHorarioFuncionario = idHorarioFuncionario;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
