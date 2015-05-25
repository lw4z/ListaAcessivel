package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Usuario;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;


public class TelaPerfilUsuario extends ActionBarActivity {

    private TextView resultEmail, resultNomeCompleto, resultAnoNascimento, resultCpf, resultTelefone1,
            resultTelefone2, resultCep, resultCidade, resultEstado, resultBairro, resultRua, resultNumero,
            resultComplemento, resultReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_usuario);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        getSupportActionBar().setTitle("Perfil");
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        inicializacaoObjetos();

        ClienteSession clienteSession = new ClienteSession();
        Cliente cliente = clienteSession.getCliente();

            resultEmail.setText(cliente.getEmail());
            resultNomeCompleto.setText(cliente.getNome());
            resultAnoNascimento.setText(cliente.getAno_nascimento());
            resultCpf.setText(cliente.getCpf());

            ArrayList<String> telefones = new ArrayList<String>();
            telefones.add(cliente.getTelefones().get(0));
            telefones.add(cliente.getTelefones().get(1));

            Endereco endereco = new Endereco(cliente.getEndereco().getRua(),
                    cliente.getEndereco().getBairro(),
                    cliente.getEndereco().getNumero(),
                    cliente.getEndereco().getComplemento(),
                    cliente.getEndereco().getReferencia(),
                    cliente.getEndereco().getCidade(),
                    cliente.getEndereco().getEstado(),
                    cliente.getEndereco().getCep());

            resultTelefone1.setText(telefones.get(0));
            resultTelefone2.setText(telefones.get(1));
            resultCep.setText(endereco.getCep());
            resultCidade.setText(endereco.getCidade());
            resultEstado.setText(endereco.getEstado());
            resultBairro.setText(endereco.getBairro());
            resultRua.setText(endereco.getRua());
            resultNumero.setText(endereco.getNumero());
            resultComplemento.setText(endereco.getComplemento());
            resultReferencia.setText(endereco.getReferencia());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_perfil_usuario, menu);
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

    //Inicialização dos objetos da tela
    public void inicializacaoObjetos(){
        resultEmail = (TextView) findViewById(R.id.resultEmail);
        resultNomeCompleto = (TextView) findViewById(R.id.resultNomeCompleto);
        resultAnoNascimento = (TextView) findViewById(R.id.resultAnoNascimento);
        resultCpf = (TextView) findViewById(R.id.resultCpf);
        resultTelefone1 = (TextView) findViewById(R.id.resultTelefone1);
        resultTelefone2 = (TextView) findViewById(R.id.resultTelefone2);
        resultCep = (TextView) findViewById(R.id.resultCep);
        resultCidade = (TextView) findViewById(R.id.resultCidade);
        resultEstado = (TextView) findViewById(R.id.resultEstado);
        resultBairro = (TextView) findViewById(R.id.resultBairro);
        resultRua = (TextView) findViewById(R.id.resultRua);
        resultNumero = (TextView) findViewById(R.id.resultNumero);
        resultComplemento = (TextView) findViewById(R.id.resultComplemento);
        resultReferencia = (TextView) findViewById(R.id.resultReferencia);
    }

    //Métodos dos Botoẽs da tela
    public void editarPerfil(View view){
        Intent it = new Intent(this,TelaEditarPerfil.class);
        startActivity(it);
    }
}
