package lumicode.agendaja.api.resource;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lumicode.agendaja.api.model.Agendamento;
import lumicode.agendaja.api.model.EmServico;
import lumicode.agendaja.api.model.Estabelecimento;
import lumicode.agendaja.api.model.Servico;
import lumicode.agendaja.api.repository.AgendamentoRepository;
import lumicode.agendaja.api.repository.AgendamentoServicoRepository;
import lumicode.agendaja.api.repository.EmServicoRepository;
@RestController
@RequestMapping("/EmServico")
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
	
	@PostMapping
	private ResponseEntity<?> cadastrarEmServico(@RequestBody Long idEstabelecimento,	
			@RequestBody Long idAgendamento, HttpServletResponse response  ){
		
		Agendamento agendamento = agendamentoRepository.pegarAgendamento(idAgendamento);
		
		//pegando atributos para colocar o funcionario em servico
		String[] datahora = agendamento.getDataHorarioAgendado().split(" ");
		Long idFuncionario = agendamento.getFuncionario().getIdFuncionario();
		String horaInicio = datahora[1];
		//dia semana, dia mes e mes 
		Calendar calendar = Calendar.getInstance();
		Integer diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		Integer diaMes = calendar.get(Calendar.DAY_OF_MONTH);
		Integer mes = calendar.get(Calendar.MONTH) + 1;
		//pegando a hora do termino do servico
		Servico servico = agendamentoServicoRepository.pegarAgendamentoServico(agendamento.getIdAgendamento());
		Integer duracaoServico = servico.getDuracaoServico();
		
//		String casoZero = "";
//		
//		int cont = 0;
//		if(duracao>=60) {
//			while(duracao>=60) {
//				duracao = duracao - 60;
//				cont++;
//			}
//			if(duracao < 60) {
//				int soma = minutos + duracao;
//				
//				if(soma >=60) {
//					while(soma>=60) {
//						soma = soma -60;
//						cont++;
//					}
//				}
//				
//				hora = String.valueOf(horas+cont) + ":" + String.valueOf(soma) +":"+"00";
//	
//				System.out.println(hora);
//			}
//			
//		}else if(duracao<60) {
//			int soma = duracao + minutos;
//			
//			if(soma>=60) {
//				while(soma>=60) {
//					soma = soma-60;
//					cont++;
//				}
//				
//				if(soma<60) {
//					if(String.valueOf(soma).length() == 1){
//						if(soma < 10) {
//							casoZero = 0+""+soma;
//						}
//						hora = String.valueOf(horas+cont) + ":" + String.valueOf(casoZero)+":"+"00";
//						System.out.println(hora);
//					}else {
//						hora = String.valueOf(horas+cont) + ":" + String.valueOf(soma)+":"+"00";
//						System.out.println(hora);
//					}
//				
//				}
//				
//			}else if(soma<60) {
//				if(String.valueOf(soma).length() == 1){
//					if(soma < 10) {
//						casoZero = 0+""+soma;
//					}
//					hora = String.valueOf(horas+cont) + ":" + String.valueOf(casoZero)+":"+"00";
//					System.out.println(hora);
//				}else {
//					hora = String.valueOf(horas) + ":" + String.valueOf(soma)+":"+"00";
//					System.out.println(hora);
//				
//					}
//				
//				}
//			}
//		
		
		
		//colocando o funcionario no servico
		
		
		return null;
	}
	
	
}
