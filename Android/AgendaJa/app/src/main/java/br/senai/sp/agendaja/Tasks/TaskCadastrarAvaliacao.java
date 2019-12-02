package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Avaliacao;

public class TaskCadastrarAvaliacao extends AsyncTask {

    private int idEstabelecimento;
    private int idCliente;
    private int notaAvaliacao;
    private String comentario;
    private String dados = "";
    private Avaliacao avaliacaoCadastrada;


    public TaskCadastrarAvaliacao(int idEstabelecimento, int idCliente, int notaAvaliacao, String comentario) {
        this.idEstabelecimento = idEstabelecimento;
        this.idCliente = idCliente;
        this.notaAvaliacao = notaAvaliacao;
        this.comentario = comentario;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        JSONStringer jsAvaliacao = new JSONStringer();

        try {

            jsAvaliacao.object();
            jsAvaliacao.key("estabelecimento").object().key("idEstabelecimento").value(idEstabelecimento).endObject();
            jsAvaliacao.key("cliente").object().key("idCliente").value(idCliente).endObject();
            jsAvaliacao.key("avaliacao").value(notaAvaliacao);
            jsAvaliacao.key("comentario").value(comentario);
            jsAvaliacao.endObject();

            URL url = new URL("http://"+ MainActivity.IP_SERVER + "/avaliacoesEstabelecimentos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type","Application/json");
            connection.setRequestProperty("token",MainActivity.TOKEN);

            connection.setDoInput(true);
            PrintStream stream = new PrintStream(connection.getOutputStream());
            stream.print(jsAvaliacao);

            Scanner scanner = new Scanner(connection.getInputStream());
            dados = scanner.nextLine();

            if(dados!=null){
                JSONObject object = new JSONObject(dados);
                avaliacaoCadastrada = new Avaliacao();
                avaliacaoCadastrada.setIdAvaliacao(object.getInt("idAvaliacaoEstabelecimento"));
                avaliacaoCadastrada.setIdCliente(object.getJSONObject("cliente").getInt("idCliente"));
                avaliacaoCadastrada.setIdEstabelecimento(object.getJSONObject("estabelecimento").getInt("idEstabelecimento"));
                avaliacaoCadastrada.setNotaAvaliacao(object.getInt("avaliacao"));
                avaliacaoCadastrada.setComentario(object.getString("comentario"));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(avaliacaoCadastrada!=null){
            return avaliacaoCadastrada;
        }else{
            return null;
        }
    }
}
