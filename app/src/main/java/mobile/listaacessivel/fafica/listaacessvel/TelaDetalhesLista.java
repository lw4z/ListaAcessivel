package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView quantidadeTotal, valorTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhes_lista);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Campos de Valores totais das listas
        quantidadeTotal = (TextView) findViewById(R.id.txtQuantidadeTotalProdutos);
        valorTotal = (TextView) findViewById(R.id.txtValorTotalLista);

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

    //Métodos dos botões
    public void imprimirLista(View view){

    }

    public void editarLista(View view){
        Intent it = new Intent(this,TelaEditarListaPasso1.class);
        startActivity(it);
    }

    public void solicitarEntrega(View view){
        getMessage("Alerta!","Deseja realmente solicitar a entrega da lista?");
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
                Intent it = new Intent(TelaDetalhesLista.this,TelaMinhasListas.class);
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
