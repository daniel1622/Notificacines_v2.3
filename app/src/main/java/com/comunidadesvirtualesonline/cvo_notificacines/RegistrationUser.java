package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comunidadesvirtualesonline.cvo_notificacines.consumos.ConsumosServer;


/**
 * Created by Guardian on 13/01/2016.
 */
public class RegistrationUser extends AppCompatActivity implements
        View.OnClickListener {

    public String mensaje = "MSG";
    public String User,contraseña,token;
    public String id_db = "10001";
    private EditText inicio_usuario, inicio_contraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicar_sesion);



        Button boton = (Button) findViewById(R.id.btnRegistrarUser);
        boton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnRegistrarUser://Si el id del botón pulsado se corresponde con el id que tenemos aquí entraría dentro del case.
                //Aquí va el código que se va a ejecutar cuando se pulse el botón.

                inicio_usuario = (EditText) findViewById(R.id.inicio_usuario);//aqui se obtiene el unsuario dijitado en el layaut iniciar_sesion.
                inicio_contraseña = (EditText) findViewById(R.id.inicio_contraseña); //aqui se obtiene la contraseña dijitada en el layaut iniciar_sesion.

                // se debe convertir el EditText a un string para luego ser enviado a la base de datos.
                User = inicio_usuario.getText().toString();
                contraseña = inicio_contraseña.getText().toString();
                Log.i(mensaje,"DATOS DEL EDITTEXT TRANSFORMADOS A STRING= "+User+"----"+contraseña);

                SharedPreferences prefs = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("u", User);
                editor.putString("p", contraseña);
                editor.putString("id_db", id_db);
                editor.putInt("consumo",1);
                editor.commit();


                Intent i = new Intent(this, ConsumosServer.class);
                startService(i);

                //se obtiene un estado de un Json para saber si el usuario se registro correctamente en la bd.
                String estado_registration = prefs.getString("estado_registration","estado que indica si el usuario se registro o no");
                Log.i(mensaje,"ESTADO_REGISTRATION class "+estado_registration);

                CharSequence text = "Usuario o Contraseña Incorrectos";
                int duration = Toast.LENGTH_SHORT;

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_dialog,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView textToast = (TextView) layout.findViewById(R.id.text_toast);
                textToast.setText(text);



                if (estado_registration.equals("0")){
                    Log.i(mensaje,"if 0 "+estado_registration);
                    Toast toast = Toast.makeText(this, "El usuario se registro correctamente", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(this, Notificaciones.class);
                    startActivity(intent);
                }else
                       if (estado_registration.equals("1")){
                       Log.i(mensaje,"if 1 "+estado_registration);
                       Toast toast = Toast.makeText(this, "!Error: No se puede registrar¡", Toast.LENGTH_SHORT);
                       toast.show();

                    }else
                       if (estado_registration.equals("2")){
                           Log.i(mensaje, "if 2 " + estado_registration);

                Toast toast = new Toast(this);
                toast.setDuration(duration);
                toast.setView(layout);
                toast.show();
                    }
                }
        }
}




