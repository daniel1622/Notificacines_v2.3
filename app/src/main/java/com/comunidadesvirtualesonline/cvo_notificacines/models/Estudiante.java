package com.comunidadesvirtualesonline.cvo_notificacines.models;

/**
 * Created by Guardian on 10/01/2016.
 */
public class Estudiante {

    private String Titulo;
    private String NomEstudiante;
    private String Fecha;
    private String Url;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setNomEstudiante(String nomEstudiante) {
        NomEstudiante = nomEstudiante;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getNomEstudiante() {
        return NomEstudiante;
    }

    public String getFecha() {
        return Fecha;
    }
}
