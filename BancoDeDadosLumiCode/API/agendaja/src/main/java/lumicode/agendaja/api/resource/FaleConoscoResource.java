package lumicode.agendaja.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.repository.FaleConoscoRepository;

@RestController
@RequestMapping("/faleConosco")
public class FaleConoscoResource {
	@Autowired
	private FaleConoscoRepository faleConoscoRepository;
	
	
}
