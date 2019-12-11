package lumicode.agendaja.api.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "view_servico_pendente")
public class ServicoPendenteVIEW {
	@Id
	@Column(name = "id")
	private Long idAgendamento;
	@Column(name = "id_funcionario")
	private Integer idFunncionario;
	@Column(name = "nome_funcionario")
	private String nomeFuncionario;
	@Column(name = "id_estabelecimento")
	private Integer idEstabelecimento;
	@Column(name = "nome_estabelecimento")
	private String nomeEstabelecimento;
	@Column(name = "preco")
	private Double preco;
	@Column(name = "cliente")
	private Integer cliente;
	@Column(name = "nome_cliente")
	private String nomeCliente;
	@Column(name = "foto_cliente")
	private String fotoCliente;
	@Column(name = "celular_cliente")
	private String celularCliente;
	@Column(name = "servico")
	private String servico;
	@Column(name = "categoria")
	private String categoria;
	@Column(name = "duracao_servico")
	private Integer duracaoServico;
	@Column(name = "data_hora")
	private String dataHora;
	@Column(name = "finalizado")
	private Integer finalizado;
	@Column(name = "cancelado")
	private char cancelado;

	
	public Long getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	

	public Integer getIdFunncionario() {
		return idFunncionario;
	}

	public void setIdFunncionario(Integer idFunncionario) {
		this.idFunncionario = idFunncionario;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(Integer idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}

	
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
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

	public String getCelularCliente() {
		return celularCliente;
	}

	public void setCelularCliente(String celularCliente) {
		this.celularCliente = celularCliente;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getDuracaoServico() {
		return duracaoServico;
	}

	public void setDuracaoServico(Integer duracaoServico) {
		this.duracaoServico = duracaoServico;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public Integer getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Integer finalizado) {
		this.finalizado = finalizado;
	}

	public char getCancelado() {
		return cancelado;
	}

	public void setCancelado(char cancelado) {
		this.cancelado = cancelado;
	}
	
	
}
