package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Endereco;

public class TaskEditarEndereco extends AsyncTask {
    private Endereco endereco;
    private String resposta;

    public TaskEditarEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsEndereco = new JSONStringer();
        try {

            jsEndereco.object();
            jsEndereco.key("IdEndereco").value(endereco.getIdEndereco());
            jsEndereco.key("logradouro").value(endereco.getLogradouro());
            jsEndereco.key("bairro").value(endereco.getBairro());
            jsEndereco.key("cep").value(endereco.getCep());
            jsEndereco.key("idCidade").object().key("idCidade").value(endereco.getCodIBGE()).endObject();
            jsEndereco.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            URL url = new URL("http://"+ MainActivity.IP_SERVER+"/enderecos/"+endereco.getIdEndereco());
            HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type","Application/json");
            connection.setRequestProperty("Accept","Application/json");
            connection.setRequestProperty("token",MainActivity.TOKEN);
            connection.setRequestMethod("PUT");

            connection.setDoInput(true);
            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.print(jsEndereco);
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            resposta = scanner.nextLine();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(resposta!=null){
            return true;
        }else{
            return false;
        }
    }
}
