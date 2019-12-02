package lumicode.agendaja.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lumicode.agendaja.api.model.Agendamento;
import lumicode.agendaja.api.model.EmServico;
import lumicode.agendaja.api.model.Servico;
import lumicode.agendaja.api.repository.AgendamentoRepository;
import lumicode.agendaja.api.repository.AgendamentoServicoRepository;
import lumicode.agendaja.api.repository.EmServicoRepository;
import lumicode.agendaja.api.repository.dto.EstabelecimentoDTORepository;
import lumicode.agendaja.api.repository.dto.FuncionarioDTORepository;
import lumicode.agendaja.api.utils.CalculadoDeDuracao;
@RestController
@RequestMapping("/emServico")
@CrossOrigin(origins = "*")
public class EmServicoResource {
	@Autowired
	private EmServicoRepository emServicoRepository;
	
	@Autowired
	private AgendamentoServicoRepository agendamentoServicoRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	
	@GetMapping
	private List<EmServico> listaDeFuncionariosEmServico(){
		return emServicoRepository.findAll();
	}
	
	@PostMapping("/ocupados")
	private List<EmServico> listaOcupacaoFuncionarios(@RequestParam Integer dia_mes,
			@RequestParam Integer mes, @RequestParam Integer ano, @RequestParam Long idEstabelecimento){
		List<EmServico> emServico =  emServicoRepository.pegarOcuapados1(dia_mes, mes, ano, idEstabelecimento);
		return emServico;
	}
	
	@PostMapping
	private ResponseEntity<?> cadastrarEmServico(@RequestParam Long idAgendamento, HttpServletResponse response  ){
		
		Agendamento agendamento = agendamentoRepository.pegarAgendamento(idAgendamento);
		
		//pegando atributos para colocar o funcionario em servico
		String[] datahora = agendamento.getDataHorarioAgendado().split(" ");
		Long idFuncionario = agendamento.getFuncionario().getIdFuncionario();
		String ocupadoInicio = datahora[1];
		String[] dataEmServico = datahora[0].split("-");
		
		//dia semana, dia mes e ano
		String diaMes = dataEmServico[2];
		String mes = dataEmServico[1];
		String ano = dataEmServico[0];
		
		//pegando a hora do termino do servico
		Servico servico = agendamentoServicoRepository.pegarAgendamentoServico(agendamento.getIdAgendamento());
		Integer duracaoServico = servico.getDuracaoServico();
		CalculadoDeDuracao calculoDucarao = new CalculadoDeDuracao();
		String ocupadoFim = calculoDucarao.calcularDuracao(ocupadoInicio, duracaoServico);
		
		
		//colocando o funcionario em servico
		EmServico emServico = new EmServico();
		emServico.setIdFuncionario(idFuncionario);
		emServico.setIdEstabelecimento(agendamento.getEstabelecimento().getIdEstabelecimento());
		emServico.setDiaMes(diaMes);
		emServico.setAno(ano);
		emServico.setMes(mes);
		emServico.setOcupadoInicio(ocupadoInicio);
		emServico.setOcupadoFim(ocupadoFim);
		
		
		EmServico emServicoSalvo = emServicoRepository.save(emServico);
		
		URI uri = ServletUriComponentsBuilder
				  .fromCurrentRequestUri()
				  .path("/{id}")
				  .buildAndExpand(emServicoSalvo.getIdEmServico())
				  .toUri();
		
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(emServicoSalvo);
		
	}
	
	
}
