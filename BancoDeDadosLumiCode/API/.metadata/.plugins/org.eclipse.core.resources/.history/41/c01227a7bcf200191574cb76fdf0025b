package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.DiaSemana;
import lumicode.agendaja.api.repository.DiaSemanaRepository;

@RestController
@RequestMapping("/diaSemana")
@CrossOrigin(origins = "*")
public class DiaSemanaResource {
	@Autowired
	private DiaSemanaRepository diaSemanaRepository;
	
	@GetMapping
	private List<DiaSemana> getDiaSemana(){
		return diaSemanaRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	private DiaSemana visualizarDiaSemana(@PathVariable Long id) {
		return diaSemanaRepository.findById(id).get();
	}
	
}
