package br.senai.sp.agendaja.Verificacao;

import java.util.List;

import br.senai.sp.agendaja.Model.EmServico;

public class VerificandoHorariosFuncionarios {

  public  int verificarHorariosFuncionarios(List<EmServico> funcionariosList, String horaDeInicioCliente,String horaFimCliente) {
    int verificador=0;

    //convertendo horario cliente

    String[] horarioClienteInicio = horaDeInicioCliente.split(":");
    String[] horarioClienteEncerramento = horaFimCliente.split(":");

    Double horaInicioCliente = Double.parseDouble(horarioClienteInicio[0]);
    Double minutosInicioCliente = Double.parseDouble(horarioClienteInicio[1])/60.0;

    Double horaEncerramentoCliente = Double.parseDouble(horarioClienteEncerramento[0]);
    Double minutosEncerramentoCliente = Double.parseDouble(horarioClienteEncerramento[1])/60.0;

    Double horaFinalInicioCliente = horaInicioCliente+minutosInicioCliente;
    Double horaFinalEncerramentoCliente = horaEncerramentoCliente+minutosEncerramentoCliente;


    for(int i=0;i<funcionariosList.size();i++) {
      EmServico horariosFuncionario = funcionariosList.get(i);

      //Convertendo

      String[] horarioInicioFunc = horariosFuncionario.getHoraInicio().split(":");
      String[] horarioEncerramentoFunc = horariosFuncionario.getHoraFim().split(":");

      Double horasFuncInicio = Double.parseDouble(horarioInicioFunc[0]);
      Double MinutosFuncInicio = Double.parseDouble(horarioInicioFunc[1])/60.0;

      Double horasFuncEncerramento= Double.parseDouble(horarioEncerramentoFunc[0]);
      Double MinutosFuncEncerramento = Double.parseDouble(horarioEncerramentoFunc[1])/60.0;

      Double horaFinalInicioFunc = horasFuncInicio+MinutosFuncInicio;
      Double horaFinalEncerramentoFunc = horasFuncEncerramento+MinutosFuncEncerramento;

      if(horaFinalInicioCliente>=horaFinalInicioFunc && horaFinalInicioCliente<=horaFinalEncerramentoFunc) {
        verificador++;

      }else if(horaFinalEncerramentoCliente>=horaFinalInicioFunc && horaFinalEncerramentoCliente<=horaFinalEncerramentoFunc) {
        verificador++;
      }

    }

    return verificador;
  }

}
