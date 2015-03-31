package mobile.listaacessivel.fafica.listaacessvel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class TelaFormularioCadastroUsuario extends ActionBarActivity {

    EditText editEmail, editSenha, editNomeCompleto, editAnoNascimento, editCpf, editTelefone1,
            editTelefone2, editCep, editCidade, editEstado, editBairro, editRua, editNumero,
            editComplemento, editReferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_formulario_cadastro_usuario);
//        //Botão de Voltar na actionBar
//        try{
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_formulario_cadastro_usuario, menu);
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

    //Métodos dos Botoẽs da tela
    public void cadastrarUsuario(View view){

    }
}
