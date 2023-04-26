package com.upc.reporteincidente;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upc.reporteincidente.databinding.ActivityMapaBinding;

import java.io.IOException;
import java.util.List;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaBinding binding;

    Double latitud,longitud;
    String titulo;

    Button btnGrabarUbicacion;

    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityMapaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitud = ((Global) this.getApplication()).getGlbLatitud();
        longitud = ((Global) this.getApplication()).getGlbLongitud();

        asignarReferencias();
    }

    private void asignarReferencias() {
        btnGrabarUbicacion = findViewById(R.id.btnGrabarUbicacion);

        btnGrabarUbicacion.setOnClickListener(view -> {
            //Intent intent = new Intent(this, PeligroActivity.class);
            //intent.putExtra("latitudresp",latitud+"");
            //intent.putExtra("longitudresp",longitud+"");
            ((Global) this.getApplication()).setGlbLatitud(latitud);
            ((Global) this.getApplication()).setGlbLongitud(longitud);

            //Log.d("Lexit1==>",latitud+"");
            this.finish();
            //startActivity(intent);
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapLongClickListener(latLng -> {
            //Log.d("==>","Valores: "+latLng.toString());
//            try{
//
//                List<Address> listaDirecciones = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
//                if(listaDirecciones.size()>0){
//                    Address direccion = listaDirecciones.get(0);
//                    String nombreDireccion = direccion.getAddressLine(0);
//                    mMap.addMarker(new MarkerOptions()
//                            .position(latLng)
//                            .draggable(true)
//                            .title(nombreDireccion));
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//
            latitud = latLng.latitude;
            longitud = latLng.longitude;
          mMap.addMarker(new MarkerOptions().position(latLng));


        });


        //Intent intent = getIntent();

        //Log.d("C1==>",intent.getStringExtra("latitud"));

//        if(intent.getStringExtra("latitud") != null) {
//
//            latitud = Double.parseDouble(intent.getStringExtra("latitud"));
//            longitud = Double.parseDouble(intent.getStringExtra("longitud"));
//            titulo = intent.getStringExtra("titulo");
//            Log.d("CC1==>",intent.getStringExtra("latitud"));
//
//        } else if (intent.getStringExtra("latitudver") != null) {
//            latitud = Double.parseDouble(intent.getStringExtra("latitudver"));
//            longitud = Double.parseDouble(intent.getStringExtra("longitudver"));
//            titulo = intent.getStringExtra("titulover");
//
//        }

        titulo = "Mi ubicacion";

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {
                latitud = marker.getPosition().latitude;
                longitud = marker.getPosition().longitude;

                //Toast.makeText(this, latitud+"", Toast.LENGTH_SHORT).show();
                //Log.d("Lx1==>",latitud+"");

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }
        });
        // Add a marker in Sydney and move the camera

        //LatLng upc = new LatLng(latitud, longitud);
        LatLng upc = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(upc).draggable(true).title(titulo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(upc,18));



        //latitud = upc.latitude;
        //longitud = upc.longitude;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);






    }





}