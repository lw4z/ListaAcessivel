package mobile.listaacessivel.fafica.listaacessvel;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterCriarListaPasso3;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.Acentuacao;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;


public class TelaCriarListaPasso3 extends ActionBarActivity {

    private ListView listaProdutos;
    private MyArrayAdapterCriarListaPasso3 adapter;
    EditText editProcurar, quantidadeProduto;
    private ArrayList<Produto> produtos;
    private ArrayList<Produto> produtosPesquisa = new ArrayList<Produto>();
    private ArrayList<Produto> produtosTemporarios = new ArrayList<Produto>();
    //private ArrayList<Produto> produtosJson = new ArrayList<Produto>();
    private TextView txtNomeProduto;
    private Button btPesquisar;
    private LinearLayout layout;
    private int noOfBtns;
    private Button[] btns;
    boolean flag = false;
    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo3);

        instance();
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Declaração de itens da tela
        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);
        txtNomeProduto = (TextView) findViewById(R.id.campoPesquisaProduto);
        btPesquisar = (Button) findViewById(R.id.btPesquisarProduto);
        layout = (LinearLayout) findViewById(R.id.buttonLayout);

        //Teste de criação da lista

        int id_estabelecimento = getIntent().getIntExtra("id_estabelecimento", 0);
        Log.i("IDESTABELECIMENTO: ", String.valueOf(id_estabelecimento));

        String json = getIntent().getStringExtra("listaProdutos");


        ArrayListProdutosSession listaProdutosJson = new ArrayListProdutosSession();

        produtos = listaProdutosJson.getListaProdutos();

                TOTAL_LIST_ITEMS = produtos.size();
                Produto p = produtos.get(0);
                Log.e("Metodo TesteGson", p.getDescricao() + ", " + p.getValidade());


        Log.i("TAMANHOPRODUTOS",String.valueOf(produtos.size()));

        //Carregamento da lista de produtos inicial
        if(produtos != null) {
            for(int i = 0; i < produtos.size(); i++){
                produtosPesquisa.add(produtos.get(i));
            }

            int tamanho = produtosPesquisa.size();
            TOTAL_LIST_ITEMS = tamanho;
            layout.removeAllViews();
            flag = true;
            produtosTemporarios.clear();

            if(produtosPesquisa.size() < NUM_ITEMS_PAGE){
                for(int i =0; i < produtosPesquisa.size(); i ++){
                    produtosTemporarios.add(produtosPesquisa.get(i));
                }
                adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
                listaProdutos.setAdapter(adapter);
            }else{
                for(int i =0 ; i < NUM_ITEMS_PAGE; i++){
                    produtosTemporarios.add(produtosPesquisa.get(i));
                }
                adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
                listaProdutos.setAdapter(adapter);
            }
            setButtonsForPagination();
        }

        //Métodos do botão pesquisar
        btPesquisar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomeProduto = txtNomeProduto.getText().toString();

                String charText = Acentuacao.limparAcentuacao(nomeProduto);
                produtosPesquisa.clear();

                for(Produto p: produtos){
                    String produto = Acentuacao.limparAcentuacao(p.getDescricao());
                    if (produto.contains(charText)) {
                        produtosPesquisa.add(p);
                    }
                }

                int tamanho = produtosPesquisa.size();
                TOTAL_LIST_ITEMS = tamanho;
                layout.removeAllViews();
                flag = true;
                produtosTemporarios.clear();

                if(produtosPesquisa.size() < NUM_ITEMS_PAGE){
                    for(int i =0; i < produtosPesquisa.size(); i ++){
                        produtosTemporarios.add(produtosPesquisa.get(i));
                    }
                    adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
                    listaProdutos.setAdapter(adapter);
                }else{
                    for(int i =0 ; i < NUM_ITEMS_PAGE; i++){
                        produtosTemporarios.add(produtosPesquisa.get(i));
                    }
                    adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
                    listaProdutos.setAdapter(adapter);
                }
                setButtonsForPagination();
            }
        });

        try {
            //Envio do produto para a próxima tela
            listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(view.getContext(), TelaDetalhesDoProduto.class);

                    Produto produto = produtosTemporarios.get(position);

                    ProdutoSession produtoSession = new ProdutoSession(produto);

                    //intent.putExtra("id_produto",(produtosTemporarios.get(position).getId_produto()));
//                    intent.putExtra("selecao",produtos.get(position).isSelecionado());
//                    Log.i("PRODUTO: ",String.valueOf(produtos.get(position).getId_produto()));
                    startActivity(intent);
                }
            });

        }catch (Exception e){

        }

    }

    //Método que gera os botões de paginação
    @SuppressLint("InlinedApi")
    private void setButtonsForPagination() {

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;

        if (val == 0) {
            val = 0;
        } else {
            val = 1;
        }
        noOfBtns = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;

        btns = new Button[noOfBtns];

        for (int i = 0; i < noOfBtns; i++) {
            btns[i] = new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(
                    android.R.color.transparent));
            btns[i].setText("" + (i + 1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            layout.addView(btns[i], lp);

            final int j = i;

            // verificação dos cliques nos botões
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

    				loadListForSearch(j);

                    CheckBtnBackGroud(j);
                }
            });
        }
    }

    //Checagem dos cliques dos botões para modificação de cores e geração da assistência
    private void CheckBtnBackGroud(int index) {

        for (int i = 0; i < noOfBtns; i++) {
            if (i == index) {
                btns[index].setBackgroundColor(getResources().getColor(
                        android.R.color.darker_gray));
                btns[i].setTextColor(getResources().getColor(
                        android.R.color.white));
                btns[i].setWidth(2);
                btns[i].setContentDescription("Página atual de produtos numero: " + (i + 1));
            } else if(i < index){
                btns[i].setBackgroundColor(getResources().getColor(
                        android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(
                        android.R.color.black));
                btns[i].setWidth(2);
                btns[i].setContentDescription("Voltar para a página anterior de produtos numero: " + (i + 1));
            }else{
                btns[i].setBackgroundColor(getResources().getColor(
                        android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(
                        android.R.color.black));
                btns[i].setWidth(2);
                btns[i].setContentDescription("avançar para próxima página de produtos numero: " + (i + 1));
            }
        }

    }

    //Carregamento da lista de pesquisa
    private void loadListForSearch(int number) {
        int start = number * NUM_ITEMS_PAGE;
        produtosTemporarios.clear();
        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < produtosPesquisa.size()) {
                produtosTemporarios.add(produtosPesquisa.get(i));
            } else {
                break;
            }
        }
        adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
        listaProdutos.setAdapter(adapter);
    }

    //Carregamento da lista
    private void loadList(int number) {

        int start = number * NUM_ITEMS_PAGE;

        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < produtos.size()) {
                produtosTemporarios.add(produtos.get(i));
            } else {
                break;
            }
        }
        adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
        listaProdutos.setAdapter(adapter);

    }

    //Método dos Botões
    public void finalizarLista(View view){
        getMessage("Deseja realmente finalizar a criação da lista?");
    }

    //Método de mensagem
    public AlertDialog alerta;

    public void getMessage(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaCriarListaPasso3.this,TelaMinhasListas.class);
                startActivity(it);
                finish();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                return;
            }
        });
        //cria o AlertDialog e exibe na tela
        alerta = builder.create();
        alerta.show();
    }

    public void instance(){
        produtos = new ArrayList<Produto>();
    }

//    public void setGson(String json) {
//        gson = new Gson();
//
//       if(json != null) {
//           Produto[] produtosArray = gson.fromJson(json, Produto[].class);
//           String teste = "";
//           for (Produto p : produtosArray) {
//               produtos.add(p);
//           }
//           Produto p = produtos.get(0);
//           Log.e("Metodo TesteGson", p.getDescricao() + ", " + p.getValidade());
//       }
//    }

}
