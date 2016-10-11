package com.ulatina.debateslatinos.debateslatinos;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity {

    private TextView tvTime;
    private Button btnStart;
    private Button btnStop;

    private Context context;
    private Chronometer chronometer;
    private Thread threadChrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        tvTime =(TextView) findViewById(R.id.tvTime);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chronometer == null){

                    chronometer = new Chronometer(context);
                    threadChrono = new Thread(chronometer);
                    threadChrono.start();
                    chronometer.start();

                }
                //btn has been pressed
                //tvTime.setText("Boton Start");
            }
        });
        //if(AccessToken.getCurrentAccessToken() == null){
       //     goMainScreen();}
          btnStop.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                 if(chronometer != null){

                     chronometer.stop();
                     threadChrono.interrupt();
                     threadChrono = null;

                     chronometer = null;
        }
    }
});
    }

    public void updateTimerText(final String time) throws InterruptedException {
        Thread.sleep(10);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTime.setText(time);
            }
        });
    }

    private void goMainScreen(){
        Intent intent = new Intent(this, InicioFb.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goMainScreen();
    }
}
