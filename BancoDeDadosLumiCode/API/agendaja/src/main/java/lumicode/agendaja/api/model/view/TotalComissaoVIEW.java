package lumicode.agendaja.api.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "view_total_comissao")
public class TotalComissaoVIEW {
	@Id
	@Column(name = "funcionario")
	private Long funcionario;
	@Column(name = "totalComissao")
	private Double totalComissao;

	public Long getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Long funcionario) {
		this.funcionario = funcionario;
	}

	public Double getTotalComissao() {
		return totalComissao;
	}

	public void setTotalComissao(Double totalComissao) {
		this.totalComissao = totalComissao;
	}
	
}
