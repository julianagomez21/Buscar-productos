package com.example.model;

import org.json.JSONObject;

public class Proveedor {
    private String nombre;
    private double precioEnvio;

    public Proveedor(String nombre, double precioEnvio) {
        this.nombre = nombre;
        this.precioEnvio = precioEnvio;
    }

    public Proveedor(JSONObject json) {
        this(json.getString("nombre"), json.getDouble("precioEnvio"));
    }

    public String getNombre(){
        return this.nombre;
    }

    public double getPrecioEnvio(){
        return this.precioEnvio;
    }

}
