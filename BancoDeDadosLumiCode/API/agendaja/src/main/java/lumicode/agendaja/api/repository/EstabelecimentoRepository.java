package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Estabelecimento;

public interface EstabelecimentoRepository 
	extends JpaRepository<Estabelecimento, Long>{
	
	@Query("select e from Estabelecimento e where e.email = ?1 and e.senha = ?2")
	public Estabelecimento loginEstabelecimento(String email, String senha);
	
	@Query("select e from Estabelecimento e where e.email = ?1")
	public Estabelecimento verificarEmail(String email);
	
	@Query("select e from Estabelecimento e where e.idEstabelecimento = ?1")
	public Estabelecimento getById(Long idEstabelecimento);
	
	@Query("select e from Estabelecimento e where e.foto = ?1 and e.idEstabelecimento = ?2")
	public Estabelecimento verificarImagem(String img, Long id);
	
	@Query("select e from Estabelecimento e where e.cnpj = ?1")
	public Estabelecimento verificarCNJP(String cnpj);
	
	@Query("select e from Estabelecimento e where e.razaoSocial = ?1")
	public Estabelecimento verificarRazaoSocial(String razaoSocial);
	
	@Query("select e from Estabelecimento e where e.nomeEstabelecimento = ?1")
	public Estabelecimento verificarNomeEstebelecimento(String nomeEstabelecimento);
	
}
