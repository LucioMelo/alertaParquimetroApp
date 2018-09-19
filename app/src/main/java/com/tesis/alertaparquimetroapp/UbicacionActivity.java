package com.tesis.alertaparquimetroapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;

public class UbicacionActivity extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        String value="Ubicacion";




/*
        Bundle extras=getIntent().getExtras();
        if(extras!=null)
        {
            value=extras.getString("clave");
        }
*/
       // TextView textView=(TextView) findViewById(R.id.texto1);
       // textView.setText(value);
    }
}
