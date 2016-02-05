package com.comunidadesvirtualesonline.cvo_notificacines.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.comunidadesvirtualesonline.cvo_notificacines.R;
import com.comunidadesvirtualesonline.cvo_notificacines.models.Usuarios;

import java.util.ArrayList;

/**
 * Created by Guardian on 11/01/2016.
 */
public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder>  {

    private ArrayList<Usuarios>  usuarios ;
    private int itemLayout;

     public UsuariosAdapter(ArrayList<Usuarios>usuarios, int itemLayout){
         this.usuarios = usuarios;
         this.itemLayout = itemLayout;
    }


    @Override
    public UsuariosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent ,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuariosAdapter.ViewHolder holder, int position) {

        Usuarios users = usuarios.get(position);

        holder.estuNombre.setText(users.getNomEstudiante());
        holder.estuCurso.setText(users.getCurEstudiante());
        holder.estuID.setText(users.getIdEstudiante());


    }
    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView estuNombre;
        public TextView estuCurso;
        public TextView estuID;

        public ViewHolder(View itemView) {
            super(itemView);

            estuNombre = (TextView) itemView.findViewById(R.id.NomEstudiante);

            estuCurso = (TextView) itemView.findViewById(R.id.Curso);

            estuID = (TextView) itemView.findViewById(R.id.IdEstu);

        }
    }

}
