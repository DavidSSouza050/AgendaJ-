package lumicode.agendaja.api.model.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_servico")
public class ServicoIdDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServico;

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	
	
}
