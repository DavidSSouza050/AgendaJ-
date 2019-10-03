package br.senai.sp.agendaja;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import br.senai.sp.agendaja.modal.Cliente;
import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout dadosPessoais;
    private Cliente clienteLogado;
    private CircleImageView imagemCliente;
    private TextView nomeCompletoCliente;
    private TextView telefoneCliente;
    private Cliente clienteEditadoComSucesso;

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

        clienteLogado = (Cliente) getActivity().getIntent().getSerializableExtra("clienteLogado");


        clienteEditadoComSucesso = (Cliente) getActivity().getIntent().getSerializableExtra("clienteEditado");


        dadosPessoais = getActivity().findViewById(R.id.caixa_dados_pessoais_perfil);
        imagemCliente = getActivity().findViewById(R.id.img_foto_usuario_perfilFragment);
        nomeCompletoCliente = getActivity().findViewById(R.id.text_nome_completo_perfil);
        telefoneCliente = getActivity().findViewById(R.id.text_telefone_perfil);

        if(clienteLogado!=null && clienteLogado.getIdCliente()!=null){
        //colocando os dados do cliente nos campos
        nomeCompletoCliente.setText(clienteLogado.getNome() + " " + clienteLogado.getSobrenome());
        telefoneCliente.setText(clienteLogado.getCelular());

//        File fotoCliente = new File(clienteLogado.getFoto());
//        String caminhoFoto = fotoCliente.getPath();
//        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
//        imagemCliente.setImageBitmap(bitmap);
        }
        //setando listenner no relativelaytout
        dadosPessoais.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(clienteEditadoComSucesso!=null && clienteEditadoComSucesso.getIdCliente()!=null){
            nomeCompletoCliente.setText(clienteEditadoComSucesso.getNome() + " " + clienteEditadoComSucesso.getSobrenome());
            telefoneCliente.setText(clienteEditadoComSucesso.getCelular());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.caixa_dados_pessoais_perfil:
                Intent intent = new Intent(getContext(),EditarDadosPessoaisActivity.class);
                if(clienteLogado!=null){
                    intent.putExtra("clienteLogado",clienteLogado);
                }
                startActivity(intent);

                //Toast.makeText(getContext(),clienteLogado.getSenha() + " " + clienteLogado.getSexo(),Toast.LENGTH_LONG ).show();
        }
    }


}
