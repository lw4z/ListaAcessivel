package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class TelaCriarListaPasso3 extends ActionBarActivity {

    ListView listaProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo3);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        try {
            MyArrayAdapterCriarListaPasso3 adapter = new MyArrayAdapterCriarListaPasso3(this, criarDados());

            // 2. Recupera o ListView para o activity_main.xml
            listaProdutos = (ListView) findViewById(R.id.listViewProdutos);

            // 3. setListAdapter
            listaProdutos.setAdapter(adapter);
        }catch (Exception e){

        }

    }

    //Método que recebe os dados para a lista
    private ArrayList<ItemCriarListaPasso3> criarDados(){
        ArrayList<ItemCriarListaPasso3> items = new ArrayList<ItemCriarListaPasso3>();
        items.add(new ItemCriarListaPasso3("Nescal","Marca: " + "Nestle", "Valor: " + "R$ 3,80",3));
        items.add(new ItemCriarListaPasso3("Refrigerante","Marca: " + "Jatobá", "Valor: " + "R$ 2,70",8));
        items.add(new ItemCriarListaPasso3("Carne Bovina","Marca: " + "Friboi", "Valor: " + "R$ 13,80",5));
        items.add(new ItemCriarListaPasso3("Macarrão","Marca: " + "Vitarella", "Valor: " + "R$ 1,80",12));

        return items;
    }

    //Método para pesquisa de produto




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_criar_lista_passo3, menu);
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

    //Método dos Botões
    public void finalizarLista(View view){

    }
}
