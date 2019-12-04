package br.senai.sp.agendaja.Model;

public class Agendamento {

    private int idAgendamento;
    private int idFuncionario;
    private int idEstabelecimento;
    private String nomeEstabelecimento;
    private String preco;
    private int idCliente;
    private String nomeServico;
    private String nomeCategoria;
    private String duracaoServico;
    private int statusFinalizado;
    private String statusCancelado;
    private String dataAgendamento;
    private String nomeCliente;


    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDuracaoServico() {
        return duracaoServico;
    }

    public void setDuracaoServico(String duracaoServico) {
        this.duracaoServico = duracaoServico;
    }

    public int getStatusFinalizado() {
        return statusFinalizado;
    }

    public void setStatusFinalizado(int statusFinalizado) {
        this.statusFinalizado = statusFinalizado;
    }

    public String getStatusCancelado() {
        return statusCancelado;
    }

    public void setStatusCancelado(String statusCancelado) {
        this.statusCancelado = statusCancelado;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "idAgendamento=" + idAgendamento +
                ", idFuncionario=" + idFuncionario +
                ", idEstabelecimento=" + idEstabelecimento +
                ", nomeEstabelecimento='" + nomeEstabelecimento + '\'' +
                ", preco='" + preco + '\'' +
                ", idCliente=" + idCliente +
                ", nomeServico='" + nomeServico + '\'' +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", duracaoServico='" + duracaoServico + '\'' +
                ", statusFinalizado=" + statusFinalizado +
                ", statusCancelado='" + statusCancelado + '\'' +
                ", dataAgendamento='" + dataAgendamento + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
