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


public class TelaLogin extends ActionBarActivity {

    EditText email_usuario, senha_usuario;
    String usuarioJsonString;

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

        //Teste de criação de Objeto JSON
        final JSONObject usuarioJson = new JSONObject();

        new Thread(){
            @Override
            public void run(){
                try{
                    String link = "http://10.0.2.2:8080/ListaAcessivel/IndexServlet";

                    URL url = new URL(link);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // se true indica que enviaremos dados (parâmetros) no corpo da requisição
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");

                    usuarioJson.put("email",email_usuario);
                    usuarioJson.put("senha",senha_usuario);

                    usuarioJsonString = usuarioJson.toString();

                    Log.v("Usuario",usuarioJsonString);
//                    String postParameters = "numero1=" + URLEncoder.encode("5", "iso-8859-1") + "&" +
//                            "numero2=" + URLEncoder.encode("8", "iso-8859-1");

                    // escreve os parâmetros no corpo da requisição
                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                    dos.writeBytes(usuarioJsonString);
                    dos.flush();
                    dos.close();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // lê a resposta como String
                        String resposta = readString(connection.getInputStream());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private String readString(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
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
        startActivity(it);
        finish();
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
