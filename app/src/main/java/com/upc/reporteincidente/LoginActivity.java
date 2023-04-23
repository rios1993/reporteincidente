package com.upc.reporteincidente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtPassword;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignarReferencias();
    }

    private void asignarReferencias(){
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(view -> {

            String txtUser = txtUsuario.getText().toString();

            String string_to_be_converted_to_MD5 = txtPassword.getText().toString();
            String txtPassEncriptado = md5(string_to_be_converted_to_MD5);
            //System.out.println(txtPassEncriptado);

            String criterio = txtUser + "/" + txtPassEncriptado;
            String url = "https://upcmovilestf.zonaexperimental.com/index.php/login/" + criterio;
            //String url = "https://upcmovilestf.zonaexperimental.com/index.php/productos";

            StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length()>0){
                            Toast.makeText(LoginActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(),MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }


                    }catch (JSONException e){
                        Log.d("==>", e.getMessage());

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("==>", error.getMessage());

                }
            }); //{
                //@Nullable
                //@Override
                //protected Map<String, String> getParams() throws AuthFailureError {
                //    Map<String, String> parametros = new HashMap<>();
                //    parametros.put("username", txtUser);
                //    parametros.put("password", txtPassEncriptado);
                //    return parametros;
                    //return super.getParams();
               // }
            //};
            RequestQueue cola = Volley.newRequestQueue(this);
            cola.add(peticion);

        });

    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}