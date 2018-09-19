package com.tesis.alertaparquimetroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.tesis.alertaparquimetroapp.adapter.menuAdapterRecyclerView;
import com.tesis.alertaparquimetroapp.modelo.setGetCardViewMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager Imanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        List<setGetCardViewMenu> item=new ArrayList<>();
        item.add(new setGetCardViewMenu(R.drawable.reloj2,"Capturar Boleto"));
        item.add(new setGetCardViewMenu(R.drawable.bicycle,"Guardar Ubicacion"));
        item.add(new setGetCardViewMenu(R.drawable.reloj,"Tiempo Restante"));


        recyclerView=(RecyclerView) findViewById(R.id.idRecyclerViewMenu);
        recyclerView.setHasFixedSize(true);
        Imanager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(Imanager);
        adapter=new menuAdapterRecyclerView(item);
        recyclerView.setAdapter(adapter);

    }
}
