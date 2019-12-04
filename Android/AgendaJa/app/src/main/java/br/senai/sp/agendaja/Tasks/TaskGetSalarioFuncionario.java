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
import br.senai.sp.agendaja.MainActivityFuncionario;

public class TaskGetSalarioFuncionario extends AsyncTask {
    private String token;
    private Double salario;
    private String dados = "";

    public TaskGetSalarioFuncionario(String token) {
        this.token = token;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {

            URL url = new URL("http://"+ MainActivity.IP_SERVER+"/salarios/funcionario/"+ MainActivityFuncionario.FUNCIONARIOLOGADO.getIdFuncionario());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("token",token);
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String linha = "";

            while(linha!=null){
                linha = bufferedReader.readLine();
                dados = dados+linha;
            }

            if(dados!=null){
                JSONObject object = new JSONObject(dados);
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
