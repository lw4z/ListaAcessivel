package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ivan on 01/04/15.
 */
public class MyArrayAdapterDestalhesLista extends ArrayAdapter<ItemDetalhesLista>{
    private final Context context;
    private final ArrayList<ItemDetalhesLista> itemsArrayList;

    public MyArrayAdapterDestalhesLista(Context context, ArrayList<ItemDetalhesLista> itemsArrayList) {

        super(context, R.layout.layout_linha_tabela_detalhes_lista, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Cria o inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Recupera as linhas para o inflater
        View rowView = inflater.inflate(R.layout.layout_linha_tabela_detalhes_lista, parent, false);

        // 3. Recupera o texto das duas linhas do rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.text1);
        TextView valueView = (TextView) rowView.findViewById(R.id.text2);

        // 4. Define o texto para cada textView
        labelView.setText(itemsArrayList.get(position).getTitulo());
        valueView.setText(itemsArrayList.get(position).getDescricao());

        // 5. retorna a rowView
        return rowView;
    }
}
