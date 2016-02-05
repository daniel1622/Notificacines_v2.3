package com.comunidadesvirtualesonline.cvo_notificacines.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.RequiresPermission;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.comunidadesvirtualesonline.cvo_notificacines.models.Estudiante;

import com.comunidadesvirtualesonline.cvo_notificacines.*;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Guardian on 10/01/2016.
 */
public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder>  {

   private ArrayList<Estudiante> estudiantes;

    private LayoutInflater layoutInflater;
    private Context mContext;
    private View v;

    public EstudianteAdapter( Context context){

        layoutInflater =LayoutInflater.from(context);
        this.mContext = context;
    }


    public void setEstudiantes ( ArrayList<Estudiante> estudiantes){

        this.estudiantes = estudiantes;
        notifyItemRangeChanged(0,estudiantes.size());

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        v = layoutInflater.inflate(R.layout.row_estudiantes, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        Bitmap bitmap = BitmapFactory.decodeResource(v.getResources(), R.drawable.avatar);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView)v.findViewById(R.id.imageUser);
        circularImageView.setImageBitmap(circularBitmap);


        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textViewUrl = (TextView) v.findViewById(R.id.url);
                String url = textViewUrl.getText().toString();
                Intent i = new Intent(mContext, webActivity.class);
                i.putExtra("url", url);
                mContext.startActivity(i);

                CircleImageView estadoCardView = (CircleImageView) v.findViewById(R.id.circle_Estado);
                estadoCardView.setImageResource(R.drawable.circle);
            }
        });

                return  viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Estudiante estudiante = estudiantes.get(position);

        holder.estuTitulo.setText(estudiante.getTitulo());
        holder.estuNombre.setText(estudiante.getNomEstudiante());
        holder.estuFecha.setText(estudiante.getFecha());
        holder.estuUrl.setText(estudiante.getUrl());
        holder.tipo.setText(estudiante.getTipo());

        String tip = (estudiante.getTipo());

        Log.i("TIPO ONBIENVIEWHOLDER",""+tip);

        /*
        1 = corazon enfermeria
        2 = ojo disciplina o academico
        3 = carta comunicado normal
        4 = calendario recordatorios
        5= billete pagos pension
        6= mundo es informacion general

        */

        CircleImageView circleImageView1 = (CircleImageView)v.findViewById(R.id.circle_image1);
        switch (tip) {

            case "1":
                Log.i("CASO 1", "!!");
                circleImageView1.setImageResource(R.drawable.heart);

                break;

            case "2":
                Log.i("CASO 2", "!!");
                circleImageView1.setImageResource(R.drawable.eye);
                break;

            case "3":
                Log.i("CASO 3", "!!");
                circleImageView1.setImageResource(R.drawable.mail);
                break;

            case "4":
                Log.i("CASO 4", "!!");
                circleImageView1.setImageResource(R.drawable.calendar);
                break;

            case "5":
                Log.i("CASO 5", "!!");
                circleImageView1.setImageResource(R.drawable.banknote);
                break;

            case "6":
                Log.i("CASO 6", "!!");
                circleImageView1.setImageResource(R.drawable.world);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

       public TextView estuTitulo;
        public TextView estuNombre;
        public TextView estuFecha;
        public TextView estuUrl;
        public TextView tipo;
        public RelativeLayout relativeLayout;


        public ViewHolder(View itemView) {
            super(itemView);


            estuTitulo = (TextView) itemView.findViewById(R.id.Titulo);

            estuNombre = (TextView) itemView.findViewById(R.id.NombreEstudiante);

            estuFecha = (TextView) itemView.findViewById(R.id.fechaNotificacion);

            estuUrl = (TextView) itemView.findViewById(R.id.url);

            tipo = (TextView) itemView.findViewById(R.id.tipo);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relLayout);

            itemView.setClickable(true);

        }
    }

}
