package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class TelaCriarListaPasso2 extends ActionBarActivity {

    ListView listaEstabelecimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo2);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        try {
            MyArrayAdapterCriarListaPasso2 adapter = new MyArrayAdapterCriarListaPasso2(this, criarDados());

            // 2. Recupera o ListView para o activity_main.xml
            listaEstabelecimentos = (ListView) findViewById(R.id.listViewEstabelecimentos);

            // 3. setListAdapter
            listaEstabelecimentos.setAdapter(adapter);
        }catch (Exception e){

        }
    }

    //Método que recebe os dados para a lista
    private ArrayList<ItemCriarListaPasso2> criarDados(){
        ArrayList<ItemCriarListaPasso2> items = new ArrayList<ItemCriarListaPasso2>();
        items.add(new ItemCriarListaPasso2("Bompreço","Bairro: " + "Centro", "Caruaru"));
        items.add(new ItemCriarListaPasso2("Compre Bem","Bairro: " + "Centro", "Caruaru"));
        items.add(new ItemCriarListaPasso2("Varejão","Bairro: " + "Vassoural", "Caruaru"));
        items.add(new ItemCriarListaPasso2("Ponto Frio","Bairro: " + "Cohabe 3", "Caruaru"));

        return items;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_criar_lista_passo2, menu);
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

    //Método do botão
    public void filtrarPorBairro(View view){

    }
}