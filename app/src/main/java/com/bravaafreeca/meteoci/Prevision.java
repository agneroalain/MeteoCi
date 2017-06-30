package com.bravaafreeca.meteoci;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created by nnbbbbbbbnn on 28/06/2017.
 */

public class Prevision  {
    private String libelle;
    private double temp_max;
    private double temp_min;
    private String api;
    private int jour;
    private int clouds;
    private String icon;

    public Prevision(){

    }

    public String getLibelle() {
        return libelle;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public int getClouds() {
        return clouds;
    }

    public String getApi() {
        return api;
    }

    public int getJour() {
        return jour;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
