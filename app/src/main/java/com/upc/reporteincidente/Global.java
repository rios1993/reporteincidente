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




}
