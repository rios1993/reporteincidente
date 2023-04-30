package com.upc.reporteincidente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PeligroActivity extends AppCompatActivity {

    EditText txtPeligro;

    Button btnEnviar, btnUbicacion, btnFoto;

    //private String globalUsername;

    private String Globaluser, GlobalLatitud, GlobalLongitud, GlobalFotoReporte;

    DateFormat date = new SimpleDateFormat("MMM dd yyyy, H:mm Z");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peligro);

        Globaluser = ((Global) this.getApplication()).getGlbUsername();

        ((Global) this.getApplication()).setGlbLatitud(-12.076633907790978);
        ((Global) this.getApplication()).setGlbLongitud(-77.09359547166535);

        //Toast.makeText(this, Globaluser, Toast.LENGTH_SHORT).show();

        asignarReferencias();


    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("OK",(dialogInterface, i) -> {
            Intent intent = new Intent(this,ListarActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }



    private boolean validarDatos(){
        String detalle = txtPeligro.getText().toString();

        Intent intent = getIntent();

        boolean valida = true;

        if(detalle.equals("")){
            txtPeligro.setError("El detalle es obligatorio");
            valida = false;
        }

        GlobalLatitud = ((Global) this.getApplication()).getGlbLatitud()+"";
        GlobalLongitud = ((Global) this.getApplication()).getGlbLongitud()+"";
        GlobalFotoReporte= ((Global) this.getApplication()).getGlbFotoReporte();

        //Log.d("LLL2==>",GlobalLatitud);

        if(GlobalLatitud == null) {
            valida = false;
            Toast.makeText(this, "Faltan las coordenadas", Toast.LENGTH_SHORT).show();
        }

        if(GlobalFotoReporte == null){
            Toast.makeText(this, "Seleccione una foto", Toast.LENGTH_SHORT).show();
            valida = false;
        }

        return valida;

    }

    private void asignarReferencias(){

        String dateFormatted = date.format(Calendar.getInstance().getTime());

        txtPeligro = findViewById((R.id.txtPeligro));
        btnEnviar = findViewById(R.id.btnEnviar);
        btnUbicacion = findViewById(R.id.btnUbicacion);
        btnFoto = findViewById(R.id.btnFoto);

        btnFoto.setOnClickListener(view -> {
            Intent intent = new Intent(this, CargarfotoActivity.class);
            startActivity(intent);
        });

        btnUbicacion.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapaActivity.class);
            //intent.putExtra("latitud","-12.076633907790978");
            //intent.putExtra("longitud","-77.09359547166535");
            //intent.putExtra("titulo", "Mi ubicacion");


            startActivity(intent);
        });

        btnEnviar.setOnClickListener(view -> {
            boolean valida = true;
            valida = validarDatos();

            if(valida) {

                    String url = "https://upcmovilestf.zonaexperimental.com/index.php/newreporte";
                    StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mostrarMensaje("Se insertó correctamente");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("NNN==>", error.toString());

                        }
                    }) {
                        @Nullable
                        @Override

                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parametros = new HashMap<>();
                            Intent intent = getIntent();
                            parametros.put("detalle", txtPeligro.getText().toString());
                            parametros.put("foto", GlobalFotoReporte);
                            parametros.put("latitud", GlobalLatitud);
                            parametros.put("longitud", GlobalLongitud);
                            //parametros.put("id_estado", "1");
                            //parametros.put("id_evidencia", "");
                            parametros.put("username", Globaluser );
                            //parametros.put("username", user );
                            parametros.put("fecha_hora_creacion", dateFormatted);
                            return parametros;
                            //return super.getParams();
                        }
                    };
                    RequestQueue cola = Volley.newRequestQueue(this);
                    cola.add(peticion);

            }
        });


    }
}