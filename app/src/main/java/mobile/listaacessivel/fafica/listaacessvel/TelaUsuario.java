package mobile.listaacessivel.fafica.listaacessvel;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TelaUsuario extends ActionBarActivity {

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
        }

        return super.onOptionsItemSelected(item);
    }

    //Métodos dos Botoẽs da tela
    public void getPerfil(View view){
        Intent sobre = new Intent(this,TelaPerfilUsuario.class);
        startActivity(sobre);
    }

    public void visualizarListas(View view){

    }

    public void criarLista(View view){
        Intent it = new Intent(this,TelaCriarListaPasso1.class);
        startActivity(it);
    }
}

