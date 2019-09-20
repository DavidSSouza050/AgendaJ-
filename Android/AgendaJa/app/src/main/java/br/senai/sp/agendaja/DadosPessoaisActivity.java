package br.senai.sp.agendaja;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.senai.sp.agendaja.conversaoImagem.ConverterImagem;
import br.senai.sp.agendaja.modal.Cliente;
import br.senai.sp.agendaja.modal.Endereco;

public class DadosPessoaisActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{

    private ImageView imgFotoUsuario;
    private EditText nome;
    private EditText sobreNome;
    private EditText dtNascimento;
    private EditText cpf;
    //private EditText sexo;
    private Button btnCancelar;
    private Button btnProximo;
    private ImageButton imgButtonGaleria;
    private ImageButton imgButonCamera;
    private Spinner spinnerSexo;
    private String sexo;
    private Cliente clienteDaVolta;
    private ArrayAdapter adapter;
    private Bitmap imagemBitmap;
    public static final int GALERY_REQUEST = 10;
    public static final int CAMERA_REQUEST = 20;
    private String nomeFoto;
    private String caminhoFoto;
    private String imagePath;
    private Endereco cepVoltado;
    private Cliente clienteVoltadoDoContato;
    private File arquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);

        //Pegando os dados retornados da enderecoActivity
        Intent intentVoltaEndereco = getIntent();
        clienteDaVolta = (Cliente) intentVoltaEndereco.getSerializableExtra("voltantoDados");
        cepVoltado = (Endereco) intentVoltaEndereco.getSerializableExtra("voltandoCep");
        clienteVoltadoDoContato = (Cliente) intentVoltaEndereco.getSerializableExtra("clienteVoltado");



        imgFotoUsuario = findViewById(R.id.img_foto_usuario);
        nome = findViewById(R.id.txt_nome_dados_pessais);
        sobreNome = findViewById(R.id.txt_sobre_nome_dados_pessoais);
        dtNascimento = findViewById(R.id.txt_data_nascimento_dados_pessoais);
        cpf = findViewById(R.id.txt_cpf_dados_pessoais);
        btnCancelar = findViewById(R.id.btn_cancelar__dados_pessoais);
        btnProximo = findViewById(R.id.btn_proximo_dados_pessoais);
        imgButonCamera = findViewById(R.id.image_button_camera);
        imgButtonGaleria = findViewById(R.id.image_button_galeria);

        spinnerSexo = findViewById(R.id.spinner_txt_sexo);
        adapter = ArrayAdapter.createFromResource(DadosPessoaisActivity.this,R.array.sexos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(adapter);

        //início das mascaras dos campos

        SimpleMaskFormatter formatCpf = new SimpleMaskFormatter("NNNNNNNNNNN");
        MaskTextWatcher maskCpf = new MaskTextWatcher(cpf,formatCpf);
        cpf.addTextChangedListener(maskCpf);

        SimpleMaskFormatter formtDataNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskDataNascimento = new MaskTextWatcher(dtNascimento,formtDataNascimento);
        dtNascimento.addTextChangedListener(maskDataNascimento);


        imgButtonGaleria.setOnClickListener(this);
        imgButonCamera.setOnClickListener(this);
        spinnerSexo.setOnItemSelectedListener(this);
        btnProximo.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(clienteDaVolta!=null){
            nome.setText(clienteDaVolta.getNome());
            sobreNome.setText(clienteDaVolta.getSobrenome());
            cpf.setText(clienteDaVolta.getCpf());
            dtNascimento.setText(clienteDaVolta.getDataNascimento());
            if(clienteDaVolta.getSexo().equals("F")){
                spinnerSexo.setSelection(1);
            }else if(clienteDaVolta.getSexo().equals("M")){
                spinnerSexo.setSelection(2);
            }
        }else if(clienteVoltadoDoContato != null){
          nome.setText(clienteVoltadoDoContato.getNome());
          sobreNome.setText(clienteVoltadoDoContato.getSobrenome());
          cpf.setText(clienteVoltadoDoContato.getCpf());
          dtNascimento.setText(clienteVoltadoDoContato.getDataNascimento());
            if(clienteVoltadoDoContato.getSexo().equals("F")){
                spinnerSexo.setSelection(1);
            }else if(clienteVoltadoDoContato.getSexo().equals("M")){
                spinnerSexo.setSelection(2);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_proximo_dados_pessoais:
                validar();
                if(validar()){
                    ConverterImagem imagem = new ConverterImagem();
                    Cliente cliente  = new Cliente();
                    cliente.setNome(nome.getText().toString());
                    cliente.setSobrenome(sobreNome.getText().toString());
                    cliente.setCpf((cpf.getText().toString()));
                    cliente.setSexo(sexo);
                    cliente.setFoto(imagePath);
                    //cliente.setFoto(imagem.bitmapParaByteArray(imgFotoUsuario));
                    cliente.setDataNascimento(dtNascimento.getText().toString());

                    Intent intentProximo = new Intent(DadosPessoaisActivity.this,EnderecoActivity.class);
                    intentProximo.putExtra("novoCliente",cliente);
                    if(cepVoltado!=null){
                        intentProximo.putExtra("retornandoCep",cepVoltado);
                    }
                    startActivity(intentProximo);
                    finish();
                }else if(validar()==false){
                    Toast.makeText(DadosPessoaisActivity.this,"Preencha todos os campos",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btn_cancelar__dados_pessoais:
                Intent intentCancelar = new Intent(DadosPessoaisActivity.this,LoginActivity.class);
                startActivity(intentCancelar);
                finish();
                break;
            case R.id.image_button_galeria:
                Intent intentGaleria = new Intent();
                intentGaleria.setType("image/*");
                intentGaleria.setAction(Intent.ACTION_PICK);
                startActivityForResult(intentGaleria,GALERY_REQUEST);
                break;
            case R.id.image_button_camera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/IMG" + System.currentTimeMillis() + "jpg";

                 arquivoFoto = new File(caminhoFoto);

                Uri fotoUri = FileProvider.getUriForFile(
                     DadosPessoaisActivity.this,
                     BuildConfig.APPLICATION_ID + ".provider",
                     arquivoFoto
                );
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intentCamera, CAMERA_REQUEST);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            try {
                if (requestCode == GALERY_REQUEST) {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    imagemBitmap = BitmapFactory.decodeStream(inputStream);
                    imgFotoUsuario.setImageBitmap(imagemBitmap);

                    //Pegando caminho da foto e colocando criando um File com ela

                    Uri imagemUri = data.getData();

                    imagePath = getRealPathFromUri(imagemUri);

                }else if(requestCode == CAMERA_REQUEST){

                    Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);

                    imagemBitmap = Bitmap.createScaledBitmap(bitmap,50,60,true);

                    imgFotoUsuario.setImageBitmap(imagemBitmap);

                }
            }catch (FileNotFoundException e){
                e.getMessage();
            }
        }

    }


//    public String retornarCaminho(Uri uri){
//
//    }

    public boolean validar(){
        Boolean validacao = true;

//        if(nome.length()==0){
//            validacao = false;
//        }else if(sobreNome.length()==0){
//            validacao = false;
//        }else if(dtNascimento.length()==0){
//            validacao = false;
//        }else if(cpf.length()==0){
//            validacao = false;
//        }

        return validacao;
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int columnIdx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(columnIdx);
        cursor.close();
        return result;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String pegandoSexo = parent.getItemAtPosition(position).toString();

        if(pegandoSexo.equals("Masculino")){
            sexo = "M";
            Log.d("Pegando Sexo",sexo);
        }else if(pegandoSexo.equals("Feminino")){
            sexo = "F";
            Log.d("Pegando Sexo",sexo);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(DadosPessoaisActivity.this,"Selecione o sexo",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed()
    {
        Intent intentVoltar = new Intent(DadosPessoaisActivity.this,LoginActivity.class);
        startActivity(intentVoltar);
        finish();
    }
}
