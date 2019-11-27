package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;

public class RetrofitConfigPegarEmServico {

  public RetrofitConfigPegarEmServico() {
  }

  public static  final String URL = "http://" + MainActivity.IP_SERVER +"/";

  public static PegarEmServico pegarFuncionariosEmServico(){
    return RetrofitClient.getClient(URL).create(PegarEmServico.class);
  }

}
