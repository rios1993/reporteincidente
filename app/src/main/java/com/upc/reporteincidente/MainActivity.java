package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    TextView txtFullname;
    Button btnReportar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFullname = findViewById(R.id.txtFullname);
        btnReportar = findViewById(R.id.btnReportar);
        Intent intent = getIntent();

        String usuario = intent.getStringExtra("usuario");
        String fullname = intent.getStringExtra("fullname");
        String privilegio = intent.getStringExtra("privilegio");

        txtFullname.setText(fullname);

        btnReportar.setOnClickListener(view ->
        {
            Intent intentP = new Intent(this, PeligroActivity.class);
            startActivity(intentP);
        });
    }
}