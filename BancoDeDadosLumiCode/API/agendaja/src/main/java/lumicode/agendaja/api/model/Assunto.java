package lumicode.agendaja.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_assunto")
public class Assunto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAssunto;
	private String assunto;
	
	
	public Long getIdAssunto() {
		return idAssunto;
	}
	public void setIdAssunto(Long idAssunto) {
		this.idAssunto = idAssunto;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	
	
	
}
