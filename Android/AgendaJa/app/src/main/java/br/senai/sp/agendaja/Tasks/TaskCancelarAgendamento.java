package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.senai.sp.agendaja.MainActivity;

public class TaskCancelarAgendamento extends AsyncTask {
    private int idAgendamento;
    private String dados = "";
    private JSONObject jsonObject;
    private String resposta;

    public TaskCancelarAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            URL url  = new URL("http://"+ MainActivity.IP_SERVER+"/agendamentos/cancelar/"+idAgendamento);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-type","Application/json");
            connection.setRequestProperty("token",MainActivity.TOKEN);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String linha = "";

            while(linha!=null){
                linha = bufferedReader.readLine();
                dados = dados  + linha;
            }


            if(dados!=null){
                jsonObject = new JSONObject(dados);
                 resposta = jsonObject.getString("mesage");
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonObject!=null){
            return resposta;
        }else{
            return null;
        }
    }
}
