package br.senai.sp.agendaja.Verificacao;

import android.util.Log;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.Model.Estabelecimento;
import br.senai.sp.agendaja.Model.Horario;

public class VerificandoHorarios {

  public List<String> verificandoHorarios(Estabelecimento estabelecimento,Horario horarioDoDia) {

    if (estabelecimento.getHorarios() != null) {

      List<Horario> horarioList;
      horarioList = estabelecimento.getHorarios();
      List<String> horariosDoEstabelecimento = new ArrayList<>();
      boolean verificacao = true;

      Calendar c = Calendar.getInstance();

      int idDiaAtual = c.get(Calendar.DAY_OF_WEEK);


      Double contFor;


      for (int cont = 0; cont < estabelecimento.getHorarios().size(); cont++) {

        Horario horario = horarioList.get(cont);

        if (horario.getIdDiaSemana() == idDiaAtual) {
          horarioDoDia = horario;
        }
      }

      if (horarioDoDia != null) {
        String[] arrayHorarioAbertura = horarioDoDia.getHorarioAbertura().split(":");
        String[] arrayHorarioFechamento = horarioDoDia.getHorarioFechamento().split(":");


        //Essa parte do código diz repeito a os estabelecimento que abrem em um horário que termina em zero e fecha em um horário que termina em zero

        //Aqui eu divido os minutos do horario de abertura do salo por 60 para transformar tudo em horas, o intuito é trabalhar com um double na hora de fazer o for();
        Float minAbertura = Float.parseFloat(arrayHorarioAbertura[1]) / 60;
        //aqui eu formato para que na divisao descrita acima apareça apenas as duas primeiras casas do numero
        //DecimalFormat dfAbertura = new DecimalFormat("#.##");
        //minAbertura = Float.parseFloat(dfAbertura.format(minAbertura));


        //aqui também existe a divisão para que os minutos do horário de fechamento fique convertido em horas para trabalhar com double no for();
        Float minFechamento = Float.parseFloat(arrayHorarioFechamento[1]) / 60;
        //aqui também formatado para que apareca apenas duas casas decimais após a divisão
        //DecimalFormat dfFechamento = new DecimalFormat("#.##");
        //minFechamento = Float.valueOf((dfFechamento.format(minFechamento)));

        //essa é a hora com os minutos convertidos em horas também, tanto a de abertura quanto a de fechamento
        Double horaAberturaFormatada = Double.parseDouble(arrayHorarioAbertura[0]) + minAbertura;
        Double horaFechamentoFormatada = Double.parseDouble(arrayHorarioFechamento[0]) + minFechamento;

        //esse é o contados que também é convertido de minutos para horas
        Float contador = Float.valueOf((float) (20.00 / 60.00));
        //DecimalFormat dfContador = new DecimalFormat("#.##");
        //contador = Float.valueOf(dfContador.format(contador));

        Log.d("contador", String.valueOf(contador));


        for (contFor = horaAberturaFormatada; contFor <= horaFechamentoFormatada; contFor = contFor + contador) {

          //nessa parte do codigo faz-se o processo inverso feito acima, as horas decimais são convertidas para minnutos novamente e é chamado o método calcularHorarioFinal que faz a conta e devolve um string;

          Double minutosEmHoras = (contFor % 1);

          //DecimalFormat dfMinutos = new DecimalFormat("#.##");
          //dfMinutos.setRoundingMode(RoundingMode.UP);
          //minutosEmHoras = Double.valueOf(dfMinutos.format(minutosEmHoras));

//        Log.d("minutos reversos", String.valueOf(contFor));

          int horasParaMinutos = ((int) (minutosEmHoras * 60));


          DecimalFormat dfMinutosReverso = new DecimalFormat("##");
          dfMinutosReverso.setRoundingMode(RoundingMode.UP);


          String horasString = String.valueOf(horasParaMinutos);

          if (horasString.endsWith("9")) {
            horasParaMinutos++;
          } else if (horasString.endsWith("8")) {
            horasParaMinutos += 2;
          } else if (horasString.endsWith("7")) {
            horasParaMinutos += 3;
          } else if (horasString.endsWith("6")) {
            horasParaMinutos += 4;
          } else if (horasString.endsWith("5")) {
            horasParaMinutos += 5;
          } else if (horasString.endsWith("4")) {
            horasParaMinutos += 6;
          } else if (horasString.endsWith("3")) {
            horasParaMinutos += 7;
          } else if (horasString.endsWith("2")) {
            horasParaMinutos += 8;
          } else if (horasString.endsWith("1")) {
            horasParaMinutos += 9;
          }


          int horarioAberturaReverso = (int) (contFor / 1);

          String horaReversa = horarioAberturaReverso + ":" + horasParaMinutos;

          String horaFinal = CalculoHorario.calcularHorarioFinal(horaReversa);

          horariosDoEstabelecimento.add(horaFinal);

        }
        horariosDoEstabelecimento.remove(horariosDoEstabelecimento.size() - 1);

        return horariosDoEstabelecimento;

      } else {
        return null;
      }
    }else{
      return null;
    }

    }


  public List<String> verificandoHorariosComDiaEspecifico(Estabelecimento estabelecimento,Horario horarioDoDia,int idDiaEscolhido) {

    if (estabelecimento.getHorarios() != null) {

      List<Horario> horarioList;
      horarioList = estabelecimento.getHorarios();
      List<String> horariosDoEstabelecimento = new ArrayList<>();


      Double contFor;


      for (int cont = 0; cont < estabelecimento.getHorarios().size(); cont++) {

        Horario horario = horarioList.get(cont);

        if (horario.getIdDiaSemana() == idDiaEscolhido) {
          horarioDoDia = horario;
        }
      }

      if (horarioDoDia != null) {
        String[] arrayHorarioAbertura = horarioDoDia.getHorarioAbertura().split(":");
        String[] arrayHorarioFechamento = horarioDoDia.getHorarioFechamento().split(":");


        //Essa parte do código diz repeito a os estabelecimento que abrem em um horário que termina em zero e fecha em um horário que termina em zero

        //Aqui eu divido os minutos do horario de abertura do salo por 60 para transformar tudo em horas, o intuito é trabalhar com um double na hora de fazer o for();
        Double minAbertura = Double.parseDouble(arrayHorarioAbertura[1]) / 60;
        //aqui eu formato para que na divisao descrita acima apareça apenas as duas primeiras casas do numero
        DecimalFormat dfAbertura = new DecimalFormat("#.##");
        minAbertura = Double.valueOf(dfAbertura.format(minAbertura));


        //aqui também existe a divisão para que os minutos do horário de fechamento fique convertido em horas para trabalhar com double no for();
        Double minFechamento = Double.parseDouble(arrayHorarioFechamento[1]) / 60;
        //aqui também formatado para que apareca apenas duas casas decimais após a divisão
        DecimalFormat dfFechamento = new DecimalFormat("#.##");
        minFechamento = Double.valueOf(dfFechamento.format(minFechamento));

        //essa é a hora com os minutos convertidos em horas também, tanto a de abertura quanto a de fechamento
        Double horaAberturaFormatada = Double.valueOf(arrayHorarioAbertura[0]) + minAbertura;
        Double horaFechamentoFormatada = Double.valueOf(arrayHorarioFechamento[0]) + minFechamento;

        //esse é o contados que também é convertido de minutos para horas
        Double contador = Double.valueOf((20.00 / 60.00));
        DecimalFormat dfContador = new DecimalFormat("#.##");
        contador = Double.valueOf(dfContador.format(contador));

        for (contFor = horaAberturaFormatada; contFor <= horaFechamentoFormatada; contFor = contFor + contador) {

          //nessa parte do codigo faz-se o processo inverso feito acima, as horas decimais são convertidas para minnutos novamente e é chamado o método calcularHorarioFinal que faz a conta e devolve um string;

          Double minutosEmHoras = (contFor % 1);

          DecimalFormat dfMinutos = new DecimalFormat("#.##");
          dfMinutos.setRoundingMode(RoundingMode.UP);
          minutosEmHoras = Double.valueOf(dfMinutos.format(minutosEmHoras));

//        Log.d("minutos reversos", String.valueOf(contFor));

          int horasParaMinutos = ((int) (minutosEmHoras * 60));


          DecimalFormat dfMinutosReverso = new DecimalFormat("##");
          dfMinutosReverso.setRoundingMode(RoundingMode.UP);


          String horasString = String.valueOf(horasParaMinutos);

          if (horasString.endsWith("9")) {
            horasParaMinutos++;
          } else if (horasString.endsWith("8")) {
            horasParaMinutos += 2;
          } else if (horasString.endsWith("7")) {
            horasParaMinutos += 3;
          } else if (horasString.endsWith("6")) {
            horasParaMinutos += 4;
          } else if (horasString.endsWith("5")) {
            horasParaMinutos += 5;
          } else if (horasString.endsWith("4")) {
            horasParaMinutos += 6;
          } else if (horasString.endsWith("3")) {
            horasParaMinutos += 7;
          } else if (horasString.endsWith("2")) {
            horasParaMinutos += 8;
          } else if (horasString.endsWith("1")) {
            horasParaMinutos += 9;
          }


          int horarioAberturaReverso = (int) (contFor / 1);

          String horaReversa = horarioAberturaReverso + ":" + horasParaMinutos;

          String horaFinal = CalculoHorario.calcularHorarioFinal(horaReversa);

          horariosDoEstabelecimento.add(horaFinal);

        }
        horariosDoEstabelecimento.remove(horariosDoEstabelecimento.size() - 1);

        return horariosDoEstabelecimento;

      } else {
        return null;
      }
    }else{
      return null;
    }

  }


  }
