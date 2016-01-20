package com.comunidadesvirtualesonline.cvo_notificacines.adapters;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import com.comunidadesvirtualesonline.cvo_notificacines.*;

import java.util.ArrayList;

/**
 * Created by Guardian on 10/01/2016.
 */
public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder>  {

   private ArrayList<Estudiante> estudiantes;
   private int itemLayout;


    public EstudianteAdapter( ArrayList<Estudiante> estudiantes, int itemLayout){

        this.estudiantes = estudiantes;
        this.itemLayout =  itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
                return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Estudiante estudiante = estudiantes.get(position);

        holder.estuTitulo.setText(estudiante.getTitulo());
        holder.estuNombre.setText(estudiante.getNomEstudiante());
        holder.estuFecha.setText(estudiante.getFecha());
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

       public TextView estuTitulo;
        public TextView estuNombre;
        public TextView estuFecha;

        public ViewHolder(View itemView) {
            super(itemView);


            estuTitulo = (TextView) itemView.findViewById(R.id.Titulo);

            estuNombre = (TextView) itemView.findViewById(R.id.NombreEstudiante);

            estuFecha = (TextView) itemView.findViewById(R.id.fechaNotificacion);



        }
    }

}
