package br.senai.sp.agendaja.Services;

import org.json.JSONArray;

import java.util.List;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.EmServico;
import retrofit2.Call;

public class PegarFuncionariosEmServico {
  private Integer dia;
  private Integer mes;
  private Integer ano;
  private Integer idEstabelecimento;
  private PegarEmServico pegarEmServico;

  public PegarFuncionariosEmServico(Integer dia, Integer mes, Integer ano, Integer idEstabelecimento) {
    this.dia = dia;
    this.mes = mes;
    this.ano = ano;
    this.idEstabelecimento = idEstabelecimento;
  }


  public Call<List<EmServico>> postPegarFuncionariosEmServico(){

    pegarEmServico = RetrofitConfigPegarEmServico.pegarFuncionariosEmServico();

    Call<List<EmServico>> emServicoCall = pegarEmServico.pegarFuncionariosEmServico(dia,mes,ano,idEstabelecimento, MainActivity.TOKEN);

    return emServicoCall;

  }

}
