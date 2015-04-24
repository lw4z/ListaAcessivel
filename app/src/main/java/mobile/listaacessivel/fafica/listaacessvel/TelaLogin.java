package mobile.listaacessivel.fafica.listaacessvel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;


public class TelaLogin extends ActionBarActivity {

    EditText email_usuario, senha_usuario;

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
        senha_usuario = (EditText)findViewById(R.id.campoSenha);

        //Teste de criação de Objeto JSON
        JSONObject usuario = new JSONObject();
        try{
            usuario.put("email",email_usuario);
            usuario.put("senha",senha_usuario);

        }catch(JSONException e){
            e.printStackTrace();
        }


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
