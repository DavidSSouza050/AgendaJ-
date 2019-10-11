package br.senai.sp.agendaja;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.Provider;

import br.senai.sp.agendaja.modal.Cliente;
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment implements View.OnClickListener {

  private RelativeLayout dadosPessoais;
  private Cliente clienteLogado;
  private CircleImageView imagemCliente;
  private TextView nomeCompletoCliente;
  private TextView telefoneCliente;
  private Cliente clienteEditadoComSucesso;
  private RelativeLayout relativeSair;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_perfil, container, false);

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);


    //instanciando os cliente do login e da edição
    clienteLogado = (Cliente) getActivity().getIntent().getSerializableExtra("clienteLogado");
    clienteEditadoComSucesso = (Cliente) getActivity().getIntent().getSerializableExtra("clienteEditado");


    //instanciando as viewa do fragment_perfil
    dadosPessoais = getActivity().findViewById(R.id.caixa_dados_pessoais_perfil);
    imagemCliente = getActivity().findViewById(R.id.img_foto_usuario_perfilFragment);
    nomeCompletoCliente = getActivity().findViewById(R.id.text_nome_completo_perfil);
    telefoneCliente = getActivity().findViewById(R.id.text_telefone_perfil);
    relativeSair = getActivity().findViewById(R.id.caixa_sair_perfil);


    if (clienteLogado != null && clienteLogado.getIdCliente() != null) {
      //colocando os dados do cliente nos campos
      nomeCompletoCliente.setText(clienteLogado.getNome() + " " + clienteLogado.getSobrenome());
      telefoneCliente.setText(clienteLogado.getCelular());


    try {

        URL url = new URL(MainActivity.IP_FOTO + clienteLogado.getFoto());

        Picasso.get()
             .load(String.valueOf(url))
             .resize(100,100)
             .into(imagemCliente);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    }

    //setando listenner nos relativelaytouts
    dadosPessoais.setOnClickListener(this);
    relativeSair.setOnClickListener(this);
  }

  @Override
  public void onResume() {
    super.onResume();

    if (clienteEditadoComSucesso != null && clienteEditadoComSucesso.getIdCliente() != null) {
      clienteLogado =null;
      nomeCompletoCliente.setText(clienteEditadoComSucesso.getNome() + " " + clienteEditadoComSucesso.getSobrenome());
      telefoneCliente.setText(clienteEditadoComSucesso.getCelular());
    }

  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.caixa_dados_pessoais_perfil:
        Intent intent = new Intent(getContext(), EditarDadosPessoaisActivity.class);
        if (clienteLogado != null) {
          intent.putExtra("clienteLogado", clienteLogado);
        }else if(clienteEditadoComSucesso!=null){
          intent.putExtra("clienteSegundaEdicao",clienteEditadoComSucesso);
        }
        startActivity(intent);
        break;
      case R.id.caixa_sair_perfil:
        Intent intentLogout = new Intent(getContext(), TipoLoginActivity.class);
        startActivity(intentLogout);
        getActivity().finish();
        break;

    }
  }

//  private class CarregarImagem extends AsyncTask<URL,Void,Drawable>{
//
//    @Override
//    protected Drawable doInBackground(URL... urls) {
//      //pega a url passada pelo execute
//      URL url = urls[0];
//      InputStream content = null;
//
//      try {
//        //pega o conteudo da resposta http
//        content = (InputStream) url.getContent();
//        //trasnforma o inputStream em Drawable
//        Drawable drw = Drawable.createFromStream(content,"src");
//        return drw;
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      return null;
//    }
//    @Override
//    protected void onPostExecute(Drawable drawable) {
//      super.onPostExecute(drawable);
//      //recebe o drawable e coloca no imageView
//      imagemCliente.setImageDrawable(drawable);
//      //esconde a progress bar
////      viewHolder.progress.setVisibility(View.INVISIBLE);
//    }
//  }

}
