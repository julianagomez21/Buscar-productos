package com.example.model;

import java.io.IOException;
import org.json.JSONObject;

public class Producto {
    private String nombre;
    private double precioBase;
    private double precioTotal;
    private int edadRecomendada;
    private Proveedor proveedor;

    public Producto(String nombre, double precioBase, int edadRecomendada, Proveedor proveedor){
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.edadRecomendada = edadRecomendada;
        this.proveedor = proveedor;
        this.precioTotal = this.precioBase + this.proveedor.getPrecioEnvio();
    }

    public Producto(JSONObject json, Proveedor proveedor) throws IOException{
        this(json.getString("nombre"), json.getDouble("precio"), json.getInt("edad"), proveedor);
    }

    public int getEdadRecomendada() {
        return this.edadRecomendada;
    }

    public double getPrecioTotal() {
        return this.precioTotal;
    }

    @Override
    public String toString() {
        return String.format("%s - precio base: %.1f - precio de envío: %.1f - precio total: %.1f%n",
                this.nombre, this.precioBase, this.proveedor.getPrecioEnvio(), this.precioTotal);
    }

}
