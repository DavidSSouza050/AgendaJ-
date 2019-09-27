package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.Assunto;
import lumicode.agendaja.api.repository.AssuntoRepository;

@RestController
@RequestMapping("/assunto")
public class AssuntoResource {
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@GetMapping
	private List<Assunto> getAssunto(){
		return assuntoRepository.findAll();
	}
}
