package br.senai.sp.agendaja.modal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Cliente extends Informacao implements Serializable {
  private Integer idCliente;

  public Integer getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Integer idCliente) {
    this.idCliente = idCliente;
  }

  private byte[] foto;
  private String nome;
  private String sobrenome;
  private String cpf;
  private String dataNascimento;
  private String sexo;

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  @Override
  public String toString() {
    return "Cliente{" +
         "idCliente=" + idCliente +
         ", foto=" + Arrays.toString(foto) +
         ", nome='" + nome + '\'' +
         ", sobrenome='" + sobrenome + '\'' +
         ", cpf='" + cpf + '\'' +
         ", dataNascimento='" + dataNascimento + '\'' +
         ", sexo='" + sexo + '\'' +
         '}';
  }
}
