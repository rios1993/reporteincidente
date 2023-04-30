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

    private String extraRep,extraEvi, fullfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verfoto);

        imgVerFoto = findViewById(R.id.imgVerFoto);

        extraRep = getIntent().getStringExtra("verfotoreporte");
        extraEvi = getIntent().getStringExtra("verfotoevidencia");

        if(extraRep.equals("SI")) {
            fullfoto = ((Global) this.getApplication()).getGlbFotoVer();
        } else if (extraEvi.equals("SI")) {
            fullfoto = ((Global) this.getApplication()).getGlbFotoVerEvidencia();
        }

        Log.d("FFF1==>", fullfoto);
        Log.d("FFF2==>", extraRep);

        Picasso.get()
                .load("https://upcmovilestf.zonaexperimental.com/images/"+fullfoto)
                .into(imgVerFoto);
    }
}