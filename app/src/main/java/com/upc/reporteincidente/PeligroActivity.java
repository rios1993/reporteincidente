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

    Button btnEnviar;

    //private String globalUsername;

    private String Globaluser;
    DateFormat date = new SimpleDateFormat("MMM dd yyyy, H:mm Z");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peligro);

        Globaluser = ((Global) this.getApplication()).getGlbUsername();

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

        boolean valida = true;

        if(detalle.equals("")){
            txtPeligro.setError("El detalle es obligatorio");
            valida = false;
        }

        return valida;

    }

    private void asignarReferencias(){

        String dateFormatted = date.format(Calendar.getInstance().getTime());

        txtPeligro = findViewById((R.id.txtPeligro));
        btnEnviar = findViewById(R.id.btnEnviar);

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
                            parametros.put("detalle", txtPeligro.getText().toString());
                            //parametros.put("foto", "Foto");
                            //parametros.put("latitud", "1.1");
                            //parametros.put("longitud", "2.2");
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