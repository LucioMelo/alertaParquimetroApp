package com.tesis.alertaparquimetroapp.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tesis.alertaparquimetroapp.MenuActivity;
import com.tesis.alertaparquimetroapp.R;
import com.tesis.alertaparquimetroapp.UbicacionActivity;

public class TiempoRestante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_restante);

        BottomBar bottomBar=(BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tiempoRestante);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {

                if(tabId==R.id.inicio)
                {
                    /*
                    inicioFragment inicioFragment= new inicioFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,inicioFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                    */

                    Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                    getApplicationContext().startActivity(intent);
                }
                else if(tabId==R.id.localizar)
                {
                    /*
                    localizarFragment localizarFragment= new localizarFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,localizarFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                    */

                    Intent intent = new Intent(getApplicationContext(),UbicacionActivity.class);
                    getApplicationContext().startActivity(intent);
                }
                else if(tabId== R.id.tiempoRestante)
                {
                /*
                    tiempoRestanteFragment tiempoRestanteFragment= new tiempoRestanteFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,tiempoRestanteFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                 */

                }

            }
        });
    }
}
