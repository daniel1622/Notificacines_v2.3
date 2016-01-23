package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;

import com.comunidadesvirtualesonline.cvo_notificacines.gcmquickstart.QuickstartPreferences;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


/**
 * Created by JUANDAVID on 19/01/16.
 */
public class GCMIntentService extends IntentService{

    private boolean Registro2 = false;
    private boolean Registro1 = true;
    private boolean Registro;

    private String MSG = "MENSAJE";


    private String estado1= "1";
    private String estado2= "2";

    private String Token;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = "RegIntentService";
    private View view;

    StartAplication startAplication = new StartAplication();

    public GCMIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Log.i("SENDER ID=".toString(), getString(R.string.gcm_defaultSenderId));
            Log.i("TOKEN=".toString(), "" + token);
            Registro = true;
            this.Token = token;


        if (!token.equals("")) {

            Log.i(MSG,"ESTOY EN EL IF CON VERDADERO");

            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("estado", estado1.toString());
            editor.commit();

        }else{
            sharedPreferences.edit().putBoolean(StartPreferences.REGISTRATION_TOKEN_COMPLETE, false).apply();

            Log.i(MSG, "ESTOY EN EL IF CON FALSE");
        }


        } catch (IOException e) {
            Log.d("ERROR".toString(), "??" + e);
            e.printStackTrace();


        }
    }

    public boolean isRegistro() {
        return Registro;
    }

    public String getToken() {
        return Token;
    }



}
