package com.ulatina.debateslatinos.debateslatinos;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by Federico Melendez on 11/10/2016.
 */

public class Chronometer implements Runnable {

    public static final long MILLIS_TO_MINUTES = 60000;
    public static final long MILLIS_TO_HOURS = 3600000;

    private Context mainContext;
    private long startTime;

    private  boolean isRunning;

    public Chronometer(Context mainContext) {
        this.mainContext = mainContext;
    }

    public void start(){

        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop(){

        isRunning = false;
    }

    @Override
    public void run() {

       while(isRunning){

           long since = System.currentTimeMillis() - startTime;

           int second =(int) ((since / 1000) %60);
           int minutes = (int)((since / MILLIS_TO_MINUTES)% 60);
           int hours = (int) ((since / MILLIS_TO_HOURS)% 24);
           int millis = (int) since % 1000;

           try {
               ((MainActivity) mainContext).updateTimerText(String.format(
                       "%02d:%02d:%02d:%03d", hours,minutes,second,millis
                       ));
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
    }
}
