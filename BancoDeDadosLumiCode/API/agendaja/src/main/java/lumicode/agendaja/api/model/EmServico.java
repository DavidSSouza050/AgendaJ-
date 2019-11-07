package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;
import lumicode.agendaja.api.model.dto.FuncionarioDTO;


@Entity
@Table(name = "tbl_em_servico")
public class EmServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmServico;
	@ManyToOne
	@JoinColumn(name="id_estabelecimento")
	private EstabelecimentoDTO estabelecimento;
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private FuncionarioDTO funcionario;
	private String diaMes;
	private String mes;
	
	private String ano;
	private String ocupadoInicio;
	private String ocupadoFim;

	public Long getIdEmServico() {
		return idEmServico;
	}

	public void setIdEmServico(Long idEmServico) {
		this.idEmServico = idEmServico;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	

	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getDiaMes() {
		return diaMes;
	}

	public void setDiaMes(String diaMes) {
		this.diaMes = diaMes;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getOcupadoInicio() {
		return ocupadoInicio;
	}

	public void setOcupadoInicio(String ocupadoInicio) {
		this.ocupadoInicio = ocupadoInicio;
	}

	public String getOcupadoFim() {
		return ocupadoFim;
	}

	public void setOcupadoFim(String ocupadoFim) {
		this.ocupadoFim = ocupadoFim;
	}
	
}
