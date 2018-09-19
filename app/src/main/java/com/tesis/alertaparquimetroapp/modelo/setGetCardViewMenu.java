package com.tesis.alertaparquimetroapp.modelo;

/**
 * Created by Lucio on 11/10/2017.
 */

public class setGetCardViewMenu {
    private int imagen;
    private String nombreMenu;

    public setGetCardViewMenu(int imagen, String nombreMenu) {
        this.imagen = imagen;
        this.nombreMenu = nombreMenu;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }
}
