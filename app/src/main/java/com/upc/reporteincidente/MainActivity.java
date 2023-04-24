package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    TextView txtFullname;
    Button btnReportar, btnResolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFullname = findViewById(R.id.txtFullname);
        btnReportar = findViewById(R.id.btnReportar);
        btnResolver = findViewById(R.id.btnResolver);

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
    }
}