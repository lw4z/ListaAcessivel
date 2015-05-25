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
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.adapters.MyArrayAdapterMinhasLista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Estabelecimento;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Produto;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.IpConection;


public class TelaMinhasListas extends ActionBarActivity {

    ListView listaListas;
    ArrayList<Lista> listas = null;
    private ArrayList<Produto> produtos;
    private Cliente cliente;
    private Estabelecimento estabelecimento;
    private String link;
    private Gson gson;
    private String ip = IpConection.IP.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_minhas_listas);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ClienteSession clienteSession = new ClienteSession();
        cliente = clienteSession.getCliente();

        MyArrayAdapterMinhasLista adapter = null;

        try {
            if(criarDados() != null) {
                adapter = new MyArrayAdapterMinhasLista(this, criarDados());
            }else{
                Toast.makeText(this, "Não foi encontrada nenhuma lista, retorne para a área do usuário e crie uma lista!", Toast.LENGTH_LONG).show();
            }

            // 2. Recupera o ListView para o activity_main.xml
            listaListas = (ListView) findViewById(R.id.listDetalhesLista);

            // 3. setListAdapter
            listaListas.setAdapter(adapter);

            //Envio da lista para a próxima tela
            listaListas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(view.getContext(), TelaDetalhesLista.class);

                    ListaSession lista = new ListaSession(listas.get(position));

                    startActivity(intent);
                }
            });
        }catch (Exception e){

        }
    }

    //Método que recebe os dados para a lista
    private ArrayList<Lista> criarDados(){
    Lista[] listasArray;
    String json;

        Log.i("IDCLIENTE", String.valueOf(cliente.getId_usuario()));
    try{
        link = "http://"+ ip +":8080/ListaAcessivel/MinhasListasMobileServlet?id_cliente=" + cliente.getId_usuario();
        ConnectionHttp conection = new ConnectionHttp(TelaMinhasListas.this);
        conection.execute(link);
        json = conection.get();

        listas = new ArrayList<Lista>();

        if(!json.contains("vazio")){
            gson = new Gson();
            listasArray = gson.fromJson(json,Lista[].class);

            for(int i = 0; i < listasArray.length;i ++){
                listas.add(listasArray[i]);
                Log.i("TAMANHOLISTAARRAY", String.valueOf(listasArray.length));
            }
        }else{
            Toast.makeText(this, "Não foi encontrada nenhuma lista, retorne para a área do usuário e crie uma lista!", Toast.LENGTH_LONG).show();
        }

        Log.i("TAMANHOLISTA", String.valueOf(listas.size()));
        Log.i("JSON", json);


    }catch (InterruptedException e1) {
        e1.printStackTrace();
    }catch (ExecutionException e1) {
        e1.printStackTrace();
    }
        return listas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_minhas_listas, menu);
        return true;
    }

}
