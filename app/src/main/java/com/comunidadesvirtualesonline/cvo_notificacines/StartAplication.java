package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RegionIterator;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.comunidadesvirtualesonline.cvo_notificacines.gcmquickstart.QuickstartPreferences;


public class StartAplication extends AppCompatActivity {

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String MSG = "MENSAJE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_aplication);

        Intent intent = new Intent(this, GCMIntentService.class);
        startService(intent);

/*
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(MSG,"ENTRO AL METODO ON RECEIVE");
                SharedPreferences prefs =
                        context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String estado = prefs.getString("estado", "por_defecto");

                Log.i(MSG,"ESTADO"+estado);

            }
        };
    }
    */

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(MSG,"ENTRE AL  onReceive");
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);

                boolean sentToken = sharedPreferences
                        .getBoolean(StartPreferences.REGISTRATION_TOKEN_COMPLETE, false);

                if (sentToken) {
                     Log.i(MSG,"ENTRE ALA CONDICION DEL onReceive");

                     Intent in = new Intent(StartAplication.this, Notificaciones.class);
                     startActivity(in);

                } else {
                    Log.i(MSG,"ENTRE ALA CONDICION DEL onReceive false");

                }
            }
        };


    }





    }


