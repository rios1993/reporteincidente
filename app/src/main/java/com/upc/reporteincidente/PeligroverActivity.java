package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class PeligroverActivity extends AppCompatActivity {

    TextView txtIDReporte, txtDetalle, txtFoto, txtLatitud, txtLongitud, txtEstado, txtFechaHora, txtUsuario, txtAcciones, txtFoto_evidencia, txtFecha_enproceso, txtFecha_Atendido, txtUsername_evidencia;

    Button btnVerMapa, btnRegresarVer, btnFotoVer, btnFotoVerEvidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peligrover);

        asignarReferencias();

        cargarDatos();
    }

    private void asignarReferencias(){
        txtDetalle = findViewById(R.id.txtDetalleVer);
        txtFoto = findViewById(R.id.txtFotoVer);
        txtLatitud = findViewById(R.id.txtLatitudVer);
        txtLongitud = findViewById(R.id.txtLongitudVer);
        txtEstado = findViewById(R.id.txtEstadoVer);
        txtUsuario = findViewById(R.id.txtUsuarioVer);
        txtFechaHora = findViewById(R.id.txtFechaVer);
        txtIDReporte = findViewById(R.id.txtIdreporteVer);
        txtAcciones = findViewById(R.id.txtAccionesVer);
        txtFoto_evidencia = findViewById(R.id.txtFotoEvidencia);
        txtFecha_enproceso = findViewById(R.id.txtFechaProceso);
        txtFecha_Atendido=findViewById(R.id.txtFechaAtencion);
        txtUsername_evidencia=findViewById(R.id.txtUsuarioEvidencia);
        btnVerMapa = findViewById(R.id.btnVerMapa);
        btnRegresarVer = findViewById(R.id.btnRegresarVer);
        btnFotoVer = findViewById(R.id.btnVerFoto);
        btnFotoVerEvidencia=findViewById(R.id.btnFotoVerEvidencia);

        btnFotoVerEvidencia.setOnClickListener(view -> {
            Intent intent = new Intent(this, VerfotoActivity.class);
            intent.putExtra("verfotoreporte","NO");
            intent.putExtra("verfotoevidencia","SI");
            //Log.d("RRR1==>", "VER Reporte");
            startActivity(intent);

        });

        btnFotoVer.setOnClickListener(view -> {
            Intent intent = new Intent(this, VerfotoActivity.class);
            intent.putExtra("verfotoevidencia","NO");
            intent.putExtra("verfotoreporte","SI");
            //Log.d("EEE1==>", "VER Evidencia");
            startActivity(intent);

        });

        btnRegresarVer.setOnClickListener(view -> {
            this.finish();
        });

        btnVerMapa.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapaActivity.class);
            ((Global) this.getApplication()).setGlbLatitud(Double.parseDouble(getIntent().getStringExtra("latitud")));
            ((Global) this.getApplication()).setGlbLongitud(Double.parseDouble(getIntent().getStringExtra("longitud")));

            startActivity(intent);

        });

    }

    private void cargarDatos(){
        txtDetalle.setText(getIntent().getStringExtra("detalle"));
        txtFoto.setText(getIntent().getStringExtra("foto"));
        txtLatitud.setText(getIntent().getStringExtra("latitud"));
        txtLongitud.setText(getIntent().getStringExtra("longitud"));
        txtEstado.setText(getIntent().getStringExtra("estado"));
        txtUsuario.setText(getIntent().getStringExtra("usuario"));
        txtFechaHora.setText(getIntent().getStringExtra("fecha_hora_creacion"));
        txtIDReporte.setText(getIntent().getStringExtra("id_reporte"));
        txtAcciones.setText(getIntent().getStringExtra("acciones"));
        txtFoto_evidencia.setText(getIntent().getStringExtra("foto_evidencia"));
        txtFecha_enproceso.setText(getIntent().getStringExtra("fecha_enproceso"));
        txtFecha_Atendido.setText(getIntent().getStringExtra("fecha_atendido"));
        txtUsername_evidencia.setText(getIntent().getStringExtra("username_evidencia"));

        ((Global) this.getApplication()).setGlbFotoVer(getIntent().getStringExtra("foto"));
        ((Global) this.getApplication()).setGlbFotoVerEvidencia(getIntent().getStringExtra("foto_evidencia"));

        //Log.d("PPP1==>", txtFoto.getText().toString());
        //Log.d("PPP2==>", txtFoto_evidencia.getText().toString());


        }

}