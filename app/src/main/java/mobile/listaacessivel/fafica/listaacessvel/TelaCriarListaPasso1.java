package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaCriarListaPasso1 extends ActionBarActivity {

    private String link;

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
        Intent padaria = new Intent(this,TelaCriarListaPasso2.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            padaria.putExtra("listaEstabelecimentos", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        padaria.putExtra("categoria","padaria");
        startActivity(padaria);
    }

    public void getCategoriaMercado(View view){

        Intent mercado = new Intent(this,TelaCriarListaPasso2.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            mercado.putExtra("listaEstabelecimentos", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        mercado.putExtra("categoria","mercado");
        startActivity(mercado);
    }

    public void getCategoriaFarmacia(View view){

        Intent farmacia = new Intent(this,TelaCriarListaPasso2.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            farmacia.putExtra("listaEstabelecimentos", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        farmacia.putExtra("categoria","farmacia");
        startActivity(farmacia);
    }

    public void getCategoriaLivraria(View view){

        Intent livraria = new Intent(this,TelaCriarListaPasso2.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso1.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            livraria.putExtra("listaEstabelecimentos", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        livraria.putExtra("categoria","livraria");
        startActivity(livraria);
    }

}
