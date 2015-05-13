package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.Mask;


public class TelaFormularioCadastroUsuario extends ActionBarActivity {

    private EditText editEmail, editSenha, editNomeCompleto, editAnoNascimento, editCpf, editTelefone1,
            editTelefone2, editCep, editCidade, editEstado, editBairro, editRua, editNumero,
            editComplemento, editReferencia;
    private Endereco endereco;
    private ArrayList<String> telefones = new ArrayList<String>();
    private String link;
    private String jsonCadastro = "";
    private Gson gson;

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

        inicializacaoObjetos();

        //Utilização de mascaras para os campos
        final EditText campo_ano_nascimento = (EditText) findViewById(R.id.editAnoNascimento);
        campo_ano_nascimento.addTextChangedListener(Mask.insert("####", campo_ano_nascimento));

        final EditText campo_cpf = (EditText) findViewById(R.id.editCpf);
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        final EditText campo_telefone1 = (EditText) findViewById(R.id.editTelefone1);
        campo_telefone1.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone1));

        final EditText campo_telefone2 = (EditText) findViewById(R.id.editTelefone2);
        campo_telefone2.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone2));

        final EditText campo_cep = (EditText) findViewById(R.id.editCep);
        campo_cep.addTextChangedListener(Mask.insert("#####-###", campo_cep));

        //final EditText campo_email = (EditText) findViewById(R.id.editEmail);
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

    //Inicialização de campos da tela
    public void inicializacaoObjetos(){
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editNomeCompleto = (EditText) findViewById(R.id.editNomeCompleto);
        editAnoNascimento = (EditText) findViewById(R.id.editAnoNascimento);
        editCpf = (EditText) findViewById(R.id.editCpf);
        editTelefone1 = (EditText) findViewById(R.id.editTelefone1);
        editTelefone2 = (EditText) findViewById(R.id.editTelefone2);
        editCep = (EditText) findViewById(R.id.editCep);
        editCidade = (EditText) findViewById(R.id.editCidade);
        editEstado = (EditText) findViewById(R.id.editEstado);
        editBairro = (EditText) findViewById(R.id.editBairro);
        editRua = (EditText) findViewById(R.id.editRua);
        editNumero = (EditText) findViewById(R.id.editNumero);
        editComplemento = (EditText) findViewById(R.id.editComplemento);
        editReferencia = (EditText) findViewById(R.id.editReferencia);

    }

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

                //Convertendo dados dos campos
                String nome = editNomeCompleto.getText().toString();
                String email = editEmail.getText().toString();
                String cpf = editCpf.getText().toString();
                String senha = editSenha.getText().toString();
                String anoNascimento = editAnoNascimento.getText().toString();
                String rua = editRua.getText().toString();
                String bairro = editBairro.getText().toString();
                String numero = editNumero.getText().toString();
                String complemento = editComplemento.getText().toString();
                String referencia = editReferencia.getText().toString();
                String cidade = editCidade.getText().toString();
                String estado = editEstado.getText().toString();
                String cep = editCep.getText().toString();
                String telefone1 = editTelefone1.getText().toString();
                String telefone2 = editTelefone2.getText().toString();

                String[] campos = new String[]
                        {nome,email,cpf,senha,anoNascimento,rua,bairro,numero,
                                complemento,referencia,cidade,estado,cep,telefone1,telefone2};

                validaCampos(campos);

                endereco = new Endereco(rua,bairro,numero,complemento,referencia,cidade,estado,cep);

                telefones.add(telefone1);
                telefones.add(telefone2);

                try {
                    Cliente cliente = new Cliente(nome,cpf,email,senha,anoNascimento,endereco,telefones);

                    gson = new Gson();
                    //System.out.println(gson.toJson(cliente));
                    jsonCadastro = gson.toJson(cliente);
                    Log.i("USUARIO",jsonCadastro);

                        if (!cliente.getNome().equals("")) {
                            link = "http://192.168.43.64:8080/ListaAcessivel/CadastrarClienteMobileServlet?json_cadastro=" + jsonCadastro;
                            ConnectionHttp conection = new ConnectionHttp(TelaFormularioCadastroUsuario.this);
                            conection.execute(link);
                            Log.i("CONECTION", conection.toString());
                            conection.get();
                            Log.i("RESULTADOCADASTRO",String.valueOf(conection.get()));
                        }else{
                            return;
                        }
                }catch (InterruptedException e1) {
                    e1.printStackTrace();
                }catch (ExecutionException e1) {
                    e1.printStackTrace();
                }
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

    public void validaCampos(String[] variavel){
        for(int i = 0; i < variavel.length; i++){
            if(variavel[i] == null || variavel[i].equals("")){
                Toast.makeText(this,"Existem campos vazios no formulário, favor preencher",Toast.LENGTH_LONG).show();
                return;
            }
        }

    }
}
