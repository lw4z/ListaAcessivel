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


public class TelaUsuario extends ActionBarActivity {

    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent sobre = new Intent(this,TelaSobre.class);
            startActivity(sobre);
        }else if(id == R.id.action_logout){
            Intent login = new Intent(this,TelaLogin.class);
            startActivity(login);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Métodos dos Botoẽs da tela
    public void getPerfil(View view){
        Intent perfil = new Intent(this,TelaPerfilUsuario.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaUsuario.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            perfil.putExtra("perfilUsuario", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        startActivity(perfil);
    }

    public void visualizarListas(View view){
        Intent listas = new Intent(this,TelaMinhasListas.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaUsuario.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            listas.putExtra("listaListas", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        startActivity(listas);
    }

    public void criarLista(View view){
        Intent it = new Intent(this,TelaCriarListaPasso1.class);
        startActivity(it);
    }
}

