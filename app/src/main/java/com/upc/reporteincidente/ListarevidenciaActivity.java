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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarevidenciaActivity extends AppCompatActivity {

    RecyclerView rvListarEvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarevidencia);

        asignarReferencias();
    }

    private void asignarReferencias(){

        rvListarEvi = findViewById(R.id.rvListarEvi);
        cargarDatos();
    }

    private void cargarDatos(){
        String url = "https://upcmovilestf.zonaexperimental.com/index.php/listarreportesevi";
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
                                //object.getInt("id_estado"),
                                object.getString("descripcion"),
                                object.getInt("id_evidencia"),
                                object.getString("username"),
                                object.getString("fecha_hora_creacion")));

                    }
                    AdaptadorPersonalizadoEvi adaptador;
                    adaptador = new AdaptadorPersonalizadoEvi(ListarevidenciaActivity.this,listaReportes);
                    rvListarEvi.setAdapter(adaptador);
                    rvListarEvi.setLayoutManager(new LinearLayoutManager(ListarevidenciaActivity.this));

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