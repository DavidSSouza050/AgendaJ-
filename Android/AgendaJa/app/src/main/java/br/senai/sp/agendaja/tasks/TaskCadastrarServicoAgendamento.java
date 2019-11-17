package br.senai.sp.agendaja.tasks;

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

public class TaskCadastrarServicoAgendamento extends AsyncTask {
    private int idServico;
    private int idAgendamento;
    private String resposta;

    public TaskCadastrarServicoAgendamento(int idServico, int idAgendamento) {
        this.idServico = idServico;
        this.idAgendamento = idAgendamento;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        JSONStringer jsAgendamentoServico = new JSONStringer();
        try {

            jsAgendamentoServico.object();
            jsAgendamentoServico.key("servico").object().key("idServico").value(idServico);
            jsAgendamentoServico.key("agendamento").object().key("idAgendamento").value(idAgendamento);
            jsAgendamentoServico.endObject();

            URL url  = new URL("http://"+ MainActivity.IP_SERVER+"/agendamentoServicos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type","Application/json");
            connection.setRequestProperty("Accept","Application/json");
            connection.setRequestProperty("token",MainActivity.TOKEN);
            connection.setRequestMethod("POST");

            connection.setDoInput(true);
            PrintStream printStream =  new PrintStream(connection.getOutputStream());
            printStream.print(jsAgendamentoServico);
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            resposta = scanner.nextLine();


        } catch (JSONException e) {
            e.printStackTrace();
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
