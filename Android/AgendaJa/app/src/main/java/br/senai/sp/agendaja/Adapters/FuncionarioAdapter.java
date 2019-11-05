package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.senai.sp.agendaja.MainActivity;
import br.senai.sp.agendaja.Model.Funcionario;
import br.senai.sp.agendaja.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class FuncionarioAdapter extends RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>{

  private List<Funcionario> funcionarioList;
  private Context context;

  public FuncionarioAdapter(List<Funcionario> funcionarioList, Context context) {
    this.funcionarioList = funcionarioList;
    this.context = context;
  }

  @NonNull
  @Override
  public FuncionarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(context).inflate(R.layout.adapter_funcionarios,viewGroup,false);
    FuncionarioViewHolder funcionarioViewHolder = new FuncionarioViewHolder(view);

    return funcionarioViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull FuncionarioViewHolder funcionarioViewHolder, int i) {

    Funcionario funcionario = funcionarioList.get(i);

    if(funcionario.getFotoFuncionario()!=null && funcionario.getNomeFuncionario()!=null){
      Picasso
           .get()
           .load(MainActivity.IP_FOTO + funcionario.getFotoFuncionario())
           .resize(64,64)
           .into(funcionarioViewHolder.imagemFuncionario);

      funcionarioViewHolder.nomeFuncionario.setText(funcionario.getNomeFuncionario());

    }


  }

  @Override
  public int getItemCount() {
    return funcionarioList!=null?funcionarioList.size():0;
  }

  public class FuncionarioViewHolder extends RecyclerView.ViewHolder{

    private CircleImageView imagemFuncionario;
    private TextView nomeFuncionario;


    public FuncionarioViewHolder(@NonNull View itemView) {
      super(itemView);

      nomeFuncionario= itemView.findViewById(R.id.txt_nome_funcionario);
      imagemFuncionario = itemView.findViewById(R.id.img_foto_funcionario);

    }
  }

}
