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

public class TaskGetAgendamentosAbertos extends AsyncTask {
    private int idCliente;
    private String dados = "";
    private List<Agendamento> agendamentoList;

    public TaskGetAgendamentosAbertos(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            URL url = new URL("http://" + MainActivity.IP_SERVER + "/agendamentos/cliente/" + idCliente + "/servicoPendente");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("token",MainActivity.TOKEN);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader  inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha ="";

            while(linha!=null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }


            if(dados!=null){
                JSONArray array= new JSONArray(dados);
                agendamentoList = new ArrayList<>();

                for (int i=0;i<array.length();i++){
                    JSONObject object = (JSONObject) array.get(i);
                    Agendamento agendamento = new Agendamento();
                    agendamento.setIdCliente(object.getInt("cliente"));
                    agendamento.setIdAgendamento(object.getInt("idAgendamento"));
                    agendamento.setNomeEstabelecimento(object.getString("nomeEstabelecimento"));
                    agendamento.setPreco(object.getString("preco"));
                    agendamento.setNomeServico(object.getString("servico"));
                    agendamento.setDuracaoServico(object.getString("duracaoServico"));
                    agendamento.setDataAgendamento(object.getString("dataHora"));
                    agendamento.setNomeCategoria(object.getString("categoria"));
                    agendamento.setStatusFinalizado(object.getInt("finalizado"));
                    agendamento.setStatusCancelado(object.getString("cancelado"));
                    agendamento.setIdFuncionario(object.getInt("idFunncionario"));
                    agendamento.setIdEstabelecimento(object.getInt("idEstabelecimento"));

                    agendamentoList.add(agendamento);

                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(agendamentoList!=null){
            return agendamentoList;
        }else{
            return null;
        }
    }
}
