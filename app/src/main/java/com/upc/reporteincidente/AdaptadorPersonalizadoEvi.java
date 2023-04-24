package com.upc.reporteincidente;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoEvi extends RecyclerView.Adapter<AdaptadorPersonalizadoEvi.MiViewHolder> {

    private Context context;
    private List<Reporte> listaReportes = new ArrayList<>();


    public AdaptadorPersonalizadoEvi(Context context, List<Reporte> listaReportes){
        this.context = context;
        this.listaReportes = listaReportes;
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
        holder.filaEstadoEvi.setText(listaReportes.get(position).getId_estado()+"");
        holder.filaDetalleEvi.setText(listaReportes.get(position).getDetalle()+"");


        holder.filaVerEvi.setOnClickListener(view -> {
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
        TextView filaReporteEvi, filaDetalleEvi, filaUsuarioEvi, filaFechayHoraEvi, filaEstadoEvi;
        ImageButton filaVerEvi;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaReporteEvi = itemView.findViewById(R.id.filaReporteEvi);
            filaUsuarioEvi = itemView.findViewById(R.id.filaUsuarioEvi);
            filaDetalleEvi = itemView.findViewById(R.id.filaDetalleEvi);
            filaFechayHoraEvi = itemView.findViewById(R.id.filaFechayHoraEvi);
            filaEstadoEvi = itemView.findViewById(R.id.filaEstadoEvi);
            filaVerEvi = itemView.findViewById(R.id.filaVerEvi);

        }
    }
}