package com.comunidadesvirtualesonline.cvo_notificacines.models;

/**
 * Created by Guardian on 11/01/2016.
 */
public class Usuarios {

    private String nomEstudiante;
    private String curEstudiante;
    private String idEstudiante;

    public void setCurEstudiante(String curEstudiante) {
        this.curEstudiante = curEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNomEstudiante(String nomEstudiante) {
        this.nomEstudiante = nomEstudiante;
    }

    public String getNomEstudiante() {
        return nomEstudiante;
    }

    public String getCurEstudiante() {
        return curEstudiante;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }
}
