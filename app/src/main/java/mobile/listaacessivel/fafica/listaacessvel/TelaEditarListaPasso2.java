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
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterCriarListaPasso3;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.Acentuacao;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosAdicionados;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.EstabelecimentoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.SituacaoLista;
import mobile.listaacessivel.fafica.listaacessvel.util.ipConection;


public class TelaEditarListaPasso2 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    ArrayList<Produto> produtosPesquisa = new ArrayList<Produto>();
    ArrayList<Produto> produtosTemporarios = new ArrayList<Produto>();
    TextView txtNomeProduto;
    Button btPesquisar;
    LinearLayout layout;
    private int noOfBtns;
    private Button[] btns;
    boolean flag = false;
    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE   = 3;
    private String link, json_lista;
    private String ip = ipConection.IP.toString();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_lista_passo2);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Declaração de itens da tela
        inicializacao();

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

                    Intent intent = new Intent(view.getContext(), TelaDetalhesDoProdutoEditar2.class);

                    Produto produto = produtosTemporarios.get(position);

                    ProdutoSession produtoSession = new ProdutoSession(produto);

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

    //Carregamento da lista de busca
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

    //Métodos dos Botões
    public void finalizarEdicao(View view){
        getMessage("Deseja realmente finalizar a edição da lista?");
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
                Intent it = new Intent(TelaEditarListaPasso2.this,TelaDetalhesLista.class);

                ListaSession listaSession = new ListaSession();
                Lista lista = listaSession.getLista();

                int id_lista = lista.getId_lista();

                String descricao = lista.getDescricao();
                String situacao = SituacaoLista.CRIADA.toString();

                //ClienteSession clienteSession = new ClienteSession();
                Cliente cliente = lista.getCliente();

                //EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession();
                Estabelecimento estabelecimento = lista.getEstabelecimento();

                //Produtos da sessao
                ArrayListProdutosAdicionados listaProdutosSession = new ArrayListProdutosAdicionados();
                ArrayList<Produto> produtosJaAdicionados = listaProdutosSession.getListaProdutos();

                //Produtos modificados na tela de detalhes
                ArrayListProdutosSelecionadosSession listaProdutosJson = new ArrayListProdutosSelecionadosSession();
                ArrayList<Produto> listaProdutosModificados = listaProdutosJson.getListaProdutos();

                Log.i("TAMANHOTEMPORARIOS",String.valueOf(produtosTemporarios.size()));

//                for(int i = 0; i < produtosTemporarios.size(); i++){
//                    if(listaProdutosModificados != null) {
//                        if (produtosTemporarios.get(i).getId_produto() == listaProdutosModificados.get(i).getId_produto() && listaProdutosModificados.get(i).isSelecionado() == true) {
//                            listaProdutosSelecionados.add(listaProdutosModificados.get(i));
//                        }
//                        if (produtosTemporarios.get(i).getId_produto() != listaProdutosModificados.get(i).getId_produto()) {
//                            listaProdutosSelecionados.add(produtosTemporarios.get(i));
//                        }
//                    }else{
//                        if (produtosTemporarios.get(i).getId_produto() != listaProdutosModificados.get(i).getId_produto()) {
//                            listaProdutosSelecionados.add(produtosTemporarios.get(i));
//                        }
//                    }
//                }

                //Lista de produtos final
                ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

                if(produtosJaAdicionados != null){
                    for(Produto p : produtosJaAdicionados) {
                        listaProdutosSelecionados.add(p);
                    }
                }

                Log.i("TAMANHOSELECIONADOS",String.valueOf(listaProdutosSelecionados.size()));

                if(listaProdutosModificados != null){
                    for(Produto p : listaProdutosModificados){
                        if(p.isSelecionado()){
                            listaProdutosSelecionados.add(p);
                        }
                    }
                }

                Log.i("TAMANHOSELECIONADOS",String.valueOf(listaProdutosSelecionados.size()));

                try {
                    gson = new Gson();

                    if(listaProdutosSelecionados.size() != 0){
                        Lista listaAtualizada = new Lista(id_lista, descricao, situacao, cliente, estabelecimento, listaProdutosSelecionados);

                        json_lista = gson.toJson(listaAtualizada);
                        Log.i("LISTA ANTES REPLACE",json_lista);
                        json_lista = json_lista.replaceAll(" ", "<;>");
                        Log.i("LISTA",json_lista);

                        link = "http://" + ip + ":8080/ListaAcessivel/EditarListaPasso1?json_lista=" + URLEncoder.encode(json_lista, "UTF-8");
                        Log.i("LINK",link);

                        ConnectionHttp conection = new ConnectionHttp(TelaEditarListaPasso2.this);
                        conection.execute(link);
                        Log.i("CONECTION", conection.toString());

                        String json = conection.get();
                        Log.i("RESULTADOJSON", json);

                        Gson gson2 = new Gson();
                        Lista listaJson = gson2.fromJson(json,Lista.class);

                        ListaSession listaSessionAtualizada = new ListaSession(listaJson);
                        Log.i("LISTASESSAO1", String.valueOf(listaSessionAtualizada));

                        startActivity(it);
                        finish();
                    }else{
                        Toast.makeText(TelaEditarListaPasso2.this, "Não foi selecionado nenhum produto!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }catch (InterruptedException e1) {
                    e1.printStackTrace();
                }catch (ExecutionException e1) {
                    e1.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }

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

    public void inicializacao(){
        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);
        txtNomeProduto = (TextView) findViewById(R.id.campoPesquisaProduto);
        btPesquisar = (Button) findViewById(R.id.btPesquisarProduto);
        layout = (LinearLayout) findViewById(R.id.buttonLayout);
    }
}
