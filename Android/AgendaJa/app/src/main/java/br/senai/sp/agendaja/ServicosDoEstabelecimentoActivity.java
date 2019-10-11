package br.senai.sp.agendaja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agendaja.model.Phone;
import br.senai.sp.agendaja.model.PhoneCategory;
import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class ServicosDoEstabelecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos_do_estabelecimento);

        ExpandableLayout layout = (ExpandableLayout)findViewById(R.id.expandable_layout);

        layout.setRenderer(new ExpandableLayout.Renderer<PhoneCategory,Phone>() {

            @Override
            public void renderParent(View view, PhoneCategory phoneCategory, boolean isExpanded, int parentPosition) {
                ((TextView)view.findViewById(R.id.tv_parent_name)).setText(phoneCategory.name);
                view.findViewById(R.id.arrow).setBackgroundResource(isExpanded? R.drawable.ic_arrow: R.drawable.ic_arrow_dwon);
            }

            @Override
            public void renderChild(final View view, Phone phone, int parentPosition, int childPosition) {
                ((TextView)view.findViewById(R.id.tv_child_name)).setText(phone.name);
                ((TextView)view.findViewById(R.id.tv_child_name)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView txt = (TextView)view.findViewById(R.id.tv_child_name);
                        Toast.makeText(ServicosDoEstabelecimentoActivity.this, "Clicked : "+txt.getText(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        layout.addSection(getSection());
        layout.addSection(getSection());
        layout.addSection(getSection());
    }

    private Section<PhoneCategory,Phone> getSection(){
        Section<PhoneCategory,Phone> section = new Section<>();
        PhoneCategory phoneCategory = new PhoneCategory("Phone");

        List<Phone> listPhone = new ArrayList<>();
        for (int i=1;i<=5;i++)
            listPhone.add(new Phone("Phone "+i));
        section.parent = phoneCategory;
        section.children.addAll(listPhone);
        return section;
    }
}
