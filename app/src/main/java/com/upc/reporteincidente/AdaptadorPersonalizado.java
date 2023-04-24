package com.upc.reporteincidente;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder> {

    private Context context;
    private List<Reporte> listaReportes = new ArrayList<>();


    public AdaptadorPersonalizado(Context context, List<Reporte> listaReportes){
        this.context = context;
        this.listaReportes = listaReportes;
    }


    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.fila, parent,false);
        return new MiViewHolder(vista);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {

        holder.filaReporte.setText(listaReportes.get(position).getId_reporte()+"");
        holder.filaUsuario.setText(listaReportes.get(position).getUsername()+"");
        holder.filaFechayHora.setText(listaReportes.get(position).getFecha_hora_creacion()+"");
        holder.filaEstado.setText(listaReportes.get(position).getId_estado()+"");
        holder.filaDetalle.setText(listaReportes.get(position).getDetalle()+"");


        holder.filaVer.setOnClickListener(view -> {
            Intent intent = new Intent(context,PeligroverActivity.class);
            intent.putExtra("id_reporte", listaReportes.get(position).getId_reporte()+"");
            intent.putExtra("detalle", listaReportes.get(position).getDetalle()+"");
            intent.putExtra("usuario", listaReportes.get(position).getUsername()+"");
            intent.putExtra("fecha_hora_creacion", listaReportes.get(position).getFecha_hora_creacion()+"");
            intent.putExtra("estado", listaReportes.get(position).getId_estado()+"");
            intent.putExtra("latitud",listaReportes.get(position).getLatitud()+"");
            intent.putExtra("longitud",listaReportes.get(position).getLongitud()+"");
            intent.putExtra("foto",listaReportes.get(position).getFoto()+"");

            context.startActivity(intent);
        });


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
            Intent intent = new Intent(context,ListarActivity.class);
            context.startActivity(intent);
        });
        ventana.create().show();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView filaReporte, filaDetalle, filaUsuario, filaFechayHora, filaEstado;
        ImageButton filaVer;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaReporte = itemView.findViewById(R.id.filaReporte);
            filaUsuario = itemView.findViewById(R.id.filaUsuario);
            filaDetalle = itemView.findViewById(R.id.filaDetalle);
            filaFechayHora = itemView.findViewById(R.id.filaFechayHora);
            filaEstado = itemView.findViewById(R.id.filaEstado);
            filaVer = itemView.findViewById(R.id.filaVer);

        }
    }
}
