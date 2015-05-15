package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;


public class TelaDetalhesDoProdutoEditar2 extends ActionBarActivity {

    Button removerProduto;
    EditText quantidadeProduto;
    TextView txtNomeProduto, txtMarcaProduto, txtValidadeProduto, txtValorProduto, txtNomeEstabelecimento;
    ProdutoSession produtoSession = new ProdutoSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_do_produto_editar2);

        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Inicialização de campos da tela
        inicializaObjetos();

        ProdutoSession produtoSession = new ProdutoSession();
        Produto produto = produtoSession.getProduto();

        //Definição dos valores na tela
        txtNomeProduto.setText(produto.getDescricao());
        txtValorProduto.setText(String.valueOf(produto.getValor()));
        txtMarcaProduto.setText(produto.getMarca());
        txtValidadeProduto.setText(produto.getValidade());
        txtNomeEstabelecimento.setText(produto.getEstabelecimento().getNome_fantasia());


        //Botão remover produto
        removerProduto = (Button) findViewById(R.id.bt_remover_produto);
        removerProduto.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                //Ação do botão
                getMessageRemover("Deseja remover esse produto da sua lista?");
            }
        });

        //Condição para o botão aparecer
        if(produto.isSelecionado() == true){
            removerProduto.setVisibility(View.VISIBLE);
            quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
        }

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_detalhes_do_produto_editar2, menu);
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

    //Métodos dos Botões
    public void adicionarProdutoLista(View view){
        Intent it = new Intent(this,TelaEditarListaPasso2.class);
        int quantidade = Integer.parseInt(quantidadeProduto.getText().toString());

        ProdutoSession produtoSession = new ProdutoSession();
        Produto produto = produtoSession.getProduto();
        produto.setSelecionado(true);
        produto.setQuantidade(quantidade);

        setProduto(produto);
        Log.i("QUANTIDADEPRODUTO", String.valueOf(quantidade));
        startActivity(it);
        finish();
    }

    //Métodos de mensagem
    public AlertDialog alerta;

    public void getMessageRemover(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaDetalhesDoProdutoEditar2.this,TelaEditarListaPasso2.class);
                ProdutoSession produtoSession = new ProdutoSession();
                Produto produto = produtoSession.getProduto();
                produto.setSelecionado(false);
                produto.setQuantidade(0);
                setProduto(produto);

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

    public void inicializaObjetos(){
        quantidadeProduto = (EditText) findViewById(R.id.campoQuantidadeProduto);
        txtNomeProduto = (TextView) findViewById(R.id.txtNomeProduto);
        txtValidadeProduto = (TextView) findViewById(R.id.txtValidadeProduto);
        txtMarcaProduto = (TextView) findViewById(R.id.txtMarcaProduto);
        txtValorProduto = (TextView) findViewById(R.id.txtValorProduto);
        txtNomeEstabelecimento = (TextView) findViewById(R.id.txtNomeEstabelecimento);
    }

    public void setProduto(Produto produto){
        ArrayListProdutosSession listaProdutosJson = new ArrayListProdutosSession();
        ArrayList<Produto> lista = listaProdutosJson.getListaProdutos();

        for(int i = 0; i < lista.size(); i++){
            if(produto.getId_produto() == lista.get(i).getId_produto()){
                lista.set(i,produto);
            }
        }
        listaProdutosJson = new ArrayListProdutosSession(lista);
        ArrayListProdutosSelecionadosSession listProdutosSession = new ArrayListProdutosSelecionadosSession(lista);
    }
}
