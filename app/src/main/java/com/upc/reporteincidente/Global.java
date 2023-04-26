package com.upc.reporteincidente;

import android.app.Application;

public class Global extends Application {

    private String glbUsername;
    private String glbPrivilegio;

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




}
