package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterDetalhesLista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosEditarSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;


public class TelaDetalhesLista extends ActionBarActivity {

    TextView  txtDescricaoLista,
            txtSituacaoLista, txtDataCriacaoLista, txtNomeEstabelecimento,
            txtBairroEstabelecimento, txtRuaEstabelecimento, txtTelefone1Estabelecimento,
            txtTelefone2Estabelecimento, txtQuantidadeTotalProdutos, txtValorTotalLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_lista);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inicializacao();

        ListaSession listaSession = new ListaSession();
        Log.i("LISTASESSAO2",String.valueOf(listaSession));
        Lista lista = listaSession.getLista();

        txtDescricaoLista.setText(lista.getDescricao());
        txtSituacaoLista.setText(lista.getSituacao());
        txtDataCriacaoLista.setText(lista.getData_criacao());
        txtNomeEstabelecimento.setText(lista.getEstabelecimento().getNome_fantasia());
        txtBairroEstabelecimento.setText(lista.getEstabelecimento().getEndereco().getBairro());
        txtRuaEstabelecimento.setText(lista.getEstabelecimento().getEndereco().getRua());
        txtTelefone1Estabelecimento.setText(lista.getEstabelecimento().getTelefones().get(0));
        txtTelefone2Estabelecimento.setText(lista.getEstabelecimento().getTelefones().get(1));
        txtQuantidadeTotalProdutos.setText(String.valueOf(lista.getQuantidade_total()) + " produtos");
        txtValorTotalLista.setText("R$ " + String.valueOf(lista.getValor_total()));

      }

    public void inicializacao(){
        txtDescricaoLista = (TextView) findViewById(R.id.txtDescricaoLista);
        txtSituacaoLista = (TextView) findViewById(R.id.txtSituacaoLista);
        txtDataCriacaoLista = (TextView) findViewById(R.id.txtDataCriacaoLista);
        txtNomeEstabelecimento = (TextView) findViewById(R.id.txtNomeEstabelecimento);
        txtBairroEstabelecimento = (TextView) findViewById(R.id.txtBairroEstabelecimento);
        txtRuaEstabelecimento = (TextView) findViewById(R.id.txtRuaEstabelecimento);
        txtTelefone1Estabelecimento = (TextView) findViewById(R.id.txtTelefone1Estabelecimento);
        txtTelefone2Estabelecimento = (TextView) findViewById(R.id.txtTelefone2Estabelecimento);
        txtQuantidadeTotalProdutos = (TextView) findViewById(R.id.txtQuantidadeTotalProdutos);
        txtValorTotalLista = (TextView) findViewById(R.id.txtValorTotalLista);
    }

    //Métodos dos botões
    public void editarLista(View view){
        Intent it = new Intent(this,TelaEditarListaPasso1.class);
        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        ArrayList<Produto> arrayProdutos = new ArrayList<Produto>();
        for(Produto p: lista.getProdutos()){
            arrayProdutos.add(p);
        }

        ArrayListProdutosEditarSession listaProdutosSession = new ArrayListProdutosEditarSession(arrayProdutos);
        startActivity(it);
    }

    public void solicitarEntrega(View view){
        getMessage("Deseja realmente solicitar a entrega da lista?");
    }

    public void removerLista(View view){
        getMessageRemover("Deseja realmente remover esta lista?");
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
                Intent it = new Intent(TelaDetalhesLista.this,TelaMinhasListas.class);
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

    public void getMessageRemover(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaDetalhesLista.this,TelaMinhasListas.class);
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
}
