package mobile.listaacessivel.fafica.listaacessvel;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import mobile.listaacessivel.fafica.listaacessvel.util.Mask;


public class TelaEditarPerfil extends ActionBarActivity {

    EditText editEmail, editSenha, editNomeCompleto, editAnoNascimento, editCpf, editTelefone1,
            editTelefone2, editCep, editCidade, editEstado, editBairro, editRua, editNumero,
            editComplemento, editReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_perfil);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);

        //Utilização de mascaras para os campos
        final EditText campo_ano_nascimento = (EditText) findViewById(R.id.editAnoNascimento);
        campo_ano_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_ano_nascimento));

        final EditText campo_cpf = (EditText) findViewById(R.id.editCpf);
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        final EditText campo_telefone1 = (EditText) findViewById(R.id.editTelefone1);
        campo_telefone1.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone1));

        final EditText campo_telefone2 = (EditText) findViewById(R.id.editTelefone2);
        campo_telefone1.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone2));

        final EditText campo_cep = (EditText) findViewById(R.id.editCep);
        campo_cep.addTextChangedListener(Mask.insert("#####-###", campo_cep));

        final EditText campo_email = (EditText) findViewById(R.id.editEmail);

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_editar_perfil, menu);
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

    //Métodos dos boõtes
    public void alterarPerfil(View view){
        Intent it = new Intent(this,TelaPerfilUsuario.class);
        startActivity(it);
    }
}
