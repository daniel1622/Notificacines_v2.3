package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.adapters.EstudianteAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.fragments.NotificacionesFragment;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;

/**
 * Created by Guardian on 19/01/2016.
 */
public class ConsumosServer extends IntentService {


    public String mensaje = "MSG";
    private static final String TAG = "RegIntentService";

    public ConsumosServer() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String  token = prefs.getString("TOKEN", "id del telefono ");
        String  u = prefs.getString("u", "usuario ");
        String  p = prefs.getString("p", "contraseña del usuario ");
        String  id_db = prefs.getString("id_db", "id del telefono ");

        Log.i(mensaje,"Usuario Y Contraseña ="+u+"---"+p);
        Log.i(mensaje,"TOKEN Y NomDB ="+token+"---"+id_db);

        try {
            //CONSUMO AL SERVIDOR WEB...
            URL url = new URL("http://www.comunidadesvirtualesonline.com/gcmphp/gcmphp-registration.php?token="+token+"&id_db="+id_db+"&u="+u+"&p="+p);
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
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                OutputStream out = new BufferedOutputStream(con.getOutputStream());

                out.write(data.getBytes());
                //  out.write(data1.getBytes());

                out.flush();
                out.close();


            } catch (IOException e) {
                Log.i(mensaje, "ERROR DEL ENVIO = " + e);
                e.printStackTrace();
            } finally {
                if (con != null)
                    Log.i(mensaje, "SE DESCONECTO");
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

                Log.i(mensaje, "JSON ! = =" + responsJson);

                String estado_registration = responsJson.getString("result");
                Log.i(mensaje, " estado_registration consumo = " + responsJson.getString("result"));

                SharedPreferences pref = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("estado_registration",estado_registration);
                editor.commit();


            }catch (Exception e){
                Log.i(mensaje, "ERROR AL RECIBIR EL JSON = " + e);
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


        }catch(Exception e){
            Log.d(mensaje, "ERROR DEL CONSUMO", e);
        }
    }
}



