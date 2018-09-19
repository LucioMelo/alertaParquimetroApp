package com.tesis.alertaparquimetroapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CapturaBoletoActivity extends AppCompatActivity{
    Button btn;
    ImageView img,imgResult;
    Intent i;
    Bitmap bmp;
    Bitmap bmpBN;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ReconocimientoHoraBoleto reconocimientoHoraBoleto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_boleto);


        btn=(Button)findViewById(R.id.buttonCaptura);
        img=(ImageView)findViewById(R.id.imagenCapturada);
        imgResult=(ImageView)findViewById(R.id.imagenResultado);
        ImageView imgResult=(ImageView) findViewById(R.id.imagenResultado);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK)
        {
            bmp = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bmp);
            reconocimientoHoraBoleto=new ReconocimientoHoraBoleto();

            bmpBN=reconocimientoHoraBoleto.EscalaGrises(bmp,bmp.getWidth(),bmp.getHeight());
            imgResult.setImageBitmap(bmpBN);

            //bmpBN=reconocimientoHoraBoleto.reconocimiento(bmp,bmp.getWidth(),bmp.getHeight());
            //imgResult.setImageBitmap(bmpBN);

        }

    }



}




