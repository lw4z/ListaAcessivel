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

import java.util.concurrent.ExecutionException;

import mobile.listaacessivel.fafica.listaacessvel.entidades.Lista;
import mobile.listaacessivel.fafica.listaacessvel.util.ConnectionHttp;
import mobile.listaacessivel.fafica.listaacessvel.util.ListaSession;
import mobile.listaacessivel.fafica.listaacessvel.util.IpConection;


public class TelaEdicaoSenha extends ActionBarActivity {

    EditText email_usuario;
    private String ip = IpConection.IP.toString();
    private Gson gson;
    private String email, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edicao_senha);

        //Botão da logo na ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_logo_listaacessivel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeActionContentDescription(R.string.bt_voltar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Recuperar Senha");
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Inicialização de campos da tela
        email_usuario = (EditText) findViewById(R.id.campoEmailRecuperacaoSenha);
    }

    //Métodos dos botões
    public void recuperarSenha(View view){
        getMessage("Deseja enviar seu e-mail para solicitar a recuperação de sua senha?");
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
                recupecar();
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

    //Método de conexão para recuperação de senha
    public void recupecar(){

        Intent it = new Intent(TelaEdicaoSenha.this,TelaLogin.class);

        gson = new Gson();
        email = email_usuario.getText().toString();
        Log.i("EMAIL", email);

        try{
            if(email != null && !email.equals("")) {
                link = "http://" + ip + ":8080/ListaAcessivel/RecuperarSenhaPasso1MobileServlet?email=" + email;
                Log.i("LINK", link);

                ConnectionHttp conection = new ConnectionHttp(TelaEdicaoSenha.this);
                conection.execute(link);
                Log.i("CONECTION", conection.toString());

                String json = conection.get();
                Log.i("RESULTADOJSON", json);

                if(json.contains("sucesso")){
                    Toast.makeText(this,"E-mail enviado com sucesso, verifique sua caixa de e-mail para a recuperação de sua senha!", Toast.LENGTH_LONG).show();
                    startActivity(it);
                    finish();
                }else{
                    Toast.makeText(this,"Ocorreu um erro ao enviar seu e-mail, tente novamente!", Toast.LENGTH_LONG).show();
                    return;
                }
            }else{
                Toast.makeText(this,"Digite um email válido!",Toast.LENGTH_LONG).show();
            }
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        }catch (ExecutionException e1) {
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
