package com.upc.reporteincidente;

public class Reportes {
    private Integer id_reporte;
    private String detalle;
    private String foto;
    private Double latitud;
    private Double longitud;
    //private Integer id_estado;
    private String descripcion;
    private Integer id_evidencia;
    private String username;
    private String fecha_hora_creacion;
    private String acciones;
    private String foto_evidencia;
    private String fecha_enproceso;
    private String fecha_atendido;
    private String username_evidencia;

    public Reportes(Integer id_reporte, String detalle, String foto, Double latitud, Double longitud, String descripcion, Integer id_evidencia, String username, String fecha_hora_creacion, String acciones, String foto_evidencia, String fecha_enproceso, String fecha_atendido, String username_evidencia) {
        this.id_reporte = id_reporte;
        this.detalle = detalle;
        this.foto = foto;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.id_evidencia = id_evidencia;
        this.username = username;
        this.fecha_hora_creacion = fecha_hora_creacion;
        this.acciones = acciones;
        this.foto_evidencia = foto_evidencia;
        this.fecha_enproceso = fecha_enproceso;
        this.fecha_atendido = fecha_atendido;
        this.username_evidencia = username_evidencia;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getFoto_evidencia() {
        return foto_evidencia;
    }

    public void setFoto_evidencia(String foto_evidencia) {
        this.foto_evidencia = foto_evidencia;
    }

    public String getFecha_enproceso() {
        return fecha_enproceso;
    }

    public void setFecha_enproceso(String fecha_enproceso) {
        this.fecha_enproceso = fecha_enproceso;
    }

    public String getFecha_atendido() {
        return fecha_atendido;
    }

    public void setFecha_atendido(String fecha_atendido) {
        this.fecha_atendido = fecha_atendido;
    }

    public String getUsername_evidencia() {
        return username_evidencia;
    }

    public void setUsername_evidencia(String username_evidencia) {
        this.username_evidencia = username_evidencia;
    }
}
