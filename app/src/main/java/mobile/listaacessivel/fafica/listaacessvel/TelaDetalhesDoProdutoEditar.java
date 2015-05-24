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
import android.widget.Toast;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosEditarSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ProdutoSession;


public class TelaDetalhesDoProdutoEditar extends ActionBarActivity {

    Button removerProduto, adicionarProduto;
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

        ListaSession listaSession = new ListaSession();
        Log.i("LISTASESSAO2",String.valueOf(listaSession));
        Lista lista = listaSession.getLista();

        //Definição dos valores na tela
        txtNomeProduto.setText(produto.getDescricao());
        txtValorProduto.setText("R$ " + String.valueOf(produto.getValor()));
        txtMarcaProduto.setText(produto.getMarca());
        txtValidadeProduto.setText(produto.getValidade());
        txtNomeEstabelecimento.setText(produto.getEstabelecimento().getNome_fantasia());

        //Visibilidade do botão adicionar
        adicionarProduto = (Button) findViewById(R.id.bt_adicionarProdutos);

        if(lista.getSituacao().equals("atendida") || lista.getSituacao().equals("criada")){
            adicionarProduto.setVisibility(View.VISIBLE);
        }

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
            if(lista.getSituacao().equals("atendida") || lista.getSituacao().equals("criada")) {
                removerProduto.setVisibility(View.VISIBLE);
            }
            quantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
        }

    }

    //Métodos dos botões
    public void adicionarProdutoLista(View view){
        Intent it = new Intent(this,TelaEditarListaPasso1.class);
        int quantidade = 0;

        if(!quantidadeProduto.getText().toString().equals("")){
            quantidade = Integer.parseInt(quantidadeProduto.getText().toString());
        }else{
            Toast.makeText(this,"Informe a quantidade do produto!", Toast.LENGTH_LONG).show();
            return;
        }

        ProdutoSession produtoSession = new ProdutoSession();
        Produto produto = produtoSession.getProduto();
        produto.setSelecionado(true);
        produto.setQuantidade(quantidade);

        if(quantidade >= 1){
            setProduto(produto);
            Log.i("QUANTIDADEPRODUTO",String.valueOf(quantidade));
            startActivity(it);
            finish();
        }else{
            Toast.makeText(this, "A quantidade do produto precisa ser mais que 0", Toast.LENGTH_LONG).show();
            return;
        }
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
