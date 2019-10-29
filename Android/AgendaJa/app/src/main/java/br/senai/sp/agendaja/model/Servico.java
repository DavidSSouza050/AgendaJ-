package br.senai.sp.agendaja.Model;

import java.io.Serializable;

public class Servico implements Serializable{

  private int idServico;
  private String nomeServico;
  private Double precoServico;
  private int duracao;
  private int idCategoria;
  private String categoriaServico;

  public int getIdServico() {
    return idServico;
  }

  public void setIdServico(int idServico) {
    this.idServico = idServico;
  }

  public String getNomeServico() {
    return nomeServico;
  }

  public void setNomeServico(String nomeServico) {
    this.nomeServico = nomeServico;
  }

  public Double getPrecoServico() {
    return precoServico;
  }

  public void setPrecoServico(Double precoServico) {
    this.precoServico = precoServico;
  }

  public int getDuracao() {
    return duracao;
  }

  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }

  public String getCategoriaServico() {
    return categoriaServico;
  }

  public void setCategoriaServico(String categoriaServico) {
    this.categoriaServico = categoriaServico;
  }

  public int getIdCategoria() {
    return idCategoria;
  }

  public void setIdCategoria(int idCategoria) {
    this.idCategoria = idCategoria;
  }

  @Override
  public String toString() {
    return "Servico{" +
         "idServico=" + idServico +
         ", nomeServico='" + nomeServico + '\'' +
         ", precoServico=" + precoServico +
         ", duracao=" + duracao +
         ", idCategoria=" + idCategoria +
         ", categoriaServico='" + categoriaServico + '\'' +
         '}';
  }
}
