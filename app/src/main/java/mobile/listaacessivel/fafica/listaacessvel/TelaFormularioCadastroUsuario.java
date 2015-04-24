package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import mobile.listaacessivel.fafica.listaacessvel.util.Mask;


public class TelaFormularioCadastroUsuario extends ActionBarActivity {

    EditText editEmail, editSenha, editNomeCompleto, editAnoNascimento, editCpf, editTelefone1,
            editTelefone2, editCep, editCidade, editEstado, editBairro, editRua, editNumero,
            editComplemento, editReferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_formulario_cadastro_usuario);
        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
//        getMenuInflater().inflate(R.menu.menu_tela_formulario_cadastro_usuario, menu);
//        return true;
//    }

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

    //Métodos dos Botoẽs da tela
    public void cadastrarUsuario(View view){
        getMessage("Deseja realmente criar o seu cadastro?");
    }

    //Método de mensagem
    public AlertDialog alerta;

    public void getMessage(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //builder.setTitle(titulo);
        builder.setMessage(mensagem);
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(TelaFormularioCadastroUsuario.this,TelaLogin.class);
                startActivity(it);
                finish();
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
