package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TelaMinhasListas extends ActionBarActivity {

    ListView listaListas;
    private ArrayList<String> listas = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_minhas_listas);

        //Botão de Voltar na actionBar
        try{
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Teste da lista

        listas.add("Lista da segunda feira");
        listas.add("Lista da terça feira");
        listas.add("Lista da quarta feira");
        listas.add("Lista da quinta feira");
        listas.add("Lista da sexta feira");
        listas.add("Lista do sábado");
        listas.add("Lista do domingo");

        try{
            listaListas = (ListView) findViewById(R.id.listDetalhesLista);
            listaListas.setAdapter(new ArrayAdapter<String>(this, R.layout.layout_linha_tabela,R.id.text1 ,listas));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_minhas_listas, menu);
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
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //Métodos dos botões

    public void detalhesLista(View view){

    }
}
