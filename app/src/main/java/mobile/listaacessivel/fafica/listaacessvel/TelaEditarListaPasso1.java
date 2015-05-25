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
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosEditarSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosNaoSelecionadosEditarPasso2;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.SituacaoLista;
import mobile.listaacessivel.fafica.listaacessvel.util.IpConection;


public class TelaEditarListaPasso1 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    ArrayList<Produto> produtos = null;
    ArrayList<Produto> produtosPesquisa = new ArrayList<Produto>();
    ArrayList<Produto> produtosTemporarios = new ArrayList<Produto>();
    TextView txtNomeProduto;
    Button btPesquisar, bt_FinalizarLista, bt_adicionarProdutos;
    LinearLayout layout;
    boolean flag = false;
    private int id_lista;
    private String link, json_lista;
    private String ip = IpConection.IP.toString();
    Gson gson;

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
        setContentView(R.layout.activity_tela_editar_lista_passo1);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Declaração de itens da tela
        inicializacao();
        instance();
        btn_prev = (Button)findViewById(R.id.prev);
        btn_next = (Button)findViewById(R.id.next);

        btn_prev.setEnabled(false);

        ArrayListProdutosEditarSession listaProdutosSession = new ArrayListProdutosEditarSession();

        produtos = listaProdutosSession.getListaProdutos();

        TOTAL_LIST_ITEMS = produtos.size();

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        val = val==0?0:1;
        pageCount = TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;

        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        bt_FinalizarLista = (Button) findViewById(R.id.bt_FinalizarLista);
        bt_adicionarProdutos = (Button) findViewById(R.id.bt_adicionarProdutos);

        if(lista.getSituacao().equals("atendida") || lista.getSituacao().equals("criada")){
            bt_adicionarProdutos.setVisibility(View.VISIBLE);
            bt_FinalizarLista.setVisibility(View.VISIBLE);
        }


        //Carregamento da lista de produtos inicial
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
            Toast.makeText(this,"Não existem produtos nesta lista!", Toast.LENGTH_LONG).show();
        }

        //Métodos do botão pesquisar
        btPesquisar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomeProduto = txtNomeProduto.getText().toString();

                String charText = Acentuacao.limparAcentuacao(nomeProduto);
                produtosPesquisa.clear();
                Log.i("NOMEPESQUISADO", nomeProduto);
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
                    Toast.makeText(TelaEditarListaPasso1.this,"Nenhum produto encontrado!", Toast.LENGTH_SHORT).show();
                }
                TOTAL_LIST_ITEMS = tamanho;

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

            }
        });

        try {
            //Envio do produto para a próxima tela
            listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(view.getContext(), TelaDetalhesDoProdutoEditar.class);

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

    //Método que gera os botões de paginação
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
        Log.i("PRODUTOSTEMPORARIOS", String.valueOf(produtosTemporarios.size()));
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

    //Métodos dos botões
    public void adicionarMaisProdutos(View view){
        editarPasso1();
        editarPasso2();
    }

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
                Intent it = new Intent(TelaEditarListaPasso1.this,TelaDetalhesLista.class);
                ListaSession listaSession = new ListaSession();
                Lista lista = listaSession.getLista();

                int id_lista = lista.getId_lista();

                String descricao = lista.getDescricao();
                String situacao = SituacaoLista.CRIADA.toString();

                //ClienteSession clienteSession = new ClienteSession();
                Cliente cliente = lista.getCliente();

                //EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession();
                Estabelecimento estabelecimento = lista.getEstabelecimento();

                //Produtos modificados na tela de detalhes
                ArrayListProdutosSelecionadosSession listaProdutosJson = new ArrayListProdutosSelecionadosSession();
                ArrayList<Produto> listaProdutosModificados = listaProdutosJson.getListaProdutos();

                //Lista de produtos final
                ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

                Log.i("TAMANHOSELECIONADOS",String.valueOf(listaProdutosSelecionados.size()));
                Log.i("TAMANHOTEMPORARIOS",String.valueOf(produtosTemporarios.size()));
               // Log.i("TAMANHOMODIFICADOS",String.valueOf(listaProdutosModificados.size()));

                //Comparação de diferencas entre as listas de produtos
                for(int i = 0; i < produtosTemporarios.size(); i++){
                    if(listaProdutosModificados != null) {
                        if (produtosTemporarios.get(i).getId_produto() == listaProdutosModificados.get(i).getId_produto() && listaProdutosModificados.get(i).isSelecionado() == true) {
                            listaProdutosSelecionados.add(listaProdutosModificados.get(i));
                        }
                        if (produtosTemporarios.get(i).getId_produto() != listaProdutosModificados.get(i).getId_produto()) {
                            listaProdutosSelecionados.add(produtosTemporarios.get(i));
                        }
                    }else{
                        listaProdutosSelecionados.add(produtosTemporarios.get(i));
                    }
                }

                //Removendo possíveis produtos não selecionados
                for(int i = 0; i < listaProdutosSelecionados.size(); i ++){
                    if(listaProdutosSelecionados.get(i).isSelecionado() == false){
                        listaProdutosSelecionados.remove(listaProdutosSelecionados.get(i));
                    }
                }

                Log.i("TAMANHOSELECIONADOS",String.valueOf(listaProdutosSelecionados.size()));
                Log.i("TAMANHOTEMPORARIOS",String.valueOf(produtosTemporarios.size()));

                try {
                    gson = new Gson();

                    Lista listaAtualizada = new Lista(id_lista, descricao, situacao, cliente, estabelecimento, listaProdutosSelecionados);

                    json_lista = gson.toJson(listaAtualizada);
                    Log.i("LISTA ANTES REPLACE",json_lista);
                    json_lista = json_lista.replaceAll(" ","<;>");
                    Log.i("LISTA",json_lista);

                    link = "http://" + ip + ":8080/ListaAcessivel/EditarListaPasso1MobileServlet?json_lista=" + URLEncoder.encode(json_lista, "UTF-8");
                    Log.i("LINK",link);

                    ConnectionHttp conection = new ConnectionHttp(TelaEditarListaPasso1.this);
                    conection.execute(link);
                    Log.i("CONECTION", conection.toString());

                    String json = conection.get();
                    Log.i("RESULTADOJSON", json);

                    Gson gson2 = new Gson();
                    Lista listaJson = gson2.fromJson(json,Lista.class);

                    ListaSession listaSessionAtualizada = new ListaSession(listaJson);
                    Log.i("LISTASESSAO1",String.valueOf(listaSessionAtualizada));
                    startActivity(it);
                    finish();

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
        txtNomeProduto = (EditText) findViewById(R.id.campoPesquisaProduto);
        btPesquisar = (Button) findViewById(R.id.btPesquisarProduto);
        layout = (LinearLayout) findViewById(R.id.buttonLayout);
    }

    public void editarPasso1(){

        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        int id_lista = lista.getId_lista();

        String descricao = lista.getDescricao();
        String situacao = SituacaoLista.CRIADA.toString();

        //ClienteSession clienteSession = new ClienteSession();
        Cliente cliente = lista.getCliente();

        //EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession();
        Estabelecimento estabelecimento = lista.getEstabelecimento();

        //Produtos modificados na tela de detalhes
        ArrayListProdutosSelecionadosSession listaProdutosJson = new ArrayListProdutosSelecionadosSession();
        ArrayList<Produto> listaProdutosModificados = listaProdutosJson.getListaProdutos();

        //Lista de produtos final
        ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

        for(int i = 0; i < produtosTemporarios.size(); i++){
            if(listaProdutosModificados != null) {
                if (produtosTemporarios.get(i).getId_produto() == listaProdutosModificados.get(i).getId_produto() && listaProdutosModificados.get(i).isSelecionado() == true) {
                    listaProdutosSelecionados.add(listaProdutosModificados.get(i));
                }
                if (produtosTemporarios.get(i).getId_produto() != listaProdutosModificados.get(i).getId_produto()) {
                    listaProdutosSelecionados.add(produtosTemporarios.get(i));
                }
            }else{
                listaProdutosSelecionados.add(produtosTemporarios.get(i));
            }
        }

        //Removendo possíveis produtos não selecionados
        for(int i = 0; i < listaProdutosSelecionados.size(); i ++){
            if(listaProdutosSelecionados.get(i).isSelecionado() == false){
                listaProdutosSelecionados.remove(listaProdutosSelecionados.get(i));
            }
        }

        Log.i("TAMANHOSELECIONADOS",String.valueOf(listaProdutosSelecionados.size()));

        ArrayListProdutosAdicionados produtosAdicionados = new ArrayListProdutosAdicionados(listaProdutosSelecionados);

        try {
            gson = new Gson();

            Lista listaAtualizada = new Lista(id_lista, descricao, situacao, cliente, estabelecimento, listaProdutosSelecionados);

            json_lista = gson.toJson(listaAtualizada);
            Log.i("LISTA ANTES REPLACE",json_lista);
            json_lista = json_lista.replaceAll(" ","<;>");
            Log.i("LISTA",json_lista);

            if(listaProdutosSelecionados.size() != 0){
                link = "http://" + ip + ":8080/ListaAcessivel/EditarListaPasso1MobileServlet?json_lista=" + URLEncoder.encode(json_lista, "UTF-8");
                Log.i("LINK",link);

                ConnectionHttp conection = new ConnectionHttp(TelaEditarListaPasso1.this);
                conection.execute(link);
                Log.i("CONECTION", conection.toString());

                String json = conection.get();
                Log.i("RESULTADOJSONPasso1", json);

                Gson gson2 = new Gson();
                Lista listaJson = gson2.fromJson(json,Lista.class);

                ListaSession listaSessionAtualizada = new ListaSession(listaJson);
                Log.i("LISTASESSAO1",String.valueOf(listaSessionAtualizada));
            }else{
                Toast.makeText(TelaEditarListaPasso1.this, "Não foi selecionado nenhum produto!", Toast.LENGTH_LONG).show();
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

    public void editarPasso2(){
        Intent it = new Intent(this,TelaEditarListaPasso2.class);

        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        id_lista = lista.getId_lista();

        try {
            link = "http://" + ip + ":8080/ListaAcessivel/EditarListaPasso2MobileServlet?id_lista=" + id_lista;

            ConnectionHttp conection = new ConnectionHttp(TelaEditarListaPasso1.this);
            conection.execute(link);

            Log.i("CONECTION",conection.toString());


            String json = conection.get();
            Log.i("RESULTADOListaPasso2",json.toString());
            ArrayListProdutosNaoSelecionadosEditarPasso2 listaProdutos = new ArrayListProdutosNaoSelecionadosEditarPasso2(converteArray(json));

            startActivity(it);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }

    public ArrayList<Produto> converteArray(String json){

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Gson gson = new Gson();

        Produto[] produtosArray = gson.fromJson(json, Produto[].class);
        if(produtosArray != null){
            for(Produto p : produtosArray){
                produtos.add(p);
            }
        }else{
            Toast.makeText(this, "Não foram encontrados produtos", Toast.LENGTH_LONG).show();
        }
        return produtos;
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

    public void instance(){
        produtos = new ArrayList<Produto>();
    }

}
