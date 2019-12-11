package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.MainActivityFuncionario;
import br.senai.sp.agendaja.Model.Agendamento;

public class TaskGetAgendamentosPendentesFuncionarios extends AsyncTask {
    private List<Agendamento> agendamentoList;
    private String dados = "";
    private String token;

    public TaskGetAgendamentosPendentesFuncionarios(String token) {
        this.token = token;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        URL url = null;
        try {
            url = new URL("http://" + MainActivity.IP_SERVER + "/agendamentos/funcionario/"
                    + MainActivityFuncionario.FUNCIONARIOLOGADO.getIdFuncionario() + "/mes/servicosPendente");

            Log.d("url funcionario", String.valueOf(url));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("token", token);
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String linha = "";

            while (linha != null) {
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }


            if (dados != null) {
                JSONArray array = new JSONArray(dados);
                agendamentoList = new ArrayList<>();

                for (int i = 0; i < array.length(); i++) {
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
                    agendamento.setNomeCliente(object.getString("nomeCliente"));

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
