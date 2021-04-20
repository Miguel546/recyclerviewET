package com.luismiguel.recyclerviewet;

import androidx.annotation.NonNull;

public class PalletTempModel implements Cloneable {

    public String numePallet;

    public String temperatura;

    public String nombCortoCalibre;

    public int numePosEstiba;

    public int rutaPallet;

    public String getNumePallet() {
        return numePallet;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getNombCortoCalibre() {
        return nombCortoCalibre;
    }

    public void setNombCortoCalibre(String nombCortoCalibre) {
        this.nombCortoCalibre = nombCortoCalibre;
    }

    public int getRutaPallet() {
        return rutaPallet;
    }

    public int getNumePosEstiba() {
        return numePosEstiba;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
