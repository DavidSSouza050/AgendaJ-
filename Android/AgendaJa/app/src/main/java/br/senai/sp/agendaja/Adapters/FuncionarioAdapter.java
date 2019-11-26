package br.senai.sp.agendaja.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
  private ClickFuncionario clickFuncionario;

  public FuncionarioAdapter(List<Funcionario> funcionarioList, Context context,ClickFuncionario clickFuncionario) {
    this.funcionarioList = funcionarioList;
    this.context = context;
    this.clickFuncionario = clickFuncionario;
  }

  @NonNull
  @Override
  public FuncionarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

    View view = LayoutInflater.from(context).inflate(R.layout.adapter_funcionarios,viewGroup,false);
    FuncionarioViewHolder funcionarioViewHolder = new FuncionarioViewHolder(view);

    return funcionarioViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull final FuncionarioViewHolder funcionarioViewHolder, int i) {

    final Funcionario funcionario = funcionarioList.get(i);

    if(funcionario.getFotoFuncionario()!=null && funcionario.getNomeFuncionario()!=null){
      Picasso
           .get()
           .load(MainActivity.IP_FOTO + funcionario.getFotoFuncionario())
           .resize(85,85)
           .into(funcionarioViewHolder.imagemFuncionario);

      funcionarioViewHolder.nomeFuncionario.setText(funcionario.getNomeFuncionario());

      funcionarioViewHolder.imagemFuncionario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          clickFuncionario.onClickFuncionario(funcionarioViewHolder.linearLayout,funcionario);
        }
      });

    }


  }

  @Override
  public int getItemCount() {
    return funcionarioList!=null?funcionarioList.size():0;
  }

  public class FuncionarioViewHolder extends RecyclerView.ViewHolder{

    private CircleImageView imagemFuncionario;
    private TextView nomeFuncionario;
    private LinearLayout linearLayout;

    public FuncionarioViewHolder(@NonNull View itemView) {
      super(itemView);

      nomeFuncionario= itemView.findViewById(R.id.txt_nome_funcionario);
      imagemFuncionario = itemView.findViewById(R.id.img_foto_funcionario);
      linearLayout = itemView.findViewById(R.id.linear_funcionarios);

    }
  }

  public interface ClickFuncionario{
    public void onClickFuncionario(LinearLayout linearLayout,Funcionario funcionario);
  }

}
