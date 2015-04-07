package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by ivan on 03/04/15.
 */
public class MyArrayAdapterCriarListaPasso3 extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    private List<ItemCriarListaPasso3> listaItemsPasso3 = null;
    private ArrayList<ItemCriarListaPasso3> listaProdutos;

    public MyArrayAdapterCriarListaPasso3(Context context, List<ItemCriarListaPasso3> listaItemsPasso3) {
        mContext = context;
        this.listaItemsPasso3 = listaItemsPasso3;
        inflater = LayoutInflater.from(mContext);
        this.listaProdutos = new ArrayList<ItemCriarListaPasso3>();
        this.listaProdutos.addAll(listaItemsPasso3);
    }

    public class ViewHolder {
        TextView nome_produto;
        TextView marca_produto;
        TextView valor_produto;
        NumberPicker quantidade_produto;
    }

    @Override
    public int getCount(){
        return listaItemsPasso3.size();
    }

    @Override
    public ItemCriarListaPasso3 getItem(int position){
        return listaItemsPasso3.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.layout_linha_tabela_criar_lista_passo_3, null);

            holder.nome_produto = (TextView) convertView.findViewById(R.id.textNomeProduto);
            holder.marca_produto = (TextView) convertView.findViewById(R.id.textMarcaProduto);
            holder.valor_produto = (TextView) convertView.findViewById(R.id.textValorProduto);
            holder.quantidade_produto = (NumberPicker) convertView.findViewById(R.id.campoQuantidadeProduto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nome_produto.setText(listaItemsPasso3.get(position).getNome_produto());
        holder.marca_produto.setText(listaItemsPasso3.get(position).getMarca());
        holder.valor_produto.setText(listaItemsPasso3.get(position).getValor_produto());
        holder.quantidade_produto.setValue(listaItemsPasso3.get(position).getQuantidade());
        holder.quantidade_produto.setMinValue(0);
        holder.quantidade_produto.setMaxValue(99);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ItemProdutoUnicoPasso3.class);

                intent.putExtra("nome_produto",(listaItemsPasso3.get(position).getNome_produto()));
                intent.putExtra("marca_produto",(listaItemsPasso3.get(position).getMarca()));
                intent.putExtra("valor_produto",(listaItemsPasso3.get(position).getValor_produto()));
                intent.putExtra("quantidade",(listaItemsPasso3.get(position).getQuantidade()));

                mContext.startActivity(intent);
            }
        });

        return convertView;

//        // 1. Cria o inflater
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        // 2. Recupera as linhas para o inflater
//        View rowView = inflater.inflate(R.layout.layout_linha_tabela_criar_lista_passo_3, parent, false);
//
//        // 3. Recupera o texto das duas linhas do rowView
//        TextView labelNome = (TextView) rowView.findViewById(R.id.textNomeProduto);
//        TextView valueMarca = (TextView) rowView.findViewById(R.id.textMarcaProduto);
//        TextView valueValor = (TextView) rowView.findViewById(R.id.textValorProduto);
//        NumberPicker valueQuantidade = (NumberPicker) rowView.findViewById(R.id.campoQuantidadeProduto);
//

//
//        // 4. Define o texto para cada textView
////        labelNome.setText(produtos.get(position).getNome_produto());
////        valueMarca.setText(listaProdutos.get(position).getMarca());
////        valueValor.setText(listaProdutos.get(position).getValor_produto());
////        valueQuantidade.setValue(listaProdutos.get(position).getQuantidade());
//
//        // 5. retorna a rowView
//        return rowView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listaItemsPasso3.clear();
        if (charText.length() == 0) {
            listaItemsPasso3.addAll(listaProdutos);
        } else {
            for (ItemCriarListaPasso3 p : listaProdutos) {
                if (p.getNome_produto().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    listaItemsPasso3.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }
}
