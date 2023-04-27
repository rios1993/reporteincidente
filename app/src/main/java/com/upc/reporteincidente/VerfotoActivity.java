package com.upc.reporteincidente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class VerfotoActivity extends AppCompatActivity {

    ImageView imgVerFoto;

    private String fullfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfoto);

        imgVerFoto = findViewById(R.id.imgVerFoto);

        fullfoto = ((Global) this.getApplication()).getGlbFotoVer();

        Log.d("FFF==>", fullfoto);

        Picasso.get()
                .load("https://upcmovilestf.zonaexperimental.com/images/"+fullfoto)
                .into(imgVerFoto);
    }
}