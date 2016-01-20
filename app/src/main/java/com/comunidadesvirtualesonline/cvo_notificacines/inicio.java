package com.comunidadesvirtualesonline.cvo_notificacines;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Guardian on 13/01/2016.
 */
public class inicio  extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
    }

    public void onClickInicio (View v){


        Intent i = new Intent(this, Inicio_Sesion.class );
        startActivity(i);
    }
}

