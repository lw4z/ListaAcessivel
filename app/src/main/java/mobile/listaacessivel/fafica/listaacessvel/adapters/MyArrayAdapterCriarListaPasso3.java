package mobile.listaacessivel.fafica.listaacessvel.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.listaacessivel.fafica.listaacessvel.R;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;

/**
 * Created by ivan on 03/04/15.
 */
public class MyArrayAdapterCriarListaPasso3 extends BaseAdapter{

    Context mContext;
    LayoutInflater inflater;
    private List<Produto> listaItemsPasso3 = null;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

    public EditText focuesdEditText;

    public MyArrayAdapterCriarListaPasso3(Context context, List<Produto> listaItemsPasso3) {
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
        //EditText quantidade_produto;
        //CheckBox selecaoProduto;
        int ref;
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

            convertView = inflater.inflate(R.layout.layout_linha_tabela_criar_lista_passo_3, null);

            holder.nome_produto = (TextView) convertView.findViewById(R.id.textNomeProduto);
            holder.marca_produto = (TextView) convertView.findViewById(R.id.textMarcaProduto);
            holder.valor_produto = (TextView) convertView.findViewById(R.id.textValorProduto);
            holder.selecao = (TextView) convertView.findViewById(R.id.textSelecaoProduto);
            //holder.quantidade_produto = (EditText) convertView.findViewById(R.id.campoQuantidadeProduto);
            //holder.selecaoProduto = (CheckBox) convertView.findViewById(R.id.checkSelecionarProtudo);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ref = position;


        if(produto != null){
            holder.nome_produto.setText(produto.getNome_produto());
            holder.marca_produto.setText("Marca: " + produto.getMarca());
            holder.valor_produto.setText("Valor: R$ " + Double.toString(produto.getValor_produto()));
            holder.selecao.setText(produto.getSelecao());
        }


        //Log
        String valor = String.valueOf(produto.getQuantidade());
        Log.i("VALOR DA QUANTIDADE",valor);
        Log.i("TAMANHO DA LISTA",String.valueOf(getCount()));

//        //Verificando se existe ou não valor no edit
//        if(listaItemsPasso3.get(position).getQuantidade() != 0) {
//            holder.quantidade_produto.setText("" + listaItemsPasso3.get(position).getQuantidade());
//        }else{
//            holder.quantidade_produto.setText("");
//        }
//
//        //Edit de quantidade de produtos
//        holder.quantidade_produto.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                try {
//                    listaItemsPasso3.get(holder.ref).setQuantidade(Integer.parseInt(s.toString()));
//                }catch (Exception e){
//
//                }
//            }
//        });
//
//        //CheckBox para selecionar produto
//        Produto produto = listaItemsPasso3.get(position);
//
//        holder.selecaoProduto.setChecked(produto.isSelecao());
//        holder.selecaoProduto.setTag(produto);
//
//        holder.selecaoProduto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                CheckBox check = (CheckBox) v;
//
//                Produto p = (Produto) v.getTag();
//                p.setSelecao(((CheckBox)v).isChecked());
//
//                if(check.isChecked()){
//                    if(!listaProdutosSelecionados.contains(p.getId_produto())){
//                        listaProdutosSelecionados.add(p);
//                    }
//                }else{
//                    if(listaProdutosSelecionados.contains(p.getId_produto())){
//                        listaProdutosSelecionados.add(p);
//                    }
//                }
//            }
//        });

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ItemProdutoUnicoPasso3.class);
//
//                intent.putExtra("nome_produto",(listaItemsPasso3.get(position).getNome_produto()));
//                intent.putExtra("marca_produto",(listaItemsPasso3.get(position).getMarca()));
//                intent.putExtra("valor_produto",(listaItemsPasso3.get(position).getValor_produto()));
//                intent.putExtra("quantidade",(listaItemsPasso3.get(position).getQuantidade()));
//
//                mContext.startActivity(intent);
//            }
//        });

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


