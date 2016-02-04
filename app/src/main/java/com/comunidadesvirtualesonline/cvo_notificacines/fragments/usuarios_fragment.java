package com.comunidadesvirtualesonline.cvo_notificacines.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.comunidadesvirtualesonline.cvo_notificacines.R;
import com.comunidadesvirtualesonline.cvo_notificacines.adapters.EstudianteAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.adapters.UsuariosAdapter;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Usuarios;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class usuarios_fragment extends Fragment {


    public usuarios_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        ArrayList<Usuarios> UsuariosArray = new ArrayList<>();

        Usuarios usuario1 = new Usuarios();

        usuario1.setNomEstudiante("DANIEL PARDO CUENCA");
        usuario1.setCurEstudiante("Octavo");
        usuario1.setIdEstudiante("12345678");

        UsuariosArray.add(usuario1);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view_2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new UsuariosAdapter(UsuariosArray, R.layout.row_usuarios));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }


}
