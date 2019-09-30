package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	@CrossOrigin("http://localhost:3000")
	@GetMapping
	private List<Cliente> getCliente(){	
		return clienteRepository.findAll();
	}
	
	//pegando um cliente salvo no banco
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{id}")
	private Cliente visualizarCliente(@PathVariable Long id){
		//declarando um cliente do banco
		Cliente cliente = clienteRepository.findById(id).get();
		//declarando a classe de funções de date
		ConverterDatas convertDatas = new ConverterDatas();
		// pegando a idade do cliente 
		String dataNasc = cliente.getDataNascimento();
		//transformando a data nascimento em date (ela é string)
		Date nascDate = convertDatas.stringToDatePt(dataNasc);
		//formatando a data e mandando uma String
		String dataNascFormatada = convertDatas.dataPt(nascDate);
		
//		setantando a idade formatada e trocando o date
		cliente.setDataNascimento(dataNascFormatada);
		
//		returnando o cliente
		return clienteRepository.findById(id).get();
	}
	
	//login para cliente
	@PostMapping("/login")
	@CrossOrigin("http://localhost:3000")
	private ResponseEntity<?> login(@RequestBody Cliente cliente) {
		
		Cliente clienteLogado = clienteRepository.entrar(cliente.getEmail(), cliente.getSenha()); 
		if( clienteLogado != null) {
			return ResponseEntity.ok(clienteLogado);
		}else {
			return  new ResponseEntity<String>("{mensage: 'E-mail ou Senha incorreta'}",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	// Cadastrando um cliente no banco
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	private ResponseEntity<?> salvarCliente(@Validated @RequestBody Cliente cliente,
		HttpServletResponse response){
		
		if(clienteRepository.verificarEmail(cliente.getEmail()) != null) {
			return new ResponseEntity<String>("{mensage: 'E-mail Já cadastrado'}",HttpStatus.BAD_REQUEST);
		}
		
		try {
			//declarando o coverter datas 
			ConverterDatas converterDatas = new ConverterDatas();
			//pegando a data em pt do cliente
			String dataBrString = cliente.getDataNascimento();
			//Transformando a string em date
			Date dataBrDate = converterDatas.stringToDateEn(dataBrString);
			//formatando para En
			String dataFormatadaEn = converterDatas.dataEn(dataBrDate);
			//setando no cliente a data en para cadastro
			cliente.setDataNascimento(dataFormatadaEn);
			//setando o criado em 
			cliente.setCriadoEm(converterDatas.dataAtual());
			//setando o atualizado 
			cliente.setAtualizadoEm(converterDatas.dataAtual());
			
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
			
		}catch (Exception e) {
			return  new ResponseEntity<String>("{mensage: 'Cpf já cadastrado ou está errado'}",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	//atualizando o cliente
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/{id}")
	private ResponseEntity<?> atualizarCliente(@Validated @RequestBody Cliente cliente,
			@PathVariable Long id ){
		
		
		try {
			Cliente clienteAtualizado = clienteRepository.findById(id).get();
			
			
			//declarando o coverter datas 
			ConverterDatas converterDatas = new ConverterDatas();
			//pegando a data em pt do cliente
			String dataBrString = cliente.getDataNascimento();
			//Transformando a string em date
			Date dataBrDate = converterDatas.stringToDateEn(dataBrString);
			//formatando para En
			String dataFormatadaEn = converterDatas.dataEn(dataBrDate);
			//setando no cliente a data en para cadastro
			cliente.setDataNascimento(dataFormatadaEn);
			//setando o atualizada em
			cliente.setAtualizadoEm(converterDatas.dataAtual());
			//para não atualizar o criadoEm estou setando de novo apra nao copiar
			String criadoEm = clienteAtualizado.getCriadoEm();
			cliente.setCriadoEm(criadoEm);
			// *************************
			
			BeanUtils.copyProperties(cliente, clienteAtualizado, "id");
		
			clienteRepository.save(cliente);
			
			return ResponseEntity.ok(clienteAtualizado);
		}catch (Exception e) {
			return  new ResponseEntity<String>("{mensage: 'Cpf já cadastrado ou está errado'}",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
}