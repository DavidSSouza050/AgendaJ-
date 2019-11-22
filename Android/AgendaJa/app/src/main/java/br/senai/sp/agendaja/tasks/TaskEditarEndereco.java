package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

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

        Gson gson = new Gson();
        String enderecoAtualizar = gson.toJson(endereco);

        try {
            URL url = new URL("http://"+ MainActivity.IP_SERVER+"/enderecos/"+endereco.getIdEndereco());
            HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type","Application/json");
            connection.setRequestProperty("Accept","Application/json");
            connection.setRequestProperty("token",MainActivity.TOKEN);
            connection.setRequestMethod("PUT");

            connection.setDoInput(true);
            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.print(enderecoAtualizar);
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
