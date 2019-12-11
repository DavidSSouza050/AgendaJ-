package lumicode.agendaja.api.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_servicos_funcionario")
public class ServicosFuncionarioVIEW {
	@Id
	private Long id;
	@Column(name = "funcionario")
	private Long funcionario;
	@Column(name = "nome_cliente")
	private String nomeCliente;
	@Column(name = "foto_cliente")
	private String fotoCliente;
	@Column(name = "preco")
	private Double preco;
	@Column(name = "servico")
	private String servico;
	@Column(name = "mes")
	private Integer mes;
	@Column(name = "ano")
	private Integer ano;
	@Column(name = "finalizado")
	private Integer finalizado;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Long funcionario) {
		this.funcionario = funcionario;
	}
	
	

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getFotoCliente() {
		return fotoCliente;
	}

	public void setFotoCliente(String fotoCliente) {
		this.fotoCliente = fotoCliente;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Integer finalizado) {
		this.finalizado = finalizado;
	}

	
}
