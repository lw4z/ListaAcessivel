package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ivan on 03/04/15.
 */
public class MyArrayAdapterCriarListaPasso3 extends ArrayAdapter<ItemCriarListaPasso3>{

    private final Context context;
    private final ArrayList<ItemCriarListaPasso3> listaProdutos;

    public MyArrayAdapterCriarListaPasso3(Context context, ArrayList<ItemCriarListaPasso3> listaProdutos) {
        super(context, R.layout.layout_linha_tabela_criar_lista_passo_3, listaProdutos);
        this.context = context;
        this.listaProdutos = listaProdutos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Cria o inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Recupera as linhas para o inflater
        View rowView = inflater.inflate(R.layout.layout_linha_tabela_criar_lista_passo_3, parent, false);

        // 3. Recupera o texto das duas linhas do rowView
        TextView labelNome = (TextView) rowView.findViewById(R.id.textNomeProduto);
        TextView valueMarca = (TextView) rowView.findViewById(R.id.textMarcaProduto);
        TextView valueValor = (TextView) rowView.findViewById(R.id.textValorProduto);
        NumberPicker valueQuantidade = (NumberPicker) rowView.findViewById(R.id.campoQuantidadeProduto);

        valueQuantidade.setMinValue(0);
        valueQuantidade.setMaxValue(99);

        // 4. Define o texto para cada textView
        labelNome.setText(listaProdutos.get(position).getNome_produto());
        valueMarca.setText(listaProdutos.get(position).getMarca());
        valueValor.setText(listaProdutos.get(position).getValor_produto());
        valueQuantidade.setValue(listaProdutos.get(position).getQuantidade());

        // 5. retorna a rowView
        return rowView;
    }
}
