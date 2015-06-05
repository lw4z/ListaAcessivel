package mobile.listaacessivel.fafica.listaacessvel;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterCriarListaPasso3;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.Acentuacao;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;


public class TelaCriarListaPasso3 extends ActionBarActivity {

    private ListView listaProdutos;
    private MyArrayAdapterCriarListaPasso3 adapter;
    private ArrayList<Produto> produtos = null;
    private ArrayList<Produto> produtosPesquisa = new ArrayList<Produto>();
    private ArrayList<Produto> produtosTemporarios = new ArrayList<Produto>();
    private TextView txtNomeProduto;
    private Button btPesquisar;
    boolean flag = false;

   //Opções para novo metodo
    private Button btn_prev;
    private Button btn_next;
    private int pageCount ;
    private int increment = 0;
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
        getSupportActionBar().setTitle("Criar Lista passo 3");

        //Declaração de itens da tela
        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);
        txtNomeProduto = (EditText) findViewById(R.id.campoPesquisaProduto);
        btPesquisar = (Button) findViewById(R.id.btPesquisarProduto);
        //layout = (LinearLayout) findViewById(R.id.buttonLayout);
        btn_prev = (Button)findViewById(R.id.prev);
        btn_next = (Button)findViewById(R.id.next);

        btn_prev.setEnabled(false);

        ArrayListProdutosSession listaProdutosJson = new ArrayListProdutosSession();

        produtos = listaProdutosJson.getListaProdutos();

        TOTAL_LIST_ITEMS = produtos.size();

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val==0?0:1;
        pageCount = TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;

       // Carregamento da lista de produtos inicial
        if(produtos.size() > 0) {
            for(int i = 0; i < produtos.size(); i++){
                produtosPesquisa.add(produtos.get(i));
            }

            int tamanho = produtosPesquisa.size();
            TOTAL_LIST_ITEMS = tamanho;

            flag = true;
            if (pageCount <= 1){
                btn_next.setEnabled(false);
                btn_prev.setEnabled(false);
            }else{
                btn_next.setEnabled(true);
            }
            loadListForSearch(0);
            carregarBotoes();

        }else{
            Toast.makeText(this,"Não existem produtos neste estabelecimento!", Toast.LENGTH_LONG).show();
        }

        //Métodos do botão pesquisar
        btPesquisar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomeProduto = txtNomeProduto.getText().toString();

                String charText = Acentuacao.limparAcentuacao(nomeProduto);
                produtosPesquisa.clear();
                Log.i("NOMEPESQUISADO",nomeProduto);
                for(Produto p: produtos){
                    String produto = Acentuacao.limparAcentuacao(p.getDescricao());
                    Log.i("PRODUTOPESQUISA",produto);
                    if (produto.contains(charText)) {
                            produtosPesquisa.add(p);
                    }else{
                        if(charText != null) {
                            TOTAL_LIST_ITEMS = produtos.size();
                            increment = 0;
                            int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
                            val = val == 0 ? 0 : 1;
                            pageCount = TOTAL_LIST_ITEMS / NUM_ITEMS_PAGE + val;
                            loadListForSearch(0);
                            carregarBotoes();
                        }
                    }
                }

                int tamanho = produtosPesquisa.size();
                if(produtosPesquisa.size() == 0){
                    Toast.makeText(TelaCriarListaPasso3.this,"Nenhum produto encontrado!", Toast.LENGTH_SHORT).show();
                }
                TOTAL_LIST_ITEMS = tamanho;
                //layout.removeAllViews();
                int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
                val = val==0?0:1;
                pageCount = TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;
                if (pageCount <= 1){
                    btn_next.setEnabled(false);
                    btn_prev.setEnabled(false);
                }else{
                    btn_next.setEnabled(true);
                }
                flag = true;

                loadListForSearch(0);
                carregarBotoesPesquisa();

                Log.i("PAGECOUNTPESQUISAR", String.valueOf(pageCount));
                //setButtonsForPagination();
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

                    startActivity(intent);
                }
            });

        }catch (Exception e){

        }
        loadListForSearch(0);
        carregarBotoes();
    }

    public void carregarBotoes(){
        //loadListForSearch(0);

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment++;
                CheckEnable();
                loadListForSearch(increment);
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                CheckEnable();
                loadListForSearch(increment);
            }
        });
    }

    public void carregarBotoesPesquisa(){
        //loadListForSearch(0);

        btn_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment++;
                CheckEnablePesquisa();
                loadListForSearch(increment);
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                increment--;
                CheckEnablePesquisa();
                loadListForSearch(increment);
            }
        });
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
        Log.i("PRODUTOSPESQUISA",String.valueOf(produtosPesquisa.size()));
        Log.i("PRODUTOSTEMPORARIOS",String.valueOf(produtosTemporarios.size()));
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
    public void concluirLista(View view){
        getMessage("Deseja realmente concluir a criação da lista?");
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
                Intent it = new Intent(TelaCriarListaPasso3.this,TelaFinalizarLista.class);
                //ArrayListProdutosSession listProdutosSession = new ArrayListProdutosSession(produtosTemporarios);
                startActivity(it);
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

    private void CheckEnable(){

        Log.i("INCREMENTO", String.valueOf(increment));
        Log.i("PAGECOUNT", String.valueOf(pageCount));
        if (increment+1 > pageCount){
            btn_next.setEnabled(false);
            btn_prev.setEnabled(false);
        }
        if(increment+1 == pageCount){
            btn_next.setEnabled(false);
            btn_prev.setEnabled(true);
        }else if(increment == 0){
            btn_prev.setEnabled(false);
            btn_next.setEnabled(true);
        }else{
            btn_prev.setEnabled(true);
            btn_next.setEnabled(true);
        }
    }

    private void CheckEnablePesquisa(){

        Log.i("INCREMENTO", String.valueOf(increment));
        Log.i("PAGECOUNT", String.valueOf(pageCount));
        if (increment+1 > pageCount){
            btn_next.setEnabled(false);
            btn_prev.setEnabled(false);
        }
        if(increment+1 == pageCount){
            btn_next.setEnabled(false);
            btn_prev.setEnabled(true);
        }else if(increment == 0){
            btn_prev.setEnabled(false);
            btn_next.setEnabled(true);
        }else{
            btn_prev.setEnabled(true);
            btn_next.setEnabled(true);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        Boolean resultado = getIntent().getBooleanExtra("EXIT",false);

        if (resultado == true) {
            finish();
        }
    }

}
