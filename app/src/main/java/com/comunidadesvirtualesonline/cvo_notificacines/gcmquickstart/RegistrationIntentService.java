/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.comunidadesvirtualesonline.cvo_notificacines.Main;
import com.comunidadesvirtualesonline.cvo_notificacines.R;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.comunidadesvirtualesonline.cvo_notificacines.gcmquickstart.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.comunidadesvirtualesonline.cvo_notificacines.models.RegObject;

import cz.msebera.android.httpclient.Header;


public class RegistrationIntentService extends IntentService {


    public String mensaje =">-------<";


    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            // [END get_token]
            Log.i(TAG, "GCM Registration Token: " + token);


            // este codigo permite almacenar en un xml el token del telefono para luego ser llamado en cualquier clase
            // que sea necesario su uso.
            SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("TOKEN", token);
            editor.commit();


            //"http://www.comunidadesvirtualesonline.com/gcmphp/gcmphp-validation-token.php"
            //"http://www.comunidadesvirtualesonline.com/notifications/singup.php?token=
            //"http://www.comunidadesvirtualesonline.com/gcmphp/gcmphp-validation-token.php?token="+tokenn+"&usuario="+usuario

            //CONSUMO AL SERVIDOR WEB...
            URL url = new URL("http://www.comunidadesvirtualesonline.com/gcmphp/gcmphp-validation-token.php?token="+token);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //Envio de token a la base de datos alojada en el servidor web

            HttpURLConnection con = null;
            try {


                // Construir los datos a enviar
                String data = "body=" + URLEncoder.encode(token,"UTF-8");

                Log.i(mensaje,"DATOS ="+data);

                con = (HttpURLConnection)url.openConnection();

                // Activar método POST
                con.setDoOutput(true);

                // Tamaño previamente conocido
                con.setFixedLengthStreamingMode(data.getBytes().length);

                // Establecer application/x-www-form-urlencoded debido a la simplicidad de los datos
                con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

                OutputStream out = new BufferedOutputStream(con.getOutputStream());

                out.write(data.getBytes());
              //  out.write(data1.getBytes());

                out.flush();
                out.close();

            } catch (IOException e) {
                Log.i(mensaje,"ERROR !!!!! = "+e);
                e.printStackTrace();
            } finally {
                if(con!=null)
                    Log.i(mensaje,"SE DESCONECTO");
                    con.disconnect();
            }

            //en estas lineas de codigo lo que se espera es obtener datos por medio de un JSON
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                JSONObject responsJson =  new JSONObject(responseStrBuilder.toString());

                Log.i(mensaje,"JSON ! = ="+responsJson);

                // se obtiene el estado que se encuentra en el Json del consumo. 1 es que el token ya existe en la bd.
                // 2 es que el token no exite en la bd.
                String ESTADO = responsJson.getString("result");
                Log.i(mensaje,"RESULT = "+responsJson.getString("result"));

                //se guarda el estado del Json en un SharePrefence para luego se llamado en el Main de google
                editor.putString("estado_token", ESTADO);
                editor.commit();


            }catch (Exception e){
                Log.i(mensaje,"ERROR JSON ! = ="+e);
            }
            finally{
                urlConnection.disconnect();
            }


            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);


            // Subscribe to topic channels
            subscribeTopics(token);




            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on a third-party server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to third-party servers.
     *
     * Modify this method to associate the user's GCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }

    /**
     * Subscribe to any GCM topics of interest, as defined by the TOPICS constant.
     *
     * @param token GCM token
     * @throws IOException if unable to reach the GCM PubSub service
     */
    // [START subscribe_topics]
    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
    // [END subscribe_topics]



}
