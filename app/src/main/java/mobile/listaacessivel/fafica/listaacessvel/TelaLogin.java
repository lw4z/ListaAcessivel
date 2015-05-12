package mobile.listaacessivel.fafica.listaacessvel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaLogin extends ActionBarActivity {

    private EditText email_usuario, senha_usuario;
    private String email, senha;
    private String link;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Ocultando actionBar da tela
        getSupportActionBar().hide();

        //Inicialização de campos da tela
        email_usuario = (EditText) findViewById(R.id.campoEmail);
        senha_usuario = (EditText) findViewById(R.id.campoSenha);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_login, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //Métodos dos botões
    public void fazerLogin(View view){
        Intent it = new Intent(this,TelaUsuario.class);
        email = email_usuario.getText().toString();
        senha = senha_usuario.getText().toString();

        if (email != "" && senha != "") {
            try {
                link = "http://192.168.0.105:8080/ListaAcessivel/LoginMobileServlet?email=" + email + "&senha=" + senha;
                ConnectionHttp conection = new ConnectionHttp(TelaLogin.this);
                conection.execute(link);

                Log.i("CONECTION", conection.toString());
                String json = conection.get();
                Log.i("RESULTADOJSON", json.toString());
                //it.putExtra("usuario", json);
                gson = new Gson();
                Cliente clienteJson = gson.fromJson(json, Cliente.class);
                if(clienteJson == null){
                    Toast.makeText(this,"O usuário ou a senha estão incorretos",Toast.LENGTH_LONG).show();
                }else{
                    //Objeto da sessão
                    ClienteSession clienteSession = new ClienteSession(clienteJson);
                    Log.i("CLIENTELOGIN",String.valueOf(clienteJson));
                    startActivity(it);
                    finish();
                }

            }catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }else{
            Toast.makeText(this,"Preencha o email e a senha para prosseguir",Toast.LENGTH_LONG).show();
//            Intent it2 = new Intent(this,TelaLogin.class);
//            startActivity(it2);
        }

    }

    public void fazerCadastro(View view){
        Intent it = new Intent(this,TelaFormularioCadastroUsuario.class);
        startActivity(it);
    }

    public void esqueciMinhaSenha(View view){
        Intent it = new Intent(this,TelaEdicaoSenha.class);
        startActivity(it);
    }

}
