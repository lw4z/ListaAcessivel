package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.TextView;


public class ItemProdutoUnicoPasso3 extends ActionBarActivity {

   TextView txtNome;
    TextView txtMarca;
    TextView txtValor;
    NumberPicker campoQuantidade;
    String nome_produto;
    String marca;
    String valor;
    int quantidade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_produto_unico_passo3);
        // Get the intent from ListViewAdapter
        Intent i = getIntent();
        // Get the results of rank
        nome_produto = i.getStringExtra("nome_produto");
        // Get the results of country
        marca = i.getStringExtra("marca_produto");
        // Get the results of population
        valor = i.getStringExtra("valor_produto");
        // Get the results of flag
        quantidade = Integer.parseInt(i.getStringExtra("quantidade"));

        // Locate the TextViews in singleitemview.xml
        txtNome = (TextView) findViewById(R.id.textNomeProduto);
        txtMarca = (TextView) findViewById(R.id.textMarcaProduto);
        txtValor = (TextView) findViewById(R.id.textValorProduto);
        campoQuantidade = (NumberPicker)  findViewById(R.id.campoQuantidadeProduto);

        // Load the results into the TextViews
        txtNome.setText(nome_produto);
        txtMarca.setText(marca);
        txtValor.setText(valor);
        campoQuantidade.setValue(quantidade);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_item_produto_unico_passo3, menu);
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
