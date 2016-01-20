package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.IntentService;
import android.content.Intent;
import android.view.View;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


/**
 * Created by JUANDAVID on 19/01/16.
 */
public class GCMIntentService extends IntentService{



    private boolean Registro;



    private String Token;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = "RegIntentService";
    private View view;

    public GCMIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            Registro = true;
            this.Token = token;

        } catch (IOException e) {
            e.printStackTrace();
            Registro = false;
        }
    }

    public boolean isRegistro() {
        return Registro;
    }

    public String getToken() {
        return Token;
    }

}
