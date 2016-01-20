package com.comunidadesvirtualesonline.cvo_notificacines;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.comunidadesvirtualesonline.cvo_notificacines.GCMIntentService;

public class StartAplication extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_aplication);

        GCMIntentService Registro = new GCMIntentService();
        if(Registro.isRegistro()){
            Log.i("Nos resgitramos este es ek token: ".toString(), Registro.getToken());
        }else{
            Log.i("No pudimos registrarnos :'(".toString(), "y no se porque!!");
        }
    }

}
