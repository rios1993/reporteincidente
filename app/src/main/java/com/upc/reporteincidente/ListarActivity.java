package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {

    FloatingActionButton btnNuevo;
    RecyclerView rvListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        asignarReferencias();
    }

    private void asignarReferencias(){
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(view -> {
            Intent intent = new Intent(this,PeligroActivity.class);
            startActivity(intent);
        });
        rvListar = findViewById(R.id.rvListar);
        cargarDatos();
    }

    private void cargarDatos(){
        String url = "https://upcmovilestf.zonaexperimental.com/index.php/listarreportes";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    List<Reporte> listaReportes = new ArrayList<>();
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        listaReportes.add(new Reporte (
                                object.getInt("id_reporte"),
                                object.getString("detalle"),
                                object.getString("foto"),
                                object.getDouble("latitud"),
                                object.getDouble("longitud"),
                                object.getInt("id_estado"),
                                object.getInt("id_evidencia"),
                                object.getString("username"),
                                object.getString("fecha_hora_creacion")));

                    }
                    AdaptadorPersonalizado adaptador;
                    adaptador = new AdaptadorPersonalizado(ListarActivity.this,listaReportes);
                    rvListar.setAdapter(adaptador);
                    rvListar.setLayoutManager(new LinearLayoutManager(ListarActivity.this));

                }catch (JSONException e){
                    Log.d("LLL1==>",e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LLL2==>",error.toString());

            }
        });
        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(peticion);
    }
}