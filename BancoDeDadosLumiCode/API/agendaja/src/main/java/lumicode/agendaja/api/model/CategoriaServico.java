package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_categoria_servico")
public class CategoriaServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoriaServico;
	private String categoriaServico;

	public Long getIdCategoriaServico() {
		return idCategoriaServico;
	}

	public void setIdCategoriaServico(Long idCategoriaServico) {
		this.idCategoriaServico = idCategoriaServico;
	}

	public String getCategoriaServico() {
		return categoriaServico;
	}

	public void setCategoriaServico(String categoriaServico) {
		this.categoriaServico = categoriaServico;
	}	
	
	
}
