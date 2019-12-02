package br.senai.sp.agendaja.Model;

public class Avaliacao {
    private int idAvaliacao;
    private String comentario;
    private int notaAvaliacao;
    private int idCliente;
    private int idEstabelecimento;


    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "idAvaliacao=" + idAvaliacao +
                ", comentario='" + comentario + '\'' +
                ", notaAvaliacao=" + notaAvaliacao +
                ", idCliente=" + idCliente +
                ", idEstabelecimento=" + idEstabelecimento +
                '}';
    }
}
