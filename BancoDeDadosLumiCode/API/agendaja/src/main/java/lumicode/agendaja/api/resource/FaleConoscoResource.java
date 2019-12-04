package lumicode.agendaja.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.FaleConosco;
import lumicode.agendaja.api.repository.FaleConoscoRepository;

@RestController
@RequestMapping("/faleConosco")
@CrossOrigin(origins = "*")
public class FaleConoscoResource {
	@Autowired
	private FaleConoscoRepository faleConoscoRepository;

	@GetMapping
	private List<FaleConosco> getfaleconosco(){
		return faleConoscoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	private FaleConosco visualizarFaleConosco(@PathVariable Long id){
			return faleConoscoRepository.findById(id).get();
	}
	
	//Cadastrando o comentario dos usuarios
	@PostMapping
	private ResponseEntity<String> salvarComentario(@RequestBody FaleConosco faleConosco, HttpServletResponse response){
		
		//fazendo verificação para enviar a mensagem
		if(faleConoscoRepository.save(faleConosco) != null) 
			return new ResponseEntity<String>("{mensage: 'Cométario enviado com suceso'}",HttpStatus.CREATED);
		else 
			return new ResponseEntity<String>("{mensage: 'Falha ou enviar o cometário'}", HttpStatus.BAD_REQUEST);
		
	}

}
