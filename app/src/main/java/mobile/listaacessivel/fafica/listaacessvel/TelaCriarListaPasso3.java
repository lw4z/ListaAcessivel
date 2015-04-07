package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class TelaCriarListaPasso3 extends ActionBarActivity {

    ListView listaProdutos;
    MyArrayAdapterCriarListaPasso3 adapter;
    EditText editProcurar;
    ArrayList<ItemCriarListaPasso3> produtos = new ArrayList<ItemCriarListaPasso3>();
    String [] nome;
    String [] marca;
    String [] valor;
    Integer [] quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo3);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Teste de Busca da lista
        nome = new String[] {"Nescau","Refrigerante","Carne Bovina","Macarrão"};
        marca = new String[] {"Nestle","Jatobá","Friboi","Vitarella"};
        valor = new String[] {"Valor: " + "R$ 3,80","Valor: " + "R$ 2,70","Valor: " + "R$ 13,80","Valor: " + "R$ 1,80"};
        quantidade = new Integer[] {3,8,5,12};

        listaProdutos = (ListView) findViewById(R.id.listViewProdutos);

        for (int i = 0; i < nome.length; i++){
            ItemCriarListaPasso3  p = new ItemCriarListaPasso3(nome[i], marca[i],
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
//        getMenuInflater().inflate(R.menu.menu_tela_criar_lista_passo3, menu);
//        return true;
//    }
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
//
    //Método dos Botões
    public void finalizarLista(View view){

    }
}
