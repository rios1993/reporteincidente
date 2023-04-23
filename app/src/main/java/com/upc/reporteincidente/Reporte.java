package com.upc.reporteincidente;

import java.sql.Blob;
import java.util.Date;

public class Reporte {
    private Integer id_reporte;
    private String detalle;
    private String foto;
    private Double latitud;
    private Double longitud;
    private Integer id_estado;
    private Integer id_evidencia;
    private String username;
    private String fecha_hora_creacion;

    public Reporte(Integer id_reporte, String detalle, String foto, Double latitud, Double longitud, Integer id_estado, Integer id_evidencia, String username, String fecha_hora_creacion) {
        this.id_reporte = id_reporte;
        this.detalle = detalle;
        this.foto = foto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_estado = id_estado;
        this.id_evidencia = id_evidencia;
        this.username = username;
        this.fecha_hora_creacion = fecha_hora_creacion;
    }

    public Integer getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Integer id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_evidencia() {
        return id_evidencia;
    }

    public void setId_evidencia(Integer id_evidencia) {
        this.id_evidencia = id_evidencia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFecha_hora_creacion() {
        return fecha_hora_creacion;
    }

    public void setFecha_hora_creacion(String fecha_hora_creacion) {
        this.fecha_hora_creacion = fecha_hora_creacion;
    }
}
