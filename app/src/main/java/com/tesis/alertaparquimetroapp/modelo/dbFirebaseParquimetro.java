package com.tesis.alertaparquimetroapp.modelo;

/**
 * Created by Lucio on 26/10/2017.
 */

public class dbFirebaseParquimetro {
    double ubica_latitud;
    double ubica_longitud;



    int id_ubicacion;


    public dbFirebaseParquimetro() {
    }

    public dbFirebaseParquimetro(double ubica_latitud, double ubica_longitud,int id_ubicacion ) {
        this.ubica_latitud = ubica_latitud;
        this.ubica_longitud = ubica_longitud;
        this.id_ubicacion=id_ubicacion;
    }

    public double getUbica_latitud() {
        return ubica_latitud;
    }

    public void setUbica_latitud(double ubica_latitud) {
        this.ubica_latitud = ubica_latitud;
    }

    public double getUbica_longitud() {
        return ubica_longitud;
    }

    public void setUbica_longitud(double ubica_longitud) {
        this.ubica_longitud = ubica_longitud;
    }

    public int getId_ubicacion() {return id_ubicacion;}

    public void setId_ubicacion(int id_ubicacion) {
        this.id_ubicacion = id_ubicacion;
    }






}
