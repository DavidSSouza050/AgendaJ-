package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

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

public class TaskFinalizarAgendamento extends AsyncTask {
    private int idAgendamento;
    private String dados = "";
    private String resposta;

    public TaskFinalizarAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {

            URL url = new URL("http://"+ MainActivity.IP_SERVER+"/agendamentos/finalizar/"+idAgendamento);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("token",MainActivity.TOKEN);
            connection.setRequestProperty("Content-type","application/json");
            connection.setRequestMethod("PUT");

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String linha = "";

            while(linha!=null){
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }

            if(dados!=null){
                JSONObject object = new JSONObject(dados);
                resposta = object.getString("mesage");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(resposta!=null){
            return resposta;
        }else{
            return null;
        }
    }
}
