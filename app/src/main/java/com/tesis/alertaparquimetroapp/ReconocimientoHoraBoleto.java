package com.tesis.alertaparquimetroapp;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Lucio on 13/10/2017.
 */

public class ReconocimientoHoraBoleto
{

    Bitmap bmpBN;
    Bitmap ImgLetraBN;


    public Bitmap EscalaGrises(Bitmap bmp, int ancho, int alto)
    {

        bmpBN = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        for (int i=0;i<ancho;i++)
            for (int j=0;j<alto;j++)
            {
                int pixel = bmp.getPixel(i,j);
                int rval = Color.red(pixel);
                int gval = Color.green(pixel);
                int bval = Color.blue(pixel);
                int iColor = Color.argb(255, rval, gval, bval);

                if(rval>=100&&gval>=100&&bval>=100)				//Si es mayor a 100 entra y asigna al bitmap el valor 255 (blanco).
                    bmpBN.setPixel(i,j, Color.rgb(255, 255, 255));

                else
                    bmpBN.setPixel(i,j, Color.rgb(0,0,0));

            }
            return bmpBN;
    }



    public Bitmap reconocimientoBoleto(Bitmap bmp, int ancho, int alto)
    {


        return ImgLetraBN;
    }

}
