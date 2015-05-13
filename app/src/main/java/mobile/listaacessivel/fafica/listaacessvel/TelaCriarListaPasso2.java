package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterCriarListaPasso2;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListEstabelecimentosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaCriarListaPasso2 extends ActionBarActivity {

    ListView listaEstabelecimentos;
    ArrayList<Estabelecimento> items = new ArrayList<Estabelecimento>();
    private ArrayList<String> telefones;
    private Endereco endereco;
    private String link;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_lista_passo2);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        criarDados();

        try {
            final MyArrayAdapterCriarListaPasso2 adapter = new MyArrayAdapterCriarListaPasso2(this,items);

            // 2. Recupera o ListView para o activity_main.xml
            listaEstabelecimentos = (ListView) findViewById(R.id.listViewEstabelecimentos);

            // 3. setListAdapter
            listaEstabelecimentos.setAdapter(adapter);

            //Envio do estabelecimento para a próxima tela
            listaEstabelecimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Bundle dados = new Bundle();
                    Intent intent = new Intent(view.getContext(), TelaCriarListaPasso3.class);
                    int id_estabelecimento = items.get(position).getId_estabelecimento();
                    //link = "http://192.168.0.105:8080/ListaAcessivel/CriarListaPasso2MobileServlet?id_estabelecimento=" + id_estabelecimento;
                    link = "http://192.168.43.64:8080/ListaAcessivel/CriarListaPasso2MobileServlet?id_estabelecimento=" + id_estabelecimento;

                    ConnectionHttp conection = new ConnectionHttp(TelaCriarListaPasso2.this);
                    conection.execute(link);

                    Log.i("CONECTION",conection.toString());

                    try {
                        String json = conection.get();
                        Log.i("RESULTADOJSON",json.toString());
                        ArrayListProdutosSession listaProdutos = new ArrayListProdutosSession(converteArray(json));

                    }catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e1) {
                        e1.printStackTrace();
                    }
//                    intent.putExtra("nome_estabelecimento",(items.get(position).getNome_fantasia()));
//                    intent.putExtra("id_estabelecimento",(items.get(position).getId_estabelecimento()));
//                    Log.i("ESTABELECIMENTO: ",items.get(position).getNome_fantasia());
                    startActivity(intent);
                }
            });

        }catch (Exception e){

        }
    }

    //Método que recebe os dados para a lista
    private ArrayList<Estabelecimento> criarDados(){

//        String json = getIntent().getStringExtra("listaEstabelecimentos");
//
//        gson = new Gson();
//
//        if(json != null) {
//            Estabelecimento[] estabelecimentosArray = gson.fromJson(json, Estabelecimento[].class);
//
//            for (Estabelecimento p : estabelecimentosArray) {
//                items.add(p);
//            }
//            Estabelecimento e = items.get(0);
//            Log.e("Metodo TesteGson", e.getNome_fantasia() + ", " + e.getCategoria());
//        }

        ArrayListEstabelecimentosSession estabelecimentosSession = new ArrayListEstabelecimentosSession();
        items = estabelecimentosSession.getListaEstabelecimentos();


//        endereco = new Endereco("Rua 1","Centro","12","Prédio","Proximo ao centro","Caruaru","PE","5555555555");
//
//        items.add(new Estabelecimento("Bompreço","Bompreço","bompreco@email.com","Supermercado","11111111111",endereco, telefones));
//        items.add(new Estabelecimento("Bompreço","Bompreço","bompreco@email.com","Supermercado","11111111111",endereco, telefones));
//        items.add(new Estabelecimento("Bompreço","Bompreço","bompreco@email.com","Supermercado","11111111111",endereco, telefones));
//        items.add(new Estabelecimento("Bompreço","Bompreço","bompreco@email.com","Supermercado","11111111111",endereco, telefones));
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

    public ArrayList<Produto> converteArray(String json){

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Gson gson = new Gson();

        Produto[] produtosArray = gson.fromJson(json,Produto[].class);
        if(produtosArray != null){
            for(Produto p : produtosArray){
                produtos.add(p);
            }
        }else{
            Toast.makeText(this, "Não foram encontrados produtos", Toast.LENGTH_LONG).show();
        }
        return produtos;
    }
}
