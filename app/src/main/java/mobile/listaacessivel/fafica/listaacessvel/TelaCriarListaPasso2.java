package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterCriarListaPasso2;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListEstabelecimentosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ArrayListProdutosSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.EstabelecimentoSession;
import mobile.listaacessivel.fafica.listaacessvel.util.IpConection;


public class TelaCriarListaPasso2 extends ActionBarActivity {

    ListView listaEstabelecimentos;
    ArrayList<Estabelecimento> items = new ArrayList<Estabelecimento>();
    ArrayList<Estabelecimento> estabelecimentosFiltrados = new ArrayList<Estabelecimento>();
    private ArrayList<String> telefones;
    private Endereco endereco;
    private String link;
    private Gson gson;
    private String ip = IpConection.IP.toString();
    private int state = 0;

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

        //criarDados();

        final ArrayListEstabelecimentosSession estabelecimentosSession = new ArrayListEstabelecimentosSession();
        //final ArrayList<Estabelecimento> filtro = new ArrayList<Estabelecimento>();
        items = estabelecimentosSession.getListaEstabelecimentos();

        if(items.size() == 0){
            Toast.makeText(TelaCriarListaPasso2.this, "Nenhum estabelecimento encontrado em sua Cidade!", Toast.LENGTH_LONG).show();
        }

        Log.i("TAMANHOITENS",String.valueOf(items.size()));

        ClienteSession clienteSession = new ClienteSession();
        final Cliente cliente = clienteSession.getCliente();

        for(int i = 0; i < items.size();i++) {
            estabelecimentosFiltrados.add(items.get(i));
        }

        ToggleButton toggle = (ToggleButton) findViewById(R.id.btFiltrarPorBairro);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    estabelecimentosFiltrados.clear();
                    for (int i = 0; i < items.size(); i++) {
                        if (cliente.getEndereco().getBairro().equals(items.get(i).getEndereco().getBairro())) {
                            estabelecimentosFiltrados.add(items.get(i));

                        }
                    }
                    if (estabelecimentosFiltrados.size() == 0) {
                        Toast.makeText(TelaCriarListaPasso2.this, "Nenhum estabelecimento encontrado no seu Bairro!", Toast.LENGTH_LONG).show();
                    }
                    Log.i("TAMANHOFILTRADOS", String.valueOf(estabelecimentosFiltrados.size()));
                } else {
                    estabelecimentosFiltrados.clear();
                    for (int i = 0; i < items.size(); i++) {
                        estabelecimentosFiltrados.add(items.get(i));
                    }
                }
            }
        });



        if(estabelecimentosFiltrados.size() == 0){
            Toast.makeText(TelaCriarListaPasso2.this, "Nenhum estabelecimento encontrado!", Toast.LENGTH_LONG).show();
        }

        try {
            final MyArrayAdapterCriarListaPasso2 adapter = new MyArrayAdapterCriarListaPasso2(this,estabelecimentosFiltrados);

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
                    int id_estabelecimento = estabelecimentosFiltrados.get(position).getId_estabelecimento();
                    link = "http://" + ip + ":8080/ListaAcessivel/CriarListaPasso2MobileServlet?id_estabelecimento=" + id_estabelecimento;

                    EstabelecimentoSession estabelecimentoSession = new EstabelecimentoSession(estabelecimentosFiltrados.get(position));

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
                    startActivity(intent);
                }
            });

        }catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_criar_lista_passo2, menu);
        return true;
    }

    //Método para sessão
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
