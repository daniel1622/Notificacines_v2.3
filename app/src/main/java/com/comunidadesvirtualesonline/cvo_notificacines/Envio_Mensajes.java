package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.comunidadesvirtualesonline.cvo_notificacines.fragments.NotificacionesFragment;

/**
 * Created by Guardian on 19/01/2016.
 */
public class Envio_Mensajes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mensaje);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mensajes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Envio_Mensajes.this, Notificaciones.class);
                startActivity(i);

            }
        });
    }


}
