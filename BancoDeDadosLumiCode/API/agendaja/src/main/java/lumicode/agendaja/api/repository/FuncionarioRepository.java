package lumicode.agendaja.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lumicode.agendaja.api.model.Funcionario;

public interface FuncionarioRepository 
	extends JpaRepository<Funcionario, Long>{

	@Query(value = "SELECT * FROM tbl_salario as s inner join tbl_funcionario as f " + 
			"ON s.id_salario = f.id_salario WHERE f.id_funcionario = ?1", nativeQuery= true)
	public Funcionario pegarSalarioDeFuncionario(Long idFuncionario);
	
	
	@Query("SELECT f FROM Funcionario as f WHERE f.email = ?1")
	public Funcionario verificarEmail(String email);
	
	@Query("SELECT f FROM Funcionario as f WHERE f.email = ?1 and f.senha = ?2")
	public Funcionario loginFuncionario(String email, String senha);
	
	
}
