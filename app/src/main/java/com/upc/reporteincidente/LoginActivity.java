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

    EditText txtUsuario, txtPassword, txtUsuario2, txtPassword2;
    Button btnIngresar, btnLogin2, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        asignarReferencias();

    }

    private void asignarReferencias(){
        txtUsuario2 = findViewById(R.id.txtUsuario2);
        txtPassword2 = findViewById(R.id.txtPassword2);
        //btnIngresar = findViewById(R.id.btnIngresar);
        btnLogin2 = findViewById(R.id.btnLogin2);
        btnSalir = findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });

        btnLogin2.setOnClickListener(view -> {

            if(validarDatos())
            {
                String txtUser = txtUsuario2.getText().toString();

                String string_to_be_converted_to_MD5 = txtPassword2.getText().toString();
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
                                JSONObject object = jsonArray.getJSONObject(0);
                                String globalUsuario = object.getString("username");
                                String globalFullName = object.getString("fullname");
                                String globalPrivilegio = object.getString("privilegio");


                                Toast.makeText(LoginActivity.this, "Inicio de sesión exitosa", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);


                                //Bundle bundle = new Bundle();

                                //bundle.putString("usuario", globalUsuario);
                                //bundle.putString("fullname",globalFullName);
                                //bundle.putString("privilegio",globalPrivilegio);

                                //intent.putExtras(bundle);

                                intent.putExtra("usuario",globalUsuario);
                                intent.putExtra("fullname",globalFullName);
                                intent.putExtra("privilegio",globalPrivilegio);

                                startActivity(intent);
                                finish();

                            }else{
                                Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
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
            }



        });

    }

    private boolean validarDatos()
    {
        String usuario = txtUsuario2.getText().toString();
        String contrasena = txtPassword2.getText().toString();

        boolean valida = true;

        if(usuario.equals("")){
            txtUsuario2.setError("Ingrese su usuario");
            valida = false;
        }

        if(contrasena.equals("")){
            txtPassword2.setError("Ingrese su contraseña");
            valida = false;
        }

        return valida;
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