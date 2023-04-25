package com.upc.reporteincidente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Objects;

public class EvidenciaActivity extends AppCompatActivity {

    EditText txtAcciones,txtFechaAtencion;
    Button btnGrabarEvidencia, btnFotoEvidencia;
    RadioButton radioEnProceso, radioAtendido;

    RadioGroup radioGrupo;

    private String Globaluser;
    DateFormat date = new SimpleDateFormat("MMM dd yyyy, H:mm Z");

    Boolean modificar=false;

    private String estado;

    private Integer int_estado_reporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evidencia2);

        Globaluser = ((Global) this.getApplication()).getGlbUsername();

        asignarReferencias();

        cargarDatos();

    }

    private boolean validarDatos(){
        String detalle = txtAcciones.getText().toString();

        boolean valida = true;

        if(detalle.equals("")){
            txtAcciones.setError("Las acciones son obligatorias");
            valida = false;
        }
        if(!radioEnProceso.isChecked() && !radioAtendido.isChecked()){
            Toast.makeText(this, "Seleccione un estado", Toast.LENGTH_SHORT).show();
            valida = false;
        }

        return valida;

    }

    private void cargarDatos(){
        if(!Objects.equals(getIntent().getStringExtra("id_evidencia"), "0")){
            modificar=true;
            //id_mascota = Integer.parseInt(getIntent().getStringExtra("id_mascota"));
            txtAcciones.setText(getIntent().getStringExtra("acciones"));

            estado = getIntent().getStringExtra("estado");

            //Log.d("MMM==>", estado);

            if(Objects.equals(estado, "En proceso")){
                radioGrupo.check(R.id.radioEnProceso);
            }

            txtFechaAtencion.setText(getIntent().getStringExtra("fecha_atendido"));

        }
    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("OK",(dialogInterface, i) -> {
            Intent intent = new Intent(this,ListarevidenciaActivity.class);
            startActivity(intent);
        });
        ventana.create().show();
    }

    private void asignarReferencias(){
        txtAcciones = findViewById(R.id.txtAccionesEvidencia);
        txtFechaAtencion = findViewById(R.id.txtFechaAtendido);
        btnGrabarEvidencia = findViewById(R.id.btnGrabarAtencion);
        btnFotoEvidencia = findViewById(R.id.btnFotoEvidencia);
        radioEnProceso = findViewById(R.id.radioEnProceso);
        radioAtendido=findViewById(R.id.radioAtendido);
        radioGrupo=findViewById(R.id.radioGroup);

        String dateFormatted = date.format(Calendar.getInstance().getTime());



        btnGrabarEvidencia.setOnClickListener(view -> {
            boolean valida = true;
            valida = validarDatos();

            if(radioEnProceso.isChecked()){
                int_estado_reporte = 2;
            }
            if(radioAtendido.isChecked()){
                int_estado_reporte = 3;
            }

            if(valida) {
                if (!modificar) {
                    String url = "https://upcmovilestf.zonaexperimental.com/index.php/newevidencia";
                    String url2 = "https://upcmovilestf.zonaexperimental.com/index.php/updatereporte";
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
                            parametros.put("id_reporte",getIntent().getStringExtra("id_reporte"));
                            parametros.put("acciones", txtAcciones.getText().toString());
                            //parametros.put("tipo", txt.getText().toString());
                            parametros.put("fecha_enproceso", dateFormatted);
                            parametros.put("username", Globaluser);
                            //parametros.put("fecha_nacimiento", txtFecha.getText().toString());

                            //Log.d("MMM==>", getIntent().getStringExtra("id_reporte"));
                            //Log.d("MMM==>", txtAcciones.getText().toString());
                            //Log.d("MMM==>", dateFormatted);
                            //Log.d("MMM==>", Globaluser);
                            return parametros;
                            //return super.getParams();
                        }
                    };

                    StringRequest peticion2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //mostrarMensaje("Se insertó correctamente");

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
                            Map<String, String> parametros2 = new HashMap<>();
                            parametros2.put("id_reporte",getIntent().getStringExtra("id_reporte"));
                            parametros2.put("id_estado", int_estado_reporte+"");
                            //parametros.put("tipo", txt.getText().toString());
                            //parametros.put("fecha_enproceso", dateFormatted);
                            //parametros.put("username", Globaluser);
                            //parametros.put("fecha_nacimiento", txtFecha.getText().toString());

                            //Log.d("MMM==>", getIntent().getStringExtra("id_reporte"));
                            //Log.d("MMM==>", txtAcciones.getText().toString());
                            //Log.d("MMM==>", dateFormatted);
                            //Log.d("MMM==>", Globaluser);
                            return parametros2;
                            //return super.getParams();
                        }
                    };



                    RequestQueue cola = Volley.newRequestQueue(this);
                    cola.add(peticion);
                    cola.add(peticion2);


                }else{
                    String url = "https://upcmovilestf.zonaexperimental.com/index.php/updevidencia";
                    String url2 = "https://upcmovilestf.zonaexperimental.com/index.php/updatereporte";
                    StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mostrarMensaje("Se actualizó correctamente");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("MMM==>", error.toString());

                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parametros = new HashMap<>();
                            parametros.put("id_evidencia",getIntent().getStringExtra("id_evidencia"));
                            parametros.put("acciones", txtAcciones.getText().toString());

                            if(txtFechaAtencion.getText().toString().isEmpty()){
                                parametros.put("fecha_atendido", "01/01/1980");
                            }else{
                                parametros.put("fecha_atendido", txtFechaAtencion.getText().toString());
                            }


                            //parametros.put("edad", txtEdad.getText().toString());
                            //parametros.put("peso", txtPeso.getText().toString());
                            //parametros.put("fecha_nacimiento", txtFecha.getText().toString());
                            //parametros.put("id_mascota", Integer.toString(id_mascota));

                            Log.d("MMM==>", getIntent().getStringExtra("id_evidencia"));
                            Log.d("MMM==>", txtAcciones.getText().toString());
                            Log.d("MMM==>", txtFechaAtencion.getText().toString());

                            return parametros;
                            //return super.getParams();
                        }
                    };

                    StringRequest peticion2 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //mostrarMensaje("Se actualizó correctamente");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("MMM==>", error.toString());

                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parametros2 = new HashMap<>();
                            parametros2.put("id_reporte", getIntent().getStringExtra("id_reporte"));
                            parametros2.put("id_estado", int_estado_reporte+"");
                            //parametros.put("edad", txtEdad.getText().toString());
                            //parametros.put("peso", txtPeso.getText().toString());
                            //parametros.put("fecha_nacimiento", txtFecha.getText().toString());
                            //parametros.put("id_mascota", Integer.toString(id_mascota));
                            return parametros2;
                            //return super.getParams();
                        }
                    };


                    RequestQueue cola = Volley.newRequestQueue(this);
                    cola.add(peticion);
                    cola.add(peticion2);
                }
            }
        });


    }
}