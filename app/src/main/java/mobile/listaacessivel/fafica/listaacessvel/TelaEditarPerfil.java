package mobile.listaacessivel.fafica.listaacessvel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Cep;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Cliente;
import mobile.listaacessivel.fafica.listaacessvel.entidades.Endereco;
import mobile.listaacessivel.fafica.listaacessvel.util.ClienteSession;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.Mask;
import mobile.listaacessivel.fafica.listaacessvel.util.ipConection;


public class TelaEditarPerfil extends ActionBarActivity {

    EditText editEmail, editNomeCompleto, editAnoNascimento, editCpf, editTelefone1,
            editTelefone2, editCep, editCidade, editEstado, editBairro, editRua, editNumero,
            editComplemento, editReferencia;
    private int id_cliente;
    private String link, senha;
    private String ip = ipConection.IP.toString();
    private Gson gson;
    private String json_edicao;
    Endereco endereco;
    ArrayList<String> telefones = new ArrayList<String>();
    private Cep cepAutomatico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar_perfil);

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

        final EditText campo_estado = (EditText) findViewById(R.id.editEstado);
        campo_estado.addTextChangedListener(Mask.insert("##", campo_estado));

        ClienteSession clienteSession = new ClienteSession();
        Cliente cliente = clienteSession.getCliente();

        id_cliente = cliente.getId_usuario();
        senha = cliente.getSenha();

        editEmail.setText(cliente.getEmail());
        editNomeCompleto.setText(cliente.getNome());
        editAnoNascimento.setText(cliente.getAno_nascimento());
        editCpf.setText(cliente.getCpf());

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

        editTelefone1.setText(telefones.get(0));
        editTelefone2.setText(telefones.get(1));
        editCep.setText(endereco.getCep());
        editCidade.setText(endereco.getCidade());
        editEstado.setText(endereco.getEstado());
        editBairro.setText(endereco.getBairro());
        editRua.setText(endereco.getRua());
        editNumero.setText(endereco.getNumero());
        editComplemento.setText(endereco.getComplemento());
        editReferencia.setText(endereco.getReferencia());

        Button bt_cep = (Button) findViewById(R.id.bt_buscar_cep);

        bt_cep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cep cepPesquisa = buscarCep(campo_cep.getText().toString());
                if(cepPesquisa != null && cepPesquisa.getStatus() != 0){
                    editCidade.setText(cepPesquisa.getCity());
                    editEstado.setText(cepPesquisa.getState());
                    editBairro.setText(cepPesquisa.getDistrict());
                    editRua.setText(cepPesquisa.getAddress());
                }else{
                    Toast.makeText(TelaEditarPerfil.this,"O CEP não foi encontrado!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Inicialização de campos da tela
    public void inicializacaoObjetos(){
        editEmail = (EditText) findViewById(R.id.editEmail);
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

    //Métodos dos boõtes
    public void alterarPerfil(View view){
        getMessage("Deseja realmente salvar as modificações?");
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

                endereco = new Endereco(rua,bairro,numero,complemento,referencia,cidade,estado,cep);

                telefones.add(telefone1);
                telefones.add(telefone2);

                String[] campos = new String[]
                        {nome,email,cpf,anoNascimento,rua,bairro,numero,
                                complemento,referencia,cidade,estado,cep,telefone1,telefone2};

                validaCampos(campos);

                try {
                    Cliente cliente = new Cliente(nome,cpf,email,anoNascimento,endereco,telefones);
                    cliente.setId_usuario(id_cliente);
                    cliente.setSenha(senha);
                    ClienteSession clienteSession = new ClienteSession(cliente);

                    gson = new Gson();
                    json_edicao = gson.toJson(cliente);
                    json_edicao = json_edicao.replaceAll(" ","<;>");
                    Log.i("USUARIO",json_edicao);

                    if (!cliente.getNome().equals("")) {
                        link = "http://" + ip + ":8080/ListaAcessivel/EditarPerfilMobileServlet?json_edicao=" + URLEncoder.encode(json_edicao, "UTF-8");
                        ConnectionHttp conection = new ConnectionHttp(TelaEditarPerfil.this);
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
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }

                Intent it = new Intent(TelaEditarPerfil.this,TelaPerfilUsuario.class);
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
                Toast.makeText(this, "Existem campos vazios no formulário, favor preencher", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    public Cep buscarCep(String cep){

        try {
            gson = new Gson();

            if (!cep.equals("")) {
                link = "http://apps.widenet.com.br/busca-cep/api/cep/" + cep + ".json";
                ConnectionHttp conection = new ConnectionHttp(TelaEditarPerfil.this);
                conection.execute(link);

                Cep cepJson = new Cep();
                Log.i("CONECTION", conection.toString());
                String json = conection.get();

                Log.i("RESULTADOCEP",json);

                if(json.contains("status")) {
                    cepJson = gson.fromJson(json, Cep.class);
                }else{
                    Toast.makeText(this,"Ocorreu algum problema com a conexão!",Toast.LENGTH_LONG).show();
                }

                cepAutomatico = new Cep(cepJson.getStatus(),cepJson.getCode(),cepJson.getState(),cepJson.getCity(),cepJson.getDistrict(),cepJson.getAddress());

                Log.i("RESULTADOCADASTRO",String.valueOf(conection.get()));
                Log.i("CEP",String.valueOf(cepJson));
            }
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        }catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return cepAutomatico;
    }
}
