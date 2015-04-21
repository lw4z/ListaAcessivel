package mobile.listaacessivel.fafica.listaacessvel;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import mobile.listaacessivel.fafica.listaacessvel.util.Acentuacao;


public class TelaEditarListaPasso2 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    EditText editProcurar, quantidadeProduto;
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    ArrayList<Produto> produtosPesquisa = new ArrayList<Produto>();
    ArrayList<Produto> produtosTemporarios = new ArrayList<Produto>();
    TextView txtNomeProduto;
    Button btPesquisar;
    LinearLayout layout;
    private int noOfBtns;
    private Button[] btns;
    boolean flag = false;
    public int TOTAL_LIST_ITEMS = 8;
    public int NUM_ITEMS_PAGE   = 3;
    ArrayList<Integer> id_produto;
    ArrayList<String> nome;
    ArrayList<Double> valor;
    ArrayList<String> marca;
    ArrayList<String> selecao;
    ArrayList<Integer> quantidade;

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
        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);
        txtNomeProduto = (TextView) findViewById(R.id.campoPesquisaProduto);
        btPesquisar = (Button) findViewById(R.id.btPesquisarProduto);
        layout = (LinearLayout) findViewById(R.id.buttonLayout);

        //Teste de Busca da lista

        //Com arrayList
        id_produto = new ArrayList<Integer>();
        id_produto.add(1);
        id_produto.add(2);
        id_produto.add(3);
        id_produto.add(4);
        id_produto.add(5);
        id_produto.add(6);
        id_produto.add(7);
        id_produto.add(8);

        nome = new ArrayList<String>();
        nome.add("Nescau");
        nome.add("Refrigerante");
        nome.add("Carne filé");
        nome.add("Macarrão");
        nome.add("Sabão");
        nome.add("Arroz");
        nome.add("Sabonete");
        nome.add("Creme Dental");

        marca = new ArrayList<String>();
        marca.add("Nestlé");
        marca.add("Jatobá");
        marca.add("Friboi");
        marca.add("Vitarella");
        marca.add("Omo");
        marca.add("Rampinelli");
        marca.add("Lux");
        marca.add("Colgate");

        valor = new ArrayList<Double>();
        valor.add(3.8);
        valor.add(2.7);
        valor.add(13.8);
        valor.add(1.8);
        valor.add(1.4);
        valor.add(2.2);
        valor.add(1.5);
        valor.add(1.9);

        selecao = new ArrayList<String>();
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");
        selecao.add("Não selecionado");

        for(int i = 0; i < id_produto.size(); i++){
            final Produto p = new Produto(id_produto.get(i), nome.get(i), marca.get(i),
                    valor.get(i), selecao.get(i));
            produtos.add(p);
        }

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
                    String produto = Acentuacao.limparAcentuacao(p.getNome_produto());
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

                    intent.putExtra("id_produto",(produtos.get(position).getId_produto()));
                    Log.i("PRODUTO: ",String.valueOf(produtos.get(position).getId_produto()));
                    startActivity(intent);
                }
            });

        }catch (Exception e){

        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_editar_lista_passo2, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressLint("InlinedApi")
    private void setButtonsForPagination() {

        int val = TOTAL_LIST_ITEMS % NUM_ITEMS_PAGE;
        // val = val == 0 ? 0 : 1;
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


            // code to perform on click of each button
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                        /*if(flag){
                            loadList(j);
                        }
    */					loadListForSearch(j);

                    CheckBtnBackGroud(j);
                }
            });
        }

    }// end of setButtonsForPagination()

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

    private void loadListForSearch(int number) {
        int start = number * NUM_ITEMS_PAGE;
        produtosTemporarios.clear();
        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < produtosPesquisa.size()) {
                // sort.add(data.get(i));
                produtosTemporarios.add(produtosPesquisa.get(i));
            } else {
                break;
            }
        }
        adapter = new MyArrayAdapterCriarListaPasso3(getApplicationContext(),produtosTemporarios);
        listaProdutos.setAdapter(adapter);
    }


    private void loadList(int number) {

        int start = number * NUM_ITEMS_PAGE;
        //tempList.clear();
        for (int i = start; i < (start) + NUM_ITEMS_PAGE; i++) {
            if (i < produtos.size()) {
                // sort.add(data.get(i));
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
        getMessage("Finalizar Edição!","Deseja realmente finalizar a edição da lista?");
    }

    //Método de mensagem
    public AlertDialog alerta;

    public void getMessage(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaEditarListaPasso2.this,TelaMinhasListas.class);
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
}
