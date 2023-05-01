package com.upc.reporteincidente;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoEvi extends RecyclerView.Adapter<AdaptadorPersonalizadoEvi.MiViewHolder> {

    private Context context;
    private List<Reportes> listaReportes = new ArrayList<>();

    private String username,fullname,privilegio;

    public AdaptadorPersonalizadoEvi(Context context, List<Reportes> listaReportes,String username,String fullname,String privilegio){
        this.context = context;
        this.listaReportes = listaReportes;
        this.username = username;
        this.fullname = fullname;
        this.privilegio = privilegio;
    }


    @NonNull
    @Override
    public AdaptadorPersonalizadoEvi.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.filaevidencia, parent,false);
        return new MiViewHolder(vista);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizadoEvi.MiViewHolder holder, int position) {

        holder.filaReporteEvi.setText(listaReportes.get(position).getId_reporte()+"");
        holder.filaUsuarioEvi.setText(listaReportes.get(position).getUsername()+"");
        holder.filaFechayHoraEvi.setText(listaReportes.get(position).getFecha_hora_creacion()+"");
        holder.filaEstadoEvi.setText(listaReportes.get(position).getDescripcion()+"");
        holder.filaDetalleEvi.setText(listaReportes.get(position).getDetalle()+"");


        holder.filaVerEvi.setOnClickListener(view -> {
            Intent intent = new Intent(context,EvidenciaActivity.class);
            intent.putExtra("id_reporte", listaReportes.get(position).getId_reporte()+"");
            intent.putExtra("detalle", listaReportes.get(position).getDetalle()+"");
            intent.putExtra("usuario", listaReportes.get(position).getUsername()+"");
            intent.putExtra("fecha_hora_creacion", listaReportes.get(position).getFecha_hora_creacion()+"");
            intent.putExtra("estado", listaReportes.get(position).getDescripcion()+"");
            intent.putExtra("latitud",listaReportes.get(position).getLatitud()+"");
            intent.putExtra("longitud",listaReportes.get(position).getLongitud()+"");
            intent.putExtra("foto",listaReportes.get(position).getFoto()+"");
            intent.putExtra("acciones",listaReportes.get(position).getAcciones()+"");
            intent.putExtra("foto_evidencia",listaReportes.get(position).getFoto_evidencia()+"");
            intent.putExtra("fecha_enproceso",listaReportes.get(position).getFecha_enproceso()+"");
            intent.putExtra("fecha_atendido",listaReportes.get(position).getFecha_atendido()+"");
            intent.putExtra("username_evidencia",listaReportes.get(position).getUsername_evidencia()+"");
            intent.putExtra("id_evidencia",listaReportes.get(position).getId_evidencia()+"");

            context.startActivity(intent);
        });

        holder.filaEliminarEvi.setOnClickListener(view -> {
            confirmar(position);
        });
    }

    private void confirmar(int position) {
        int idEliminar = listaReportes.get(position).getId_reporte();
        String estado = listaReportes.get(position).getDescripcion();

        if(estado.equals("Reportado"))
        {
            AlertDialog.Builder ventana = new AlertDialog.Builder(context);
            ventana.setTitle("Eliminar");
            ventana.setMessage("¿Desea eliminar el reporte?");
            ventana.setPositiveButton("Aceptar",(dialogInterface, i) ->
            {
                String url = "https://upcmovilestf.zonaexperimental.com/index.php/peligro"+idEliminar;
                StringRequest peticion = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mostrarMensaje("Reporte eliminado correctamente.");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("==>",error.toString());
                    }
                });

                RequestQueue cola = Volley.newRequestQueue(context);
                cola.add(peticion);
            });
            ventana.setNegativeButton("NO",null);
            ventana.create().show();
        }
        else
        {
            mostrarMensaje("Solo se pueden eliminar reportes en estado Reportado.");
        }


    }


    @Override
    public int getItemCount() {
        return listaReportes.size();
        //return 0;
    }

    private void mostrarMensaje(String mensaje){
        AlertDialog.Builder ventana = new AlertDialog.Builder(context);
        ventana.setTitle("Mensaje Informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar",(dialogInterface, i) -> {
            Intent intent = new Intent(context,ListarevidenciaActivity.class);
            intent.putExtra("usuario", username);
            intent.putExtra("fullname", fullname);
            intent.putExtra("privilegio", privilegio);
            context.startActivity(intent);
        });
        ventana.create().show();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView filaReporteEvi, filaDetalleEvi, filaUsuarioEvi, filaFechayHoraEvi, filaEstadoEvi;
        ImageButton filaVerEvi, filaEliminarEvi;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaReporteEvi = itemView.findViewById(R.id.filaReporteEvi);
            filaUsuarioEvi = itemView.findViewById(R.id.filaUsuarioEvi);
            filaDetalleEvi = itemView.findViewById(R.id.filaDetalleEvi);
            filaFechayHoraEvi = itemView.findViewById(R.id.filaFechayHoraEvi);
            filaEstadoEvi = itemView.findViewById(R.id.filaEstadoEvi);
            filaVerEvi = itemView.findViewById(R.id.filaVerEvi);
            filaEliminarEvi = itemView.findViewById(R.id.filaEliminarEvi);
        }
    }
}
