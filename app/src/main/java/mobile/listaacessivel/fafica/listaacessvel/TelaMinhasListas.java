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

        try {
            MyArrayAdapter adapter = new MyArrayAdapter(this, criarDados());

            // 2. Recupera o ListView para o activity_main.xml
            listaListas = (ListView) findViewById(R.id.listDetalhesLista);

            // 3. setListAdapter
            listaListas.setAdapter(adapter);
        }catch (Exception e){

        }
    }

    //Método que recebe os dados para a lista
    private ArrayList<Item> criarDados(){
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Nome da lista: " + "Lista 1","Situação da lista: " + "atendida"));
        items.add(new Item("Nome da lista: " + "Lista 2","Situação da lista: " + "solicitada"));
        items.add(new Item("Nome da lista: " + "Lista 3","Situação da lista: " + "criada"));

        return items;
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
