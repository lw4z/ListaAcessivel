package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;


public class TelaEditarListaPasso1 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    EditText editProcurar;
    ArrayList<ItemCriarListaPasso3> produtos = new ArrayList<ItemCriarListaPasso3>();
    String [] nome;
    String [] marca;
    Double [] valor;
    Integer [] quantidade;
    ArrayList<String> nome2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_lista_passo1);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Teste de Busca da lista

        //Com arrayList
        nome2 = new ArrayList<String>();
        nome2.add("Nescau");
        nome2.add("Refrigerante");
        nome2.add("Carne filé");
        nome2.add("Macarrão");

        //Com array
        nome = new String[] {"Nescau","Refrigerante","Carne Bovina","Macarrão"};
        marca = new String[] {"Nestle","Jatobá","Friboi","Vitarella"};
        valor = new Double[] {3.80,2.70,13.80,1.80};
        quantidade = new Integer[] {3,8,5,12};

        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);

        for (int i = 0; i < nome2.size(); i++){
            ItemCriarListaPasso3  p = new ItemCriarListaPasso3(nome2.get(i), marca[i],
                    valor[i], quantidade[i]);
            //Colocando todos os itens da string no array
            produtos.add(p);
        }

        adapter = new MyArrayAdapterCriarListaPasso3(this,produtos);

        listaProdutos.setAdapter(adapter);

        editProcurar = (EditText) findViewById(R.id.campoPesquisaProduto);

        editProcurar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editProcurar.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_editar_lista_passo1, menu);
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

    //Métodos dos botões
    public void adicionarMaisProdutos(View view){

    }

    public void finalizarEdicao(View view){

    }

}
