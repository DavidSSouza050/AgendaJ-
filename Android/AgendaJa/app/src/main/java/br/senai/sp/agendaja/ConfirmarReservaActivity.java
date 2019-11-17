package br.senai.sp.agendaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.agendaja.CalculoHorario.CalculoHorario;
import br.senai.sp.agendaja.model.Estabelecimento;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.Model.Servico;

public class ConfirmarReservaActivity extends AppCompatActivity  implements View.OnClickListener{
    private Estabelecimento estabelecimentoEscolhido;
    private Funcionario funcionarioEscolhido;
    private String horarioEscolhido;
    private Servico servicoEscolhido;
    private String horaEscolhida;
    private String dataEscolhida;
    private TextView txtNomeEstabelecimento;
    private TextView txtEnderecoEstabelecimento;
    private TextView txtPrecoServico;
    private TextView txtDuracaoServico;
    private Button btnConfirmarReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_reserva);

        Intent intentGetObjects = getIntent();


        //tratando os objetos e instanciando eles
        if(intentGetObjects.getSerializableExtra("estabelecimentoEscolhido")!=null){
            estabelecimentoEscolhido = (Estabelecimento) intentGetObjects.getSerializableExtra("estabelecimentoEscolhido");
        }

        if(intentGetObjects.getSerializableExtra("servicoEscolhido")!=null){
            servicoEscolhido = (Servico) intentGetObjects.getSerializableExtra("servicoEscolhido");
        }

        if(intentGetObjects.getSerializableExtra("horaEscolhido")!=null){
            horaEscolhida = (String) intentGetObjects.getSerializableExtra("horaEscolhido");
        }

        if(intentGetObjects.getSerializableExtra("dataEscolhida")!=null){
            dataEscolhida = (String) intentGetObjects.getSerializableExtra("dataEscolhida");
        }

        if(intentGetObjects.getSerializableExtra("funcionarioEscolhido")!=null){
            funcionarioEscolhido = (Funcionario) intentGetObjects.getSerializableExtra("funcionarioEscolhido");
        }

        //instanciando as views da activity

        txtNomeEstabelecimento = findViewById(R.id.textViewNomeEstabelecimentoConfirmar);
        txtEnderecoEstabelecimento = findViewById(R.id.textViewEnderecoEstabelecimentoConfirma);
        txtDuracaoServico = findViewById(R.id.txtDuracaoServicoConfirma);
        txtPrecoServico = findViewById(R.id.txtPrecoServicoConfirma);
        btnConfirmarReserva = findViewById(R.id.btn_confirmar_reserva);


        String[] horario = horarioEscolhido.split(":");
        int horas = Integer.parseInt(horario[0]);
        int minutos = Integer.parseInt(horario[1]);

        int soma = minutos + servicoEscolhido.getDuracao();

        String horaFinal = horas + ":"  + soma;

        //colocando conteudo nas views

        txtNomeEstabelecimento.setText(estabelecimentoEscolhido.getNomeEstabelecimento());
        txtEnderecoEstabelecimento.setText(
                estabelecimentoEscolhido.getBairro() + " "  + estabelecimentoEscolhido.getCidade() + "-" + estabelecimentoEscolhido.getEstado() + ","
                + estabelecimentoEscolhido.getCep()
        );

        txtPrecoServico.setText("R$" + servicoEscolhido.getPrecoServico());
        txtDuracaoServico.setText(horaEscolhida + " - " + CalculoHorario.calcularHorarioFinal(horaFinal));


        //setando os listenner nos views
        btnConfirmarReserva.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_confirmar_reserva:
                Toast.makeText(this,"TERMINAR DE FAZER RESERVA",Toast.LENGTH_LONG).show();
        }

    }
}
