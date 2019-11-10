package lumicode.agendaja.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.TipoSalario;
import lumicode.agendaja.api.repository.TipoSalarioRepository;

@RestController
@RequestMapping("/tiposSalarios")
@CrossOrigin(origins = "*")
public class TipoSalarioResource {
	@Autowired
	private TipoSalarioRepository tipoSalarioRepository;
	
	@GetMapping
	private List<TipoSalario> getTipoSlaro(){
		return tipoSalarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private TipoSalario visualizarTipoSalario(@PathVariable Long id) {
		return tipoSalarioRepository.findById(id).get();
	}
	
}
