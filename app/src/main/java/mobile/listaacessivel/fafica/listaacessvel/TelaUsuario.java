package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;


public class TelaUsuario extends ActionBarActivity {

    private String link;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        String usuario = getIntent().getStringExtra("usuario");
//
//        ConnectionHttp conexao = new ConnectionHttp(TelaUsuario.this);
//
//        String resultado = String.valueOf(conexao.getStatus());
//        Log.i("STATUS CONEXAO", resultado);
//
//        if(conexao.getStatus() != AsyncTask.Status.FINISHED) {
//            if (conexao.getStatus() == AsyncTask.Status.PENDING) {
//                gson = new Gson();
//                Cliente clienteJson = gson.fromJson(usuario, Cliente.class);
//                if (clienteJson.getNome() != null) {
//                    ArrayList<String> telefones = new ArrayList<String>();
//                    telefones.add(clienteJson.getTelefones().get(0));
//                    telefones.add(clienteJson.getTelefones().get(1));
//
//                    Endereco endereco = new Endereco(clienteJson.getEndereco().getRua(),
//                            clienteJson.getEndereco().getBairro(),
//                            clienteJson.getEndereco().getNumero(),
//                            clienteJson.getEndereco().getComplemento(),
//                            clienteJson.getEndereco().getReferencia(),
//                            clienteJson.getEndereco().getCidade(),
//                            clienteJson.getEndereco().getEstado(),
//                            clienteJson.getEndereco().getCep());
//
//                    Cliente cliente = new Cliente(clienteJson.getId_usuario(),
//                            clienteJson.getNome(),
//                            clienteJson.getCpf(),
//                            clienteJson.getEmail(),
//                            clienteJson.getSenha(),
//                            clienteJson.getAno_nascimento(),
//                            endereco, telefones);
//                } else {
//                    Intent login = new Intent(this, TelaLogin.class);
//                    startActivity(login);
//                    finish();
//                }
//            } else {
//                Intent login = new Intent(this, TelaLogin.class);
//                startActivity(login);
//                finish();
//            }
//        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent sobre = new Intent(this,TelaSobre.class);
            startActivity(sobre);
        }else if(id == R.id.action_logout){
            Intent login = new Intent(this,TelaLogin.class);
            ConnectionHttp conexao = new ConnectionHttp(TelaUsuario.this);
            conexao.cancel(true);
            startActivity(login);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Métodos dos Botoẽs da tela
    public void getPerfil(View view){
        Intent perfil = new Intent(this,TelaPerfilUsuario.class);
        //String usuario = getIntent().getStringExtra("usuario");


        //perfil.putExtra("perfilUsuario",usuario);
        startActivity(perfil);
    }

    public void visualizarListas(View view){
        Intent listas = new Intent(this,TelaMinhasListas.class);
        //link = ;
        ConnectionHttp conection = new ConnectionHttp(TelaUsuario.this);
        conection.execute(link);

        Log.i("CONECTION", conection.toString());

        try {
            String json = conection.get();
            Log.i("RESULTADOJSON",json.toString());
            listas.putExtra("listaListas", json);

        }catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        startActivity(listas);
    }

    public void criarLista(View view){
        Intent it = new Intent(this,TelaCriarListaPasso1.class);
        startActivity(it);
    }
}

