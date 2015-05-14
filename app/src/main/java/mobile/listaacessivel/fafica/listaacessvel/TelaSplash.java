package mobile.listaacessivel.fafica.listaacessvel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class TelaSplash extends Activity {

    // Set Duration of the Splash Screen
    long Delay = 1000;
    Handler messageHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_splash);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //A janela da aplicação deverá ficar apenas no formato vertical
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Get the view from splash_screen.xml
        setContentView(R.layout.activity_tela_splash);

        // Create a Timer
        Timer RunSplash = new Timer();

        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                // Close SplashScreenActivity.class
                finish();

                // Start MainActivity.class
                Intent myIntent = new Intent(TelaSplash.this,TelaLogin.class);

                if(isOnline(TelaSplash.this)) {
                    startActivity(myIntent);
                }else{
                    displayError("A conexão com a internet não foi encontrada!");
                    finish();
                }
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);

    }

    public static boolean isOnline(Context contexto) {
        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);// Pego a conectividade do contexto
        NetworkInfo netInfo = cm.getActiveNetworkInfo();// Crio o objeto netInfo que recebe as informacoes da Network
        if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())) { // Se nao tem conectividade retorna false
            return true;
        }
        return false;
    }

    public void displayError(final String errorText) {
        Runnable doDisplayError = new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_LONG).show();
            }
        };
        messageHandler.post(doDisplayError);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tela_splash, menu);
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
}
