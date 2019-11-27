package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.EmServico;
import retrofit2.Call;

public class PegarFuncionariosEmServico {
  private int dia;
  private int mes;
  private int ano;
  private int idAgendamento;
  private PegarEmServico pegarEmServico;

  public PegarFuncionariosEmServico(int dia, int mes, int ano, int idAgendamento) {
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
    this.idAgendamento = idAgendamento;
  }


  public Call<EmServico> postPegarFuncionariosEmServico(){

    pegarEmServico = RetrofitConfigPegarEmServico.pegarFuncionariosEmServico();

    Call<EmServico> emServicoCall = pegarEmServico.pegarFuncionariosEmServico(dia,mes,ano,idAgendamento, MainActivity.TOKEN);

    return emServicoCall;

  }

}
