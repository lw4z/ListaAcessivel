package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
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


public class TelaEditarListaPasso2 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    EditText editProcurar;
    ArrayList<ItemCriarListaPasso3> produtos = new ArrayList<ItemCriarListaPasso3>();
//    String [] nome;
//    String [] marca;
//    Double [] valor;
//    Integer [] quantidade;
    ArrayList<String> nome;
    ArrayList<Double> valor;
    ArrayList<String> marca;
    ArrayList<Integer> quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_lista_passo2);

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

        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);

        for (int i = 0; i < nome.size(); i++){
            ItemCriarListaPasso3  p = new ItemCriarListaPasso3(nome.get(i), marca.get(i),
                    valor.get(i), quantidade.get(i));
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
//        getMenuInflater().inflate(R.menu.menu_tela_editar_lista_passo2, menu);
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

    //Métodos dos Botões

    public void finalizarEdicao(View view){
        Intent it = new Intent(this,TelaUsuario.class);
        startActivity(it);
    }
}