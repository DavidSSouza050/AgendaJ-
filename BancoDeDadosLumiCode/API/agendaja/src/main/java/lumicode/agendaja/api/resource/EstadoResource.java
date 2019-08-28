package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.Estado;
import lumicode.agendaja.api.repository.EstadoRepository;

@RestController
@RequestMapping("/estado")
public class EstadoResource {

	@Autowired
	private EstadoRepository estadoRepository;
	
	//pegando todos os estados
	@GetMapping
	private List<Estado> getEstado(){
		return estadoRepository.findAll();
	}
	
	//pegando um estado
	@GetMapping("/{id}")
	private Estado visualizarEstado(@PathVariable Long id) {
		return estadoRepository.findById(id).get();
	}
	
	
	
}
