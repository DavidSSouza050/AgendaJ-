package br.senai.sp.agendaja.CalculoHorario;

import java.util.Date;

public final class CalculoHorario {

  public static String calcularHorarioFinal(String horarioInicial, int contador){

    String[] horario = horarioInicial.split(":");

    int horas = Integer.parseInt(horario[0]);
    int minutos = Integer.parseInt(horario[1]);
    int cont = 0;
    int soma;
    int resto;

    String horaFinal = null;

    soma = minutos + contador;

    if(soma>=60){
      while(soma>=60){
        soma = soma - 60;
        cont++;
      }

      if(soma==0) {
        resto = soma;

        horas = horas+cont;

        horaFinal = horas + ":" + resto + "0";
      }else if(soma<60){
        resto = soma;

        horas = horas+cont;

        horaFinal = horas + ":" + resto;

      }

    }else{
      horaFinal = horas + ":" +  soma;
    }

    return horaFinal;
  }


}
