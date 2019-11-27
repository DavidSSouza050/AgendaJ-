package br.senai.sp.agendaja.Services;

import br.senai.sp.agendaja.MainActivity;

public class RetrofitConfigCadastrarEmServico {

  public RetrofitConfigCadastrarEmServico() {
  }

  public static  final String URL = "http://" + MainActivity.IP_SERVER +"/";

  public static FileServiceEmServico getFileService(){
    return RetrofitClient.getClient(URL).create(FileServiceEmServico.class);
  }

}
