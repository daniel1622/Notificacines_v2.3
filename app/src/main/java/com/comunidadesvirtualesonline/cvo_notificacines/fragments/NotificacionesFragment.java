package com.comunidadesvirtualesonline.cvo_notificacines.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.R;
import com.comunidadesvirtualesonline.cvo_notificacines.adapters.EstudianteAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.consumos.ConsumosServer;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificacionesFragment extends Fragment {


    private Layout layout;
    private RecyclerView my_recycler_view;
    private EstudianteAdapter estudianteAdapter;
    private ArrayList<Estudiante> estudiantesArray = new ArrayList<>();
    private String jsonArray = "";


    public NotificacionesFragment() {
        // Required empty public constructor
    }


    private void send (){

     estudiantesArray = setJson();
        estudianteAdapter.setEstudiantes(estudiantesArray);

    }


    private ArrayList<Estudiante> setJson (){
        ArrayList<Estudiante> estudiantesArray = new ArrayList<>();

            try {

                JSONObject jsonObject = new JSONObject(jsonArray);

                JSONArray jsonArray = jsonObject.getJSONArray("result");

                for (int i = 0; i< jsonArray.length(); i++) {
                    JSONObject datosEstu = jsonArray.getJSONObject(i);

                    String titulo = datosEstu.getString("titulo");
                    String nombreEstu = datosEstu.getString("name");
                    String fecha = datosEstu.getString("fecha");
                    String letter = datosEstu.getString("iniciales");
                    String url = datosEstu.getString("url");
                    String tipo = datosEstu.getString("tipo");

                    Estudiante estudiante = new Estudiante();

                    estudiante.setTitulo(titulo);
                    estudiante.setNomEstudiante(nombreEstu);
                    estudiante.setFecha(fecha);
                    estudiante.setUrl(url);
                    estudiante.setTipo(tipo);

                    estudiantesArray.add(estudiante);


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        return  estudiantesArray;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragments_notificaciones, container,false);
        my_recycler_view = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        estudianteAdapter = new EstudianteAdapter(getActivity());
        my_recycler_view.setAdapter(estudianteAdapter);




        return view;


    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        SharedPreferences pref = getActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("consumo", 2);
        editor.commit();

        Intent i = new Intent(getActivity(), ConsumosServer.class);
        getActivity().startService(i);


        jsonArray = pref.getString("JsonNotification","json");
        Log.i("JSONARRAY", "= " + jsonArray);


        send();

    }



    }



