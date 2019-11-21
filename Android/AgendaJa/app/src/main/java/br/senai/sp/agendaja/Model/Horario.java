package br.senai.sp.agendaja.Model;

import java.io.Serializable;
import java.util.Date;

public class Horario implements Serializable {

  private int idHorario;
  private String horarioAbertura;
  private String horarioFechamento;
  private String diaSemana;
  private int idDiaSemana;


  public int getIdHorario() {
    return idHorario;
  }

  public void setIdHorario(int idHorario) {
    this.idHorario = idHorario;
  }

  public String getHorarioAbertura() {
    return horarioAbertura;
  }

  public void setHorarioAbertura(String horarioAbertura) {
    this.horarioAbertura = horarioAbertura;
  }

  public String getHorarioFechamento() {
    return horarioFechamento;
  }

  public void setHorarioFechamento(String horarioFechamento) {
    this.horarioFechamento = horarioFechamento;
  }

  public String getDiaSemana() {
    return diaSemana;
  }

  public void setDiaSemana(String diaSemana) {
    this.diaSemana = diaSemana;
  }

  public int getIdDiaSemana() {
    return idDiaSemana;
  }

  public void setIdDiaSemana(int idDiaSemana) {
    this.idDiaSemana = idDiaSemana;
  }

  @Override
  public String toString() {
    return "Horario{" +
         "idHorario=" + idHorario +
         ", horarioAbertura='" + horarioAbertura + '\'' +
         ", horarioFechamento='" + horarioFechamento + '\'' +
         ", diaSemana='" + diaSemana + '\'' +
         ", idDiaSemana=" + idDiaSemana +
         '}';
  }
}
