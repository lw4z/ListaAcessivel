package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListEstabelecimentosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaCriarListaPasso1 extends ActionBarActivity {

    private String link;
    private int id_cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo1);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ClienteSession clienteSession = new ClienteSession();
        Cliente cliente = clienteSession.getCliente();
        id_cliente = cliente.getId_usuario();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_criar_lista_passo1, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_about) {
//            Intent sobre = new Intent(this,TelaSobre.class);
//            startActivity(sobre);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //Métodos dos Botoẽs da tela
    public void getCategoriaPadaria(View view){
        String categoria = "padaria";

        Intent padaria = new Intent(this,TelaCriarListaPasso2.class);
        link = "http://localhost:8080/ListaAcessivel/CriarListaPasso1MobileServlet?categoria=" + categoria + "&id_cliente=" + id_cliente;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON", json.toString());
            //padaria.putExtra("listaEstabelecimentos", json);
            ArrayListEstabelecimentosSession listaEstabelecimentos = new ArrayListEstabelecimentosSession(converteArray(json));

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        //padaria.putExtra("categoria","padaria");
        startActivity(padaria);
    }

    public void getCategoriaMercado(View view){
        String categoria = "mercado";

        Intent mercado = new Intent(this,TelaCriarListaPasso2.class);
        link = "http://localhost:8080/ListaAcessivel/CriarListaPasso1MobileServlet?categoria=" + categoria + "&id_cliente=" + id_cliente;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON", json.toString());
            //mercado.putExtra("listaEstabelecimentos", json);
            ArrayListEstabelecimentosSession listaEstabelecimentos = new ArrayListEstabelecimentosSession(converteArray(json));

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        //mercado.putExtra("categoria","mercado");
        startActivity(mercado);
    }

    public void getCategoriaFarmacia(View view){
        String categoria = "farmacia";

        Intent farmacia = new Intent(this,TelaCriarListaPasso2.class);
        link = "http://localhost:8080/ListaAcessivel/CriarListaPasso1MobileServlet?categoria=" + categoria + "&id_cliente=" + id_cliente;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON", json.toString());
            //farmacia.putExtra("listaEstabelecimentos", json);
            ArrayListEstabelecimentosSession listaEstabelecimentos = new ArrayListEstabelecimentosSession(converteArray(json));

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        //farmacia.putExtra("categoria","farmacia");
        startActivity(farmacia);
    }

    public void getCategoriaLivraria(View view){
        String categoria = "livraria";

        Intent livraria = new Intent(this,TelaCriarListaPasso2.class);
        link = "http://localhost:8080/ListaAcessivel/CriarListaPasso1MobileServlet?categoria=" + categoria + "&id_cliente=" + id_cliente;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON", json.toString());
            //livraria.putExtra("listaEstabelecimentos", json);
            ArrayListEstabelecimentosSession listaEstabelecimentos = new ArrayListEstabelecimentosSession(converteArray(json));

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        //livraria.putExtra("categoria","livraria");
        startActivity(livraria);
    }

    public ArrayList<Estabelecimento> converteArray(String json){

        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
        Gson gson = new Gson();

        Estabelecimento[] estabelecimentosArray = gson.fromJson(json,Estabelecimento[].class);
        for(Estabelecimento e : estabelecimentosArray){
            estabelecimentos.add(e);
        }
        return estabelecimentos;
    }

}
