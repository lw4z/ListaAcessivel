package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;


public class ItemProdutoUnicoPasso3 extends ActionBarActivity {

    TextView txtNome;
    TextView txtMarca;
    TextView txtValor;
    EditText campoQuantidade;
    String nome_produto;
    String marca;
    String valor;
    int quantidade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_produto_unico_passo3);

        Intent i = getIntent();

        nome_produto = i.getStringExtra("nome_produto");
        marca = i.getStringExtra("marca_produto");
        valor = i.getStringExtra("valor_produto");
        quantidade = Integer.parseInt(i.getStringExtra("quantidade"));

        // Localizando os TextViews
        txtNome = (TextView) findViewById(R.id.textNomeProduto);
        txtMarca = (TextView) findViewById(R.id.textMarcaProduto);
        txtValor = (TextView) findViewById(R.id.textValorProduto);
        campoQuantidade = (EditText)  findViewById(R.id.campoQuantidadeProduto);

        //Ajustando acessibilidade no editText de quantidade
        campoQuantidade.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);

        // Carregando os resultados nos textViews
        txtNome.setText(nome_produto);
        txtMarca.setText(marca);
        txtValor.setText(valor);
        campoQuantidade.setText(quantidade);

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
