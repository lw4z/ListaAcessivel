package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterDetalhesLista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosEditarSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosNaoSelecionadosEditarPasso2;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.SituacaoLista;
import mobile.listaacessivel.fafica.listaacessvel.util.ipConection;


public class TelaDetalhesLista extends ActionBarActivity {

    TextView  txtDescricaoLista,
            txtSituacaoLista, txtDataCriacaoLista, txtNomeEstabelecimento,
            txtBairroEstabelecimento, txtRuaEstabelecimento, txtTelefone1Estabelecimento,
            txtTelefone2Estabelecimento, txtQuantidadeTotalProdutos, txtValorTotalLista;

    Button bt_editar, bt_solicitar, bt_remover;
    private String link, json_lista;
    private String ip = ipConection.IP.toString();
    Gson gson;

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

        //Condição para botoes editar e solicitar
        if(lista.getSituacao().equals("atendida") || lista.getSituacao().equals("criada")){
            //bt_editar.setVisibility(View.VISIBLE);
            bt_solicitar.setVisibility(View.VISIBLE);
            bt_remover.setVisibility(View.VISIBLE);
        }

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
        bt_editar = (Button) findViewById(R.id.btEditarLista);
        bt_solicitar = (Button) findViewById(R.id.btSolicitarEntrega);
        bt_remover = (Button) findViewById(R.id.btRemoverLista);
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

                solicitarLista();
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
                remover();
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

    public void solicitarLista(){
        Intent it = new Intent(TelaDetalhesLista.this,TelaDetalhesLista.class);

        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        lista.setSituacao(SituacaoLista.SOLICITADA.toString());

        int id_lista = lista.getId_lista();

        try {
            link = "http://" + ip + ":8080/ListaAcessivel/SolicitarListaMobileServlet?id_lista=" + id_lista;

            ConnectionHttp conection = new ConnectionHttp(TelaDetalhesLista.this);
            conection.execute(link);

            Log.i("CONECTION", conection.toString());

            String json = conection.get();
            Log.i("RESULTADOSolicitada", json.toString());

            if(json.equals("sucesso")){
                Toast.makeText(this,"Lista solicitada com sucesso!", Toast.LENGTH_LONG);
                startActivity(it);
                finish();
            }else{
                Toast.makeText(this,"Ocorreu um erro ao solicitar a lista!", Toast.LENGTH_LONG);
                return;
            }
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }

    public void remover(){
        Intent it = new Intent(TelaDetalhesLista.this,TelaMinhasListas.class);

        ListaSession listaSession = new ListaSession();
        Lista lista = listaSession.getLista();

        int id_lista = lista.getId_lista();
        Log.i("IDLISTA", String.valueOf(id_lista));

        try {
            link = "http://" + ip + ":8080/ListaAcessivel/ExcluirListaMobileServlet?id_lista=" + id_lista;

            ConnectionHttp conection = new ConnectionHttp(TelaDetalhesLista.this);
            conection.execute(link);

            Log.i("CONECTION", conection.toString());

            String json = conection.get();
            Log.i("RESULTADORemovida", json.toString());

            if(json.contains("sucesso")){
                Toast.makeText(this,"Lista excluida com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(it);
                finish();
            }else{
                Toast.makeText(this,"Ocorreu um erro ao excluir a lista!", Toast.LENGTH_LONG).show();
                return;
            }
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
    }
}
