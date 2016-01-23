package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.fragments.NotificacionesFragment;

/**
 * Created by Guardian on 19/01/2016.
 */
public class Envio_Mensajes extends AppCompatActivity {


    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mensaje);


        //Obteniendo la intancia del textview
        info =(TextView)findViewById(R.id.info);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mensajes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Envio_Mensajes.this, Notificaciones.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_mensajes, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.back:
                //metodoAtras()
                info.setText("Se presionó Añadir");
                Intent inten = new Intent(this, Notificaciones.class);
                startActivity(inten);

                return true;
            case R.id.enviar:
                //metodoEnviar()
                info.setText("Se presionó Buscar");
                return true;
            case R.id.edit:
                //obcion de editar
                info.setText("Se presiono editar");
               return true;

            case R.id.delete:
                //obcion de editar
                info.setText("Se presiono eliminar");
                return true;

            case R.id.action_settings:
                //metodoSettings()
                info.setText("Se presionó Ajustes");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }




}
