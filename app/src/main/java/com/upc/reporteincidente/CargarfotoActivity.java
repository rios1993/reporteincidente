package com.upc.reporteincidente;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CargarfotoActivity extends AppCompatActivity {

    ImageView imgclickUpload;
    Button btnCargarFoto,btnSalirFoto;

    private String nombreFotoCargada;


    Bitmap bitmap;

    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargarfoto);

        String readExternalStorage = Manifest.permission.READ_EXTERNAL_STORAGE;

        asignarReferencias();


    }



    private void asignarReferencias() {
        imgclickUpload = findViewById(R.id.imgClickUpload);
        btnCargarFoto = findViewById(R.id.btnCargarFoto);
        btnSalirFoto = findViewById(R.id.btnSalirFoto);

        btnSalirFoto.setOnClickListener(view -> {
            //((Global) this.getApplication()).setGlbFotoReporte(nombreFotoCargada);
            this.finish();
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        imgclickUpload.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        imgclickUpload.setOnClickListener(view -> {

//            if ((ContextCompat.checkSelfPermission(getApplicationContext(),
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
//                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//                if ((ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE))) {
//
//                } else {
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                            REQUEST_PERMISSIONS);
//                }
//            } else {
//                Log.e("Else", "Else");
//                //showFileChooser();
//
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/*");
//                activityResultLauncher.launch(intent);
//            }

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);




        });

        btnCargarFoto.setOnClickListener(view -> {
            ByteArrayOutputStream byteArrayOutputStream;
            byteArrayOutputStream = new ByteArrayOutputStream();
            if(bitmap != null){
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                final String base64Image = Base64.encodeToString(bytes,Base64.DEFAULT);

                RequestQueue queue = Volley.newRequestQueue(this);
                String url ="https://upcmovilestf.zonaexperimental.com/images/upload.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //textView.setText("Response is: "+ response);

                                nombreFotoCargada = response;

                                ((Global) getApplication()).setGlbFotoReporte(nombreFotoCargada);

                                Toast.makeText(CargarfotoActivity.this, "Foto cargada", Toast.LENGTH_SHORT).show();
                                CargarfotoActivity.this.finish();
                                //Log.d("FFF==>", nombreFotoCargada);


                                //if(response.equals("success")){
                                //    Toast.makeText(CargarfotoActivity.this, "Imagen cargada", Toast.LENGTH_SHORT).show();
                                //}else {
                                //    Toast.makeText(CargarfotoActivity.this, "Carga fallida", Toast.LENGTH_SHORT).show();
                                //}
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("That didn't work!");
                        Toast.makeText(CargarfotoActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("image", base64Image);
                        return paramV;
                    }
                };
                queue.add(stringRequest);


            }else{
                Toast.makeText(this, "Seleccione una imagen", Toast.LENGTH_SHORT).show();
            }
        });



    }
}