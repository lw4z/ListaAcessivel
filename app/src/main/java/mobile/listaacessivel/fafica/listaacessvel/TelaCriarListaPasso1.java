package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TelaCriarListaPasso1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo1);

        //Botão de Voltar na actionBar
        try{
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    public void getCategoriaPadaria(View view){

    }

    public void getCategoriaMercado(View view){

    }

    public void getCategoriaFarmacia(View view){

    }

    public void getCategoriaLivraria(View view){

    }

}