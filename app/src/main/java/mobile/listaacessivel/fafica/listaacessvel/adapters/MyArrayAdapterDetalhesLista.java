package mobile.listaacessivel.fafica.listaacessvel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.listaacessivel.fafica.listaacessvel.R;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 09/04/15.
 */
public class MyArrayAdapterDetalhesLista extends BaseAdapter{
    Context mContext;
    LayoutInflater inflater;
    private List<Produto> listaItemsPasso3 = null;
    private ArrayList<Produto> listaProdutos;

    public MyArrayAdapterDetalhesLista(Context context, List<Produto> listaItemsPasso3) {
        mContext = context;
        this.listaItemsPasso3 = listaItemsPasso3;
        inflater = LayoutInflater.from(mContext);
        this.listaProdutos = new ArrayList<Produto>();
        this.listaProdutos.addAll(listaItemsPasso3);
    }
    public class ViewHolder {
        TextView nome_produto;
        TextView marca_produto;
        TextView valor_produto;
        TextView selecao;
        TextView quantidade_produto;
    }

    @Override
    public int getCount(){
        return listaItemsPasso3.size();
    }

    @Override
    public Produto getItem(int position){
        return listaItemsPasso3.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @SuppressWarnings("static-access")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Produto produto = listaProdutos.get(position);
        final ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.layout_linha_tabela_detalhes_lista, null);

            holder.nome_produto = (TextView) convertView.findViewById(R.id.textNomeProduto);
            holder.marca_produto = (TextView) convertView.findViewById(R.id.textMarcaProduto);
            holder.valor_produto = (TextView) convertView.findViewById(R.id.textValorProduto);
            holder.selecao = (TextView) convertView.findViewById(R.id.textSelecaoProduto);
            holder.quantidade_produto = (TextView) convertView.findViewById(R.id.textQuantidadeProduto);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(produto != null){
            holder.nome_produto.setText(produto.getNome_produto());
            holder.marca_produto.setText("Marca: " + produto.getMarca());
            holder.valor_produto.setText("Valor: R$ " + Double.toString(produto.getValor_produto()));
            //holder.selecao.setText(produto.getSelecao());
            holder.quantidade_produto.setText(Integer.toString(produto.getQuantidade()));
        }

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
//            for (Produto p : listaProdutos) {
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
