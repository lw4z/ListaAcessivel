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
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.EstabelecimentoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.SituacaoLista;
import mobile.listaacessivel.fafica.listaacessvel.util.ipConection;


public class TelaFinalizarLista extends ActionBarActivity {

    private EditText campoDescricao;
    private String link, jsonLista;
    private String ip = ipConection.IP.toString();
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_finalizar_lista);

        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Inicialização de objetos
        campoDescricao = (EditText) findViewById(R.id.campoDescricaoLista);

    }

    public void finalizarLista(View view){
        getMessage("Deseja realmente finalizar a lista?");
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
                Intent it = new Intent(TelaFinalizarLista.this,TelaDetalhesLista.class);
                String descricao = campoDescricao.getText().toString();
                String situacao = SituacaoLista.CRIADA.toString();

                ClienteSession clienteSession = new ClienteSession();
                Cliente cliente = clienteSession.getCliente();

                EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession();
                Estabelecimento estabelecimento = estabelecimentoSession.getEstabelecimento();

                ArrayListProdutosSession listaProdutosJson = new ArrayListProdutosSession();

                ArrayList<Produto> listaProdutos = listaProdutosJson.getListaProdutos();
                ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

                for(Produto p : listaProdutos){
                    if(p.isSelecionado()){
                        listaProdutosSelecionados.add(p);
                    }
                }

                gson = new Gson();
                try {
                    Lista lista = new Lista(descricao, situacao, cliente, estabelecimento, listaProdutosSelecionados);

                    jsonLista = gson.toJson(lista);
                    link = "http://" + ip + ":8080/ListaAcessivel/CriarListaPasso3MobileServlet?jsonLista=" + jsonLista;
                    ConnectionHttp conection = new ConnectionHttp(TelaFinalizarLista.this);
                    conection.execute(link);
                    Log.i("CONECTION", conection.toString());
                    Log.i("LISTA",String.valueOf(jsonLista));

                    String resultado = conection.get();

                    lista = gson.fromJson(resultado, Lista.class);

                    //lista.setId_lista(id_lista);
                    ListaSession listaSession = new ListaSession(lista);

                    Log.i("RESULTADOCADASTRO",String.valueOf(conection.get()));
                }catch (InterruptedException e1) {
                    e1.printStackTrace();
                }catch (ExecutionException e1) {
                    e1.printStackTrace();
                }
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
