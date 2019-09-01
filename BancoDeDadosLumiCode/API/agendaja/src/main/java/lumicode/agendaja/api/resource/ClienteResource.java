package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.Cliente;
import lumicode.agendaja.api.repository.ClienteRepository;
import lumicode.agendaja.api.utils.ConverterDatas;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//pegando todos clientes salvo no banco 
	@GetMapping
	private List<Cliente> getCliente(){	
		return clienteRepository.findAll();
	}
	
	//pegando um cliente salvo no banco
	@GetMapping("/{id}")
	private Cliente visualizarCliente(@PathVariable Long id){
		
		//Cliente cliente = clienteRepository.findById(id).get();
		//ConverterDatas data = new ConverterDatas();
		
		//Date dataNasc = cliente.getDataNacimento();
		
		//Date dataNasc = new Date();
		//cliente.setDataNacimento(dataNasc);
		//System.out.println(data.dataPt(dataNasc));
		
		return clienteRepository.findById(id).get();
	}
	
	//login para cliente
	@GetMapping("/login")
	private String login(@RequestBody Cliente cliente) {
		String resposta;
		if(clienteRepository.Entrar(cliente.getEmail(), cliente.getSenha()) != null) {
			resposta = "Entra ai po";
		}else {
			resposta = "Ta errado alguma coisa ai";
		}
		
		return resposta;
	}
	
	
	// Cadastrando um cliente no banco
	@PostMapping
	private ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente,
		HttpServletResponse response){
		
		Cliente clienteSalvo = clienteRepository.save(cliente);
		
		//criando o cliente depois de salvo para retornar o json  
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequest()
				  .path("/{id}")
				  .buildAndExpand(cliente.getIdCliente())
				  .toUri();
		//colocando no header o localização do cliente que está na uri
		response.addHeader("Location", uri.toASCIIString());
		//criando o cliente usuario e colocando no body da requiseção depois de salvo
		return ResponseEntity.created(uri).body(clienteSalvo);
	}
	
	
	//atualizando o cliente
	@PutMapping("/{id}")
	private ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente,
			@PathVariable Long id ){
		
		Cliente clienteAtualizado = clienteRepository.findById(id).get();
		
		BeanUtils.copyProperties(cliente, clienteAtualizado, "id");
	
		clienteRepository.save(cliente);
		
		return ResponseEntity.ok(clienteAtualizado);
	}
	
	
	
}









