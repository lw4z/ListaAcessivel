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
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosEditarSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;


public class TelaDetalhesDoProdutoEditar extends ActionBarActivity {

    Button removerProduto;
    EditText quantidadeProduto;
    TextView txtNomeProduto, txtMarcaProduto, txtValidadeProduto, txtValorProduto, txtNomeEstabelecimento;
    ProdutoSession produtoSession = new ProdutoSession();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_do_produto_editar);

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

    //Métodos dos botões
    public void adicionarProdutoLista(View view){
        Intent it = new Intent(this,TelaEditarListaPasso1.class);
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

       // builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaDetalhesDoProdutoEditar.this,TelaEditarListaPasso1.class);
                ProdutoSession produtoSession = new ProdutoSession();
                Produto produto = produtoSession.getProduto();

                Log.i("PRODUTO",String.valueOf(produto.isSelecionado()));
                Log.i("PRODUTO",produto.getDescricao());

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
        ArrayListProdutosEditarSession listaProdutosSession = new ArrayListProdutosEditarSession();
        ArrayList<Produto> lista = listaProdutosSession.getListaProdutos();

        Log.i("LISTA",String.valueOf(lista.size()));
        Log.i("PRODUTO",produto.getDescricao());


        for(int i = 0; i < lista.size(); i++){
            if(produto.getId_produto() == lista.get(i).getId_produto()){
                lista.set(i,produto);
            }
        }
        listaProdutosSession = new ArrayListProdutosEditarSession(lista);
        ArrayListProdutosSelecionadosSession listProdutosSession = new ArrayListProdutosSelecionadosSession(lista);
    }
}
