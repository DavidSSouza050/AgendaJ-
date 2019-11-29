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
import java.util.List;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Agendamento;

public class TaskGetAgendamentos extends AsyncTask {
    private int idCliente;
    private String dados;
    private List<Agendamento> agendamentoList;

    public TaskGetAgendamentos(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            URL url = new URL("http://" + MainActivity.IP_SERVER + "/agendamentos/cliente/" + idCliente + "/servicoRealizados");
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

                for (int i=0;i<array.length();i++){
                    JSONObject object = (JSONObject) array.get(i);
                    Agendamento agendamento = new Agendamento();
                    agendamento.setIdCliente(object.getJSONObject("cliente").getInt("idCliente"));
                    agendamento.setIdAgendamento(object.getInt("idAgendamento"));
                    agendamento.setNomeEstabelecimento(object.getJSONObject("estabelecimento").getString("nomeEstabelecimento"));

                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
