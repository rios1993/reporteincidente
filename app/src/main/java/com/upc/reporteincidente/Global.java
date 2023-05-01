package com.upc.reporteincidente;

import android.app.Application;

public class Global extends Application {

    private String glbUsername;
    private String glbFullName;

    public String getGlbFullName() {
        return glbFullName;
    }

    public void setGlbFullName(String glbFullName) {
        this.glbFullName = glbFullName;
    }

    private String glbPrivilegio;

    public String getGlbFotoReporte() {
        return glbFotoReporte;
    }

    public void setGlbFotoReporte(String glbFotoReporte) {
        this.glbFotoReporte = glbFotoReporte;
    }

    private String glbFotoReporte;

    public String getGlbUsername() {
        return glbUsername;
    }

    public void setGlbUsername(String glbUsername) {
        this.glbUsername = glbUsername;
    }

    public String getGlbPrivilegio() {
        return glbPrivilegio;
    }

    public void setGlbPrivilegio(String glbPrivilegio) {
        this.glbPrivilegio = glbPrivilegio;
    }

    public Double getGlbLatitud() {
        return glbLatitud;
    }

    public void setGlbLatitud(Double glbLatitud) {
        this.glbLatitud = glbLatitud;
    }

    public Double getGlbLongitud() {
        return glbLongitud;
    }

    public void setGlbLongitud(Double glbLongitud) {
        this.glbLongitud = glbLongitud;
    }

    private Double glbLatitud, glbLongitud;

    public String getGlbFotoVer() {
        return glbFotoVer;
    }

    public void setGlbFotoVer(String glbFotoVer) {
        this.glbFotoVer = glbFotoVer;
    }

    private String glbFotoVer;

    public String getGlbFotoVerEvidencia() {
        return glbFotoVerEvidencia;
    }

    public void setGlbFotoVerEvidencia(String glbFotoVerEvidencia) {
        this.glbFotoVerEvidencia = glbFotoVerEvidencia;
    }

    private String glbFotoVerEvidencia;




}
