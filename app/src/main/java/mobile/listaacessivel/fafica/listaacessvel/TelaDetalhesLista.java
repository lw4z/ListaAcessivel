package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import javax.xml.transform.dom.DOMLocator;


public class TelaDetalhesLista extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterDetalhesLista adapter;
    ArrayList<ItemCriarListaPasso3> produtos = new ArrayList<ItemCriarListaPasso3>();
    ArrayList<String> nome;
    ArrayList<Double> valor;
    ArrayList<String> marca;
    ArrayList<Integer> quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_lista);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Teste de Busca da lista

        //Com arrayList
        nome = new ArrayList<String>();
        nome.add("Nescau");
        nome.add("Refrigerante");
        nome.add("Carne filé");
        nome.add("Macarrão");

        marca = new ArrayList<String>();
        marca.add("Nestlé");
        marca.add("Jatobá");
        marca.add("Friboi");
        marca.add("Vitarella");

        valor = new ArrayList<Double>();
        valor.add(3.8);
        valor.add(2.7);
        valor.add(13.8);
        valor.add(1.8);

        quantidade = new ArrayList<Integer>();
        quantidade.add(3);
        quantidade.add(5);
        quantidade.add(1);
        quantidade.add(6);

        listaProdutos = (ListView) findViewById(R.id.listProdutosListaDetalhes);

        for (int i = 0; i < nome.size(); i++){
            ItemCriarListaPasso3  p = new ItemCriarListaPasso3(nome.get(i), marca.get(i),
                    valor.get(i), quantidade.get(i));
            //Colocando todos os itens no arrayList
            produtos.add(p);
        }

        adapter = new MyArrayAdapterDetalhesLista(this,produtos);

        listaProdutos.setAdapter(adapter);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_detalhes_lista, menu);
//        return true;
//    }
//
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
}
