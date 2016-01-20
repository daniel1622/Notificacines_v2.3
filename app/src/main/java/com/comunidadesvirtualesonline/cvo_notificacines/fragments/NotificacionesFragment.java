package com.comunidadesvirtualesonline.cvo_notificacines.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.ConsumosServer;
import com.comunidadesvirtualesonline.cvo_notificacines.R;
import com.comunidadesvirtualesonline.cvo_notificacines.adapters.EstudianteAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificacionesFragment extends Fragment {


    TextView Titulo, fechaNotificacion, NombreEstudiant;
    String[] objetos = new String[3];
    String url = ("http://www.comunidadesvirtualesonline.com/notifications/singup.php?token=");
    JSONObject jsonObject;
    String msg = "AVISO:";


    public String mensaje = "estoy aqui";


    public NotificacionesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragments_notificaciones, container, false);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        String JsonTitulo = "HOLA .... !";
        String JsonNomestu = "daniel Pardo C.";
        String JsonFecha = "07/06/06";

        ArrayList<Estudiante> estudiantesArray = new ArrayList<>();

        Estudiante estudiante1 = new Estudiante();

        estudiante1.setTitulo(JsonTitulo);
        estudiante1.setNomEstudiante(JsonNomestu);
        estudiante1.setFecha(JsonFecha);

        estudiantesArray.add(estudiante1);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new EstudianteAdapter(estudiantesArray, R.layout.row_estudiantes));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


}

}

/*
    public class AnsyncTask extends AsyncTask<String,String,String[]> {


        @Override
        protected String[] doInBackground(String...  url) {
            URL url1 = null;
            try {

             // JSONObject  jsonEstudiantes = JsonParser.readerJsonFromUrl(url);

                url1 = new URL("http://www.comunidadesvirtualesonline.com/notifications/singup.php?token=");

                objetos[0] =  url1.getString("titulo");

                objetos[1] =  url1.getString("nombre");

                objetos[2] =  url1.getString("idEstu");




            } catch (IOException e ) {
                e.printStackTrace();
            }

            return objetos;
        }

        protected void onPosExecute(String[] stringFromDoInBackground) {

            Titulo = (TextView) findViewById(R.id.Titulo);

            Titulo.setText(stringFromDoInBackground[0]);

        }
    }
    */

