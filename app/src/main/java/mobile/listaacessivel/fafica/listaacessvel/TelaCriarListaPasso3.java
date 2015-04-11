package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
        setContentView(R.layout.activity_tela_criar_lista_passo3);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        getMessage("Finalizar a Lista!","Deseja realmente finalizar a criação da lista?");
    }

    //Método de mensagem
    public AlertDialog alerta;

    public void getMessage(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaCriarListaPasso3.this,TelaMinhasListas.class);
                startActivity(it);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                return;
            }
        });
        //cria o AlertDialog e exibe na tela
        alerta = builder.create();
        alerta.show();
    }
}
