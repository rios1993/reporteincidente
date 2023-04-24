package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PeligroverActivity extends AppCompatActivity {

    TextView txtIDReporte, txtDetalle, txtFoto, txtLatitud, txtLongitud, txtEstado, txtFechaHora, txtUsuario;

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
        }

}