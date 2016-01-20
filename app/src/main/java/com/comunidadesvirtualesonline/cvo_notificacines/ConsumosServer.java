package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.adapters.EstudianteAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.fragments.NotificacionesFragment;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;

/**
 * Created by Guardian on 19/01/2016.
 */
public class ConsumosServer  extends AppCompatActivity {


    public void ConsumoCardView() {

        String msg = "AVISO:";
        try {
            URL url = new URL("http://www.comunidadesvirtualesonline.com/notifications/singup.php?token=");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            JSONObject responsJson = new JSONObject(responseStrBuilder.toString());

/*
            String JsonTitulo = responsJson.getString("titulo");
            String JsonNomestu = responsJson.getString("nombreestu");
            String JsonFecha = responsJson.getString("fecha");
*/

            String JsonTitulo = "HOLA .... !";
            String JsonNomestu = "daniel Pardo C.";
            String JsonFecha = "07/06/06";

            TextView CardViewTitulo = (TextView) findViewById(R.id.Titulo);
            TextView CarViewNombres = (TextView) findViewById(R.id.NombreEstudiante);
            TextView CardViewFecha = (TextView) findViewById(R.id.fechaNotificacion);

            CardViewTitulo.setText(JsonTitulo);
            CarViewNombres.setText(JsonNomestu);
            CardViewFecha.setText(JsonFecha);


        } catch (JSONException | IOException e) {
            e.printStackTrace();
            Log.i(msg, "ERROR: " + e);

        }
    }
}



