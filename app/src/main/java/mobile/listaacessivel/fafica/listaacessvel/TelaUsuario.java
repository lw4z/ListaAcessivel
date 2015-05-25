package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
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
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaUsuario extends ActionBarActivity {

    private String link;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        getSupportActionBar().setTitle("Área do Usuário");

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
            getMessage("Deseja realmente fazer Logout de sua conta no aplicativo?");
        }
        return super.onOptionsItemSelected(item);
    }

    //Métodos dos Botoẽs da tela
    public void getPerfil(View view){
        Intent perfil = new Intent(this,TelaPerfilUsuario.class);
        startActivity(perfil);
    }

    public void visualizarListas(View view){
        Intent listas = new Intent(this,TelaMinhasListas.class);
//        ConnectionHttp conection = new ConnectionHttp(TelaUsuario.this);
//        conection.execute(link);
//
//        Log.i("CONECTION", conection.toString());
//
//        try {
//            String json = conection.get();
//            Log.i("RESULTADOJSON",json.toString());
//            listas.putExtra("listaListas", json);
//
//        }catch (InterruptedException e1) {
//            e1.printStackTrace();
//        } catch (ExecutionException e1) {
//            e1.printStackTrace();
//        }
        startActivity(listas);
    }

    public void criarLista(View view){
        Intent it = new Intent(this,TelaCriarListaPasso1.class);
        startActivity(it);
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
                Intent it = new Intent(TelaUsuario.this,TelaLogin.class);
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

