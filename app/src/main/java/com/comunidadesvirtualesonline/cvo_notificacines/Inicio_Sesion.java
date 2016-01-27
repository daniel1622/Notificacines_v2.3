package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by Guardian on 13/01/2016.
 */
public class Inicio_Sesion extends AppCompatActivity implements
        View.OnClickListener {

    public String mensaje = "MSG";
    public String User;
    public String contraseña;
    public String nom_db = "10001";
    public String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicar_sesion);

        Button boton = (Button) findViewById(R.id.btnRegistrarUser);
        boton.setOnClickListener(this);

    }

    public void RegistrationUser (View v) {

        EditText inicio_usuario = (EditText) findViewById(R.id.inicio_usuario);//aqui se obtiene el unsuario dijitado en el layaut iniciar_sesion.
        EditText inicio_contraseña = (EditText) findViewById(R.id.inicio_contraseña); //aqui se obtiene la contraseña dijitada en el layaut iniciar_sesion.

        // se debe convertir el EditText a un string para luego ser enviado a la base de datos.
        User = inicio_usuario.toString();
        contraseña = inicio_contraseña.toString();

        SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        token = prefs.getString("TOKEN", "id del telefono ");

        Log.i(mensaje,"ESTE ES EL TOKEN ALMACENADO EN EL SharedPreferences"+token);

        Log.i(mensaje," DATTOS SESION "+token+User+contraseña);

        ConsumosServer consumosServer = new ConsumosServer();
        consumosServer.checkUser(User,contraseña,token);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnRegistrarUser://Si el id del botón pulsado se corresponde con el id que tenemos aquí entraría dentro del case.
                //Aquí va el código que se va a ejecutar cuando se pulse el botón.

                EditText inicio_usuario = (EditText) findViewById(R.id.inicio_usuario);//aqui se obtiene el unsuario dijitado en el layaut iniciar_sesion.
                EditText inicio_contraseña = (EditText) findViewById(R.id.inicio_contraseña); //aqui se obtiene la contraseña dijitada en el layaut iniciar_sesion.

                // se debe convertir el EditText a un string para luego ser enviado a la base de datos.
                User = inicio_usuario.toString();
                contraseña = inicio_contraseña.toString();

                SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

                token = prefs.getString("TOKEN", "id del telefono ");

                Log.i(mensaje,"ESTE ES EL TOKEN ALMACENADO EN EL SharedPreferences"+token);

                Log.i(mensaje," DATTOS SESION "+token+User+contraseña);

                ConsumosServer consumosServer = new ConsumosServer();
                consumosServer.checkUser(User,contraseña,token);

        }

    }
}

