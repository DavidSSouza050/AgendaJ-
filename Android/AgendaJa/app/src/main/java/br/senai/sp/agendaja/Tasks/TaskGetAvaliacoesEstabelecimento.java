package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;
import br.senai.sp.agendaja.Model.Avaliacao;

public class TaskGetAvaliacoesEstabelecimento extends AsyncTask {

    private int idEstabelecimento;
    private List<Avaliacao> avaliacaoList;
    private String dados = "";

    public TaskGetAvaliacoesEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            URL url = new URL("http://"+ MainActivity.IP_SERVER+"/avaliacoesEstabelecimentos/avaliacao/estabelecimento/"+idEstabelecimento);
            HttpURLConnection connection  = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("token",MainActivity.TOKEN);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String linha = "";

            while(linha!=null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            if(dados!=null){
                JSONArray jsonArray = new JSONArray(dados);
                avaliacaoList = new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = (JSONObject) jsonArray.get(i);
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setComentario(object.getString("comentario"));
                    avaliacao.setNotaAvaliacao(object.getInt("avaliacao"));
                    avaliacao.setIdEstabelecimento(object.getJSONObject("estabelecimento").getInt("idEstabelecimento"));
                    avaliacao.setIdCliente(object.getJSONObject("cliente").getInt("idCliente"));
                    avaliacao.setIdAvaliacao(object.getInt("idAvaliacaoEstabelecimento"));

                    avaliacaoList.add(avaliacao);


                }

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(avaliacaoList!=null){
            return avaliacaoList;
        }else{
            return null;
        }
    }
}
