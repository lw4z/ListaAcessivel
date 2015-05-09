package mobile.listaacessivel.fafica.listaacessvel.util;

/**
 * Created by ivan on 08/05/15.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHttp extends AsyncTask<String, String, String> {
    private Context context;
    private ProgressDialog progressDialog;

    public ConnectionHttp(Context contexto) {
        this.context = contexto;
    }

    // Aqui ainda está na Thread principal
    @Override
    protected void onPreExecute() {
        this.progressDialog = ProgressDialog.show(context, "Aguarde...",
                "Buscando Informações");
        super.onPreExecute();
    }

    // Começa a nova Thread e realiza a tarefa;
    @Override
    protected String doInBackground(String... params) {
        String linha2 = "";
        try {
            String link = params[0];

            URL url = new URL(link);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            // se true indica que enviaremos dados no corpo da requisição
            // (padrão é
            // false)
            connection.setDoOutput(false);
            // se true indica que leremos os dados da resposta (padrão é true)
            connection.setDoInput(true);
            // default é GET
            connection.setRequestMethod("GET");
            // verifica o código da reposta
            connection.connect();

            linha2 = "";


            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader arquivo = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(),
                                //"iso-8859-1"));
                                "utf8"));
                String linha = "";

                while ((linha = arquivo.readLine()) != null) {
                    linha2 += linha;
                    publishProgress(linha);
                }
                arquivo.close();
            }
        } catch (IOException e) {
            publishProgress("Problema ao tentar se conectar...");
            e.printStackTrace();
        }
        return linha2;
    }

    // Termina e Thread e pega seu retorno
    @Override
    protected void onPostExecute(String result) {
        progressDialog.setMessage("Finalizado!");
        progressDialog.dismiss();
        super.onPostExecute(result);
    }

}