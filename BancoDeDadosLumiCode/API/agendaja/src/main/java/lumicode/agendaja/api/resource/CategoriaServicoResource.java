package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.CategoriaServico;
import lumicode.agendaja.api.repository.CategoriaServicoRepository;

@RestController
@RequestMapping("/categoriaServico")
@CrossOrigin(origins="http://localhost:3000")
public class CategoriaServicoResource {
	@Autowired
	private CategoriaServicoRepository categoriaServicoRepository;
	
	@GetMapping
	private List<CategoriaServico> getCategoriaServico(){
		return categoriaServicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private CategoriaServico visualizarCategoriaServico(@PathVariable Long id) {
		return categoriaServicoRepository.findById(id).get();
	}
	
	
	
}