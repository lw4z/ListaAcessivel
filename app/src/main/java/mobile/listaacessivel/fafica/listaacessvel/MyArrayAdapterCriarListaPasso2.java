package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ivan on 02/04/15.
 */
public class MyArrayAdapterCriarListaPasso2 extends ArrayAdapter<ItemCriarListaPasso2> {

    private final Context context;
    private final ArrayList<ItemCriarListaPasso2> listaEstabelecimentos;

    public MyArrayAdapterCriarListaPasso2(Context context, ArrayList<ItemCriarListaPasso2> listaEstabelecimentos) {

        super(context, R.layout.layout_linha_tabela_criar_lista_passo_2, listaEstabelecimentos);

        this.context = context;
        this.listaEstabelecimentos = listaEstabelecimentos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Cria o inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Recupera as linhas para o inflater
        View rowView = inflater.inflate(R.layout.layout_linha_tabela_criar_lista_passo_2, parent, false);

        // 3. Recupera o texto das duas linhas do rowView
        TextView labelNome = (TextView) rowView.findViewById(R.id.textNomeEstabelecimento);
        TextView valueBairro = (TextView) rowView.findViewById(R.id.textBairroEstabelecimento);
        TextView valueCidade = (TextView) rowView.findViewById(R.id.textCidadeEstabelecimento);

        // 4. Define o texto para cada textView
        labelNome.setText(listaEstabelecimentos.get(position).getNome());
        valueBairro.setText(listaEstabelecimentos.get(position).getBairro());
        valueCidade.setText(listaEstabelecimentos.get(position).getCidade());

        // 5. retorna a rowView
        return rowView;
    }
}
