package com.tesis.alertaparquimetroapp.vistas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import com.tesis.alertaparquimetroapp.R;
import com.tesis.alertaparquimetroapp.modelo.dbFirebaseParquimetro;
import com.tesis.alertaparquimetroapp.references.firebasesReferences;

import static com.tesis.alertaparquimetroapp.R.id.map;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Marker marcador;
    private Marker marcadorEstacionamiento;
    double lat = 0.0;
    double lng = 0.0;


    Button guardarUbicacion,buscarUbicacion;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String usuario = user.getUid();
           // Log.i("Usuario ", usuario);



    private static final int MY_LOCATION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        guardarUbicacion = (Button) findViewById(R.id.idGuardarUbicacion);
        guardarUbicacion.setOnClickListener(this);

        buscarUbicacion = (Button) findViewById(R.id.idBuscarUbicacion);
        buscarUbicacion.setOnClickListener(this);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference(firebasesReferences.appParquimetro_references);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                dbFirebaseParquimetro datos=dataSnapshot.getValue(dbFirebaseParquimetro.class);
                Log.i("Datos",dataSnapshot.toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
            mapFragment.getMapAsync(this);
    }


    @Override
    public void onClick(View v) {
        int idUbicacion=1;


        if (v.getId() == R.id.idGuardarUbicacion) {

            FirebaseDatabase databaseR=FirebaseDatabase.getInstance();
            DatabaseReference myRefR=databaseR.getReference(firebasesReferences.appParquimetro_references).child(usuario);

             //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null)
            {
                lat = location.getLatitude();
                lng = location.getLongitude();

                System.out.println("latitud:" + lat + " Logitud:" + lng);
            //agregarMarcadorAutoEstacionado(lat, lng);

                agregarMarcadorAutoEstacionado(lat,lng);

                dbFirebaseParquimetro datosll=new dbFirebaseParquimetro(lat,lng,idUbicacion);
                myRefR.push().setValue(datosll);

                Log.i("Datos"," lat:"+lat+" lng: "+lng);
            }
        }

        else if (v.getId() == R.id.idBuscarUbicacion)
        {
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference(firebasesReferences.appParquimetro_references).child(usuario);
            DatabaseReference pushedpostref=ref.push();
            String postID=pushedpostref.getKey();
            System.out.println("PostID:"+postID);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dbFirebaseParquimetro post = dataSnapshot.getValue(dbFirebaseParquimetro.class);

                    System.out.println("latitud"+post.getUbica_latitud());
                    System.out.println("Longitud"+post.getUbica_longitud());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

        }
        else {
            miUbicacion();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        System.out.println("..................."+requestCode);
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    System.out.println("Permiso concedido");
                    miUbicacion();

                } else {

                    System.out.println("Permiso denegado");
                }
                return;
            }
        }
    }

    private void agregarMarcadorAutoEstacionado(double lat, double lng)
    {
        LatLng coordenadasAuto = new LatLng(lat, lng);
        CameraUpdate miUbicacionAutoEstacinado = CameraUpdateFactory.newLatLngZoom(coordenadasAuto, 16);
        if (marcador != null)
            marcador.remove();

        marcadorEstacionamiento = mMap.addMarker(new MarkerOptions()
                .position(coordenadasAuto)
                .title("Posición del auto")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.animateCamera(miUbicacionAutoEstacinado);
    }

    private void agregarMarcador(double lat, double lng)
    {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null)
            marcador.remove();

        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posición Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

        mMap.animateCamera(miUbicacion);

    }

    private void actualizarUbicacion(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            System.out.println("latitud:" + lat + " Logitud:" + lng);
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };

    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locationListener);
    }
}
