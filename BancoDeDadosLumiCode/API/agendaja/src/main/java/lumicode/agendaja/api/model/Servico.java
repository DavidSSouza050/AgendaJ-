package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lumicode.agendaja.api.model.dto.EstabelecimentoDTO;
@Entity
@Table(name = "tbl_servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServico;
	@Size(min = 3, max = 50, message = "Minimo de caracteres do servico e 3")
	private String servico;
	private float preco;
	private int duracaoServico;
	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	private EstabelecimentoDTO estabelecimento;
	@ManyToOne
	@JoinColumn(name = "id_categoria_servico")
	private CategoriaServico categoriaServico;
	
	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getDuracaoServico() {
		return duracaoServico;
	}

	public void setDuracaoServico(int duracaoServico) {
		this.duracaoServico = duracaoServico;
	}
	
	public EstabelecimentoDTO getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(EstabelecimentoDTO estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public CategoriaServico getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(CategoriaServico categoriaServico) {
		this.categoriaServico = categoriaServico;
	}

	

	
	
	
	
	
}
