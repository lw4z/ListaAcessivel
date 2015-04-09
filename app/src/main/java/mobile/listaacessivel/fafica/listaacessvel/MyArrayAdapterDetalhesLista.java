package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mobile.listaacessivel.fafica.listaacessvel.util.Acentuacao;

/**
 * Created by ivan on 09/04/15.
 */
public class MyArrayAdapterDetalhesLista extends BaseAdapter{
    Context mContext;
    LayoutInflater inflater;
    private List<ItemCriarListaPasso3> listaItemsPasso3 = null;
    private ArrayList<ItemCriarListaPasso3> listaProdutos;

    public MyArrayAdapterDetalhesLista(Context context, List<ItemCriarListaPasso3> listaItemsPasso3) {
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
        TextView quantidade_produto;
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

            convertView = inflater.inflate(R.layout.layout_linha_tabela_detalhes_lista, null);

            holder.nome_produto = (TextView) convertView.findViewById(R.id.textNomeProduto);
            holder.marca_produto = (TextView) convertView.findViewById(R.id.textMarcaProduto);
            holder.valor_produto = (TextView) convertView.findViewById(R.id.textValorProduto);
            holder.quantidade_produto = (TextView) convertView.findViewById(R.id.textQuantidadeProduto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nome_produto.setText(listaItemsPasso3.get(position).getNome_produto());
        holder.marca_produto.setText("Marca: " + listaItemsPasso3.get(position).getMarca());
        holder.valor_produto.setText("Valor: R$ " + Double.toString(listaItemsPasso3.get(position).getValor_produto()));
        holder.quantidade_produto.setText(Integer.toString(listaItemsPasso3.get(position).getQuantidade()));
        return convertView;
    }

//    public void filter(String charText) {
//        charText = Acentuacao.limparAcentuacao(charText);
//        charText = charText.toLowerCase(Locale.getDefault());
//
//        listaItemsPasso3.clear();
//        if (charText.length() == 0) {
//            listaItemsPasso3.addAll(listaProdutos);
//        } else {
//            for (ItemCriarListaPasso3 p : listaProdutos) {
//                String produto = Acentuacao.limparAcentuacao(p.getNome_produto());
//                if (produto.toLowerCase(Locale.getDefault())
//                        .contains(charText)) {
//                    listaItemsPasso3.add(p);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

}
