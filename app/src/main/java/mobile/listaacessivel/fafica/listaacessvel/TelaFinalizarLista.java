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
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSelecionadosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.EstabelecimentoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.SituacaoLista;
import mobile.listaacessivel.fafica.listaacessvel.util.ipConection;


public class TelaFinalizarLista extends ActionBarActivity {

    private EditText campoDescricao;
    private String json_lista = "";
    private String ip = ipConection.IP.toString();
    private Gson gson;
    private String link;

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
            enviarJson();

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

    public Lista converteJson(String json){
        Gson gson = new Gson();
        Lista lista = gson.fromJson(json,Lista.class);

        return lista;
    }

    public void enviarJson(){

            Intent it = new Intent(TelaFinalizarLista.this,TelaDetalhesLista.class);
            String descricao = campoDescricao.getText().toString();
            String situacao = SituacaoLista.CRIADA.toString();

            ClienteSession clienteSession = new ClienteSession();
            Cliente cliente = clienteSession.getCliente();

            EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession();
            Estabelecimento estabelecimento = estabelecimentoSession.getEstabelecimento();

            ArrayListProdutosSelecionadosSession listaProdutosJson = new ArrayListProdutosSelecionadosSession();

            ArrayList<Produto> listaProdutos = listaProdutosJson.getListaProdutos();
            ArrayList<Produto> listaProdutosSelecionados = new ArrayList<Produto>();

            for(Produto p : listaProdutos){
                if(p.isSelecionado()){
                    listaProdutosSelecionados.add(p);
                }
            }

        try {
            gson = new Gson();
            Lista lista = new Lista(descricao, situacao, cliente, estabelecimento, listaProdutosSelecionados);
           // Lista lista = new Lista();
            //lista.setId_lista(900);

            json_lista = gson.toJson(lista);
            json_lista = json_lista.replaceAll(" ",";");
            Log.i("LISTA",json_lista);

            if(listaProdutosSelecionados.size() != 0){
                link = "http://" + ip + ":8080/ListaAcessivel/CriarListaPasso3MobileServlet?json_lista=" + json_lista;
                Log.i("LINK",link);

                ConnectionHttp conection = new ConnectionHttp(TelaFinalizarLista.this);
                conection.execute(link);
                Log.i("CONECTION", conection.toString());

                String json = conection.get();
                Log.i("RESULTADOJSON", json);

                    Gson gson2 = new Gson();
                    Lista listaJson = gson2.fromJson(json,Lista.class);

                    ListaSession listaSession = new ListaSession(listaJson);
                    Log.i("LISTASESSAO1",String.valueOf(listaSession));

                    startActivity(it);
                    finish();
            }else{
                Toast.makeText(this,"Não foi selecionado nenhum produto!", Toast.LENGTH_LONG).show();
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
}
