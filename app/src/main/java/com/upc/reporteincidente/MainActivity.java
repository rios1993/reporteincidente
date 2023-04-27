package com.upc.reporteincidente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {



    TextView txtFullname;
    Button btnReportar, btnResolver, btnSalirMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFullname = findViewById(R.id.txtFullname);
        btnReportar = findViewById(R.id.btnReportar);
        btnResolver = findViewById(R.id.btnResolver);
        btnSalirMain=findViewById(R.id.btnSalirMain);

        Intent intent = getIntent();

        String usuario = intent.getStringExtra("usuario");
        String fullname = intent.getStringExtra("fullname");
        String privilegio = intent.getStringExtra("privilegio");

        ((Global) this.getApplication()).setGlbUsername(usuario);
        ((Global) this.getApplication()).setGlbPrivilegio(privilegio);

        txtFullname.setText(fullname);

        btnReportar.setOnClickListener(view ->
        {
            Intent intentP = new Intent(this, ListarActivity.class);
            startActivity(intentP);
        });

        btnResolver.setOnClickListener(view -> {
            if(privilegio.toString().equals("2")) {
                Intent intentR = new Intent(this, ListarevidenciaActivity.class);
                startActivity(intentR);
            }else{
                Toast.makeText(this, "No tiene privilegios", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalirMain.setOnClickListener(view -> {
            AlertDialog.Builder ventana = new AlertDialog.Builder(this);
            ventana.setTitle("Salir");
            ventana.setMessage("Desea salir?");
            ventana.setPositiveButton("Aceptar",(dialogInterface, i) -> {
                //finish();
                //System.exit(0);

                Intent intentS = new Intent(this, LoginActivity.class);
                startActivity(intentS);
                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                this.finish();
            });
            ventana.setNegativeButton("NO",null);
            ventana.create().show();

        });
    }
}