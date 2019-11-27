package br.senai.sp.agendaja;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;;
import androidx.annotation.Nullable;
import br.senai.sp.agendaja.AcessoGaleria.VerificandoAcessoGaleria;
import br.senai.sp.agendaja.Paths.RealPathPhoto;
import br.senai.sp.agendaja.Services.CadastroFoto;
import br.senai.sp.agendaja.Model.Cliente;
import br.senai.sp.agendaja.Tasks.TaskLoginClienteToken;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.app.Activity.RESULT_OK;

public class PerfilFragment extends Fragment implements View.OnClickListener {

  private RelativeLayout dadosPessoais;
  private CircleImageView imagemCliente;
  private TextView nomeCompletoCliente;
  private TextView telefoneCliente;
  private Cliente clienteEditadoComSucesso;
  private RelativeLayout relativeSair;
  private int GALERY_REQUEST = 10;
  private String caminhoFoto;
  private Call<Cliente> atualizarFoto;
  private RelativeLayout editarEndereco;
  private RelativeLayout editarContato;
  private RelativeLayout sobreAgendaJa;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_perfil, container, false);

  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    Intent clientes = getActivity().getIntent();


    //instanciando os cliente do login e da edição
    //clienteLogado = (Cliente) clientes.getSerializableExtra("CLIENTELOGADO");
    clienteEditadoComSucesso = (Cliente) getActivity().getIntent().getSerializableExtra("clienteEditado");


    //instanciando as viewa do fragment_perfil
    dadosPessoais = getActivity().findViewById(R.id.caixa_dados_pessoais_perfil);
    imagemCliente = getActivity().findViewById(R.id.img_foto_usuario_perfilFragment);
    nomeCompletoCliente = getActivity().findViewById(R.id.text_nome_completo_perfil);
    telefoneCliente = getActivity().findViewById(R.id.text_telefone_perfil);
    relativeSair = getActivity().findViewById(R.id.caixa_sair_perfil);
    editarEndereco= getActivity().findViewById(R.id.caixa_endereco_perfil);

    // verificando se o cliente veio nulo
    if (MainActivity.CLIENTELOGADO != null && MainActivity.CLIENTELOGADO.getIdCliente() != null) {
      //colocando os dados do cliente nos campos
      nomeCompletoCliente.setText(MainActivity.CLIENTELOGADO.getNome() + " " + MainActivity.CLIENTELOGADO.getSobrenome());
      telefoneCliente.setText(MainActivity.CLIENTELOGADO.getCelular());


    try {

        URL url = new URL(MainActivity.IP_FOTO + MainActivity.CLIENTELOGADO.getFoto());

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
    imagemCliente.setOnClickListener(this);
    editarEndereco.setOnClickListener(this);
  }

  @Override
  public void onResume() {
    super.onResume();

     if (MainActivity.CLIENTELOGADO != null && MainActivity.CLIENTELOGADO.getIdCliente() != null) {
      //colocando os dados do cliente nos campos
      nomeCompletoCliente.setText(MainActivity.CLIENTELOGADO.getNome() + " " + MainActivity.CLIENTELOGADO.getSobrenome());
      telefoneCliente.setText(MainActivity.CLIENTELOGADO.getCelular());


      try {

        URL url = new URL(MainActivity.IP_FOTO + MainActivity.CLIENTELOGADO.getFoto());

        Picasso.get()
             .load(String.valueOf(url))
             .resize(100,100)
             .into(imagemCliente);

      } catch (MalformedURLException e) {
        e.printStackTrace();
      }

    }


  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.caixa_dados_pessoais_perfil:

        Intent intent = new Intent(getContext(), EditarDadosPessoaisActivity.class);
        if (MainActivity.CLIENTELOGADO != null) {
          intent.putExtra("clientePrimeiraEdicao", MainActivity.CLIENTELOGADO);
        }
        startActivity(intent);
        break;

      case R.id.caixa_sair_perfil:

        Intent intentLogout = new Intent(getContext(), TipoLoginActivity.class);
        startActivity(intentLogout);
        getActivity().finish();
        break;

      case R.id.img_foto_usuario_perfilFragment:

        VerificandoAcessoGaleria acessoGaleria = new VerificandoAcessoGaleria(getActivity());
        acessoGaleria.verificarPermissao();

        Intent intentGalery = new Intent();
        intentGalery.setType("image/*");
        intentGalery.setAction(Intent.ACTION_PICK);
        startActivityForResult(intentGalery,GALERY_REQUEST);

      case R.id.caixa_endereco_perfil:

//        Intent intentEditarEndereco = new Intent(getContext(),EditarEnderecoActivity.class);
//        startActivity(intentEditarEndereco);

    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if(resultCode==RESULT_OK){

      if(requestCode==GALERY_REQUEST){
        try {
          InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());

          final Bitmap imagemBitmap = BitmapFactory.decodeStream(inputStream);

          Uri imagemUri = data.getData();

          RealPathPhoto realPathPhoto = new RealPathPhoto();

          caminhoFoto = realPathPhoto.getRealPathFromUri(imagemUri,getContext());

          //ATUALIZANDO A FOTO DO CLIENTE

          CadastroFoto cadastroFoto = new CadastroFoto();

          if(MainActivity.CLIENTELOGADO!=null && MainActivity.CLIENTELOGADO.getIdCliente()!=null){
            atualizarFoto = cadastroFoto.CadastrarFoto(caminhoFoto,MainActivity.CLIENTELOGADO.getIdCliente());
          }
          atualizarFoto.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
              if(response.isSuccessful()){
                TaskLoginClienteToken loginClienteToken = new TaskLoginClienteToken(MainActivity.CLIENTELOGADO.getEmail(),
                        MainActivity.CLIENTELOGADO.getSenha(),MainActivity.TOKEN);
                loginClienteToken.execute();
                try {
                  if(loginClienteToken.get()!=null) {
                    MainActivity.CLIENTELOGADO = (Cliente) loginClienteToken.get();
                    imagemCliente.setImageBitmap(imagemBitmap);
                    Log.d("Essa é a foto atu", MainActivity.CLIENTELOGADO.getFoto());
                  }
                } catch (ExecutionException e) {
                  e.printStackTrace();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(getContext(),"Erro ao atualizar foto",Toast.LENGTH_LONG).show();

                Log.d("problema de atualizar a foto",t.getMessage());
            }
          });

//          imagemCliente.setImageBitmap(imagemBitmap);


        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }



    }

  }

}
