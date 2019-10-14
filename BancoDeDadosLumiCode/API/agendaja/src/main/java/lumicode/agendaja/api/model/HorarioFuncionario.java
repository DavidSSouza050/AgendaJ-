package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_horario_funcionario")
public class HorarioFuncionario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idHorarioFuncionario;
	private String horaEntrada;
	private String horaSaida;
	private String horaPausa;
	private String duaracaoPausa;
	@ManyToOne
	@JoinColumn(name = "id_dia_semana")
	private DiaSemana diaSemana;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	private String criadoEm;
	private String atualizadoEm;

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

	public String getHoraPausa() {
		return horaPausa;
	}

	public void setHoraPausa(String horaPausa) {
		this.horaPausa = horaPausa;
	}

	public String getDuaracaoPausa() {
		return duaracaoPausa;
	}

	public void setDuaracaoPausa(String duaracaoPausa) {
		this.duaracaoPausa = duaracaoPausa;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
