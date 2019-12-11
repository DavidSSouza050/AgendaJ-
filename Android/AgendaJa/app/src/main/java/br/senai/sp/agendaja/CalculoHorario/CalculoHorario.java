package br.senai.sp.agendaja.CalculoHorario;

import java.util.Date;

public final class CalculoHorario {

  public static String calcularHorarioFinal(String horarioInicial){

    String[] horario = horarioInicial.split(":");

    int horas = Integer.parseInt(horario[0]);
    int minutos = Integer.parseInt(horario[1]);
    int cont = 0;
    int soma;
    int resto;
    String horaString;

    String horaFinal = null;

    if(minutos>=60){

      while(minutos>=60){
        minutos = minutos -60;
        cont++;
      }

      if(minutos==0) {
        resto = minutos;

        horas = horas+cont;

        if(String.valueOf(horas).length()==1){
           horaString = "0"+horas;
        }else{
          horaString = String.valueOf(horas);
        }

        horaFinal =  horaString + ":" + resto + "0";
      }else if(minutos<60){
        resto = minutos;

        horas = horas+cont;

        if(String.valueOf(horas).length()==1){
          horaString = "0"+horas;
        }else{
          horaString = String.valueOf(horas);
        }

        horaFinal = horaString+ ":" + resto;

      }


    }else{

      if(String.valueOf(horas).length()==1){
        horaString = "0"+horas;
      }else{
        horaString = String.valueOf(horas);
      }

      horaFinal = horaString + ":" +  minutos;
    }

    return horaFinal;
  }


}
