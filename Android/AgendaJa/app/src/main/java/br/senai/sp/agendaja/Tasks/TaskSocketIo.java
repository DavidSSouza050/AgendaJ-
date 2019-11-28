package br.senai.sp.agendaja.Tasks;

import android.os.AsyncTask;

import java.net.URISyntaxException;

import br.senai.sp.agendaja.MainActivity;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class TaskSocketIo extends AsyncTask {
  @Override
  protected Object doInBackground(Object[] objects) {

    try {
      Socket socket = IO.socket(MainActivity.IP_SOCKET);
      socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
      });



    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return null;
  }
}
