package com.tesis.alertaparquimetroapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tesis.alertaparquimetroapp.CapturaBoletoActivity;
import com.tesis.alertaparquimetroapp.R;
import com.tesis.alertaparquimetroapp.modelo.setGetCardViewMenu;
import com.tesis.alertaparquimetroapp.vistas.MapaActivity;
import com.tesis.alertaparquimetroapp.vistas.TiempoRestante;

import java.util.List;

/**
 * Created by Lucio on 11/10/2017.
 */

public class menuAdapterRecyclerView extends RecyclerView.Adapter<menuAdapterRecyclerView.menuViewHolder>{

private List<setGetCardViewMenu> items;
    private Activity activity;
    Context context;




    public class menuViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView imagen;
        public TextView nombreMenu;

        public menuViewHolder(View itemView)
        {
            super(itemView);
            imagen=(ImageView) itemView.findViewById(R.id.idImagen);
            nombreMenu=(TextView) itemView.findViewById(R.id.idNombreMenu);
            context=itemView.getContext();
        }
    }

    public menuAdapterRecyclerView(List<setGetCardViewMenu> items)
    {
        this.items=items;
    }

    @Override
    public menuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_menu,parent,false);
        return new menuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(menuViewHolder holder, final int position) {
        holder.imagen.setImageResource(items.get(position).getImagen());
        holder.nombreMenu.setText(items.get(position).getNombreMenu());

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position==0){
                    System.out.println("posicion: "+position+" nombre: "+items.get(position).getNombreMenu());
                    Intent intent = new Intent(context,CapturaBoletoActivity.class);
                    context.startActivity(intent);

                }
                else if(position==1)
                {
                    //String valor="Hola texto ENVIADO";
                    //System.out.println("posicion: "+position+" nombre: "+items.get(position).getNombreMenu());
                    Intent intent = new Intent(context, MapaActivity.class);
                    //intent.putExtra("clave",valor);
                    context.startActivity(intent);

                }
                else if (position==2)
                {
                    System.out.println("posicion: "+position+" nombre: "+items.get(position).getNombreMenu());
                    Intent intent = new Intent(context,TiempoRestante.class);
                    context.startActivity(intent);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
