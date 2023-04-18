package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.helpers.LectorJSON;
import com.example.model.Producto;
import com.example.model.Proveedor;

/**
 * Clase encargada del manejo de la lista de productos y de algunas funciones 
 * CRUD: crear y buscar.
 */
public class ControlProducto {
    private List<Producto> productos;

    public ControlProducto() throws IOException {
        productos = new ArrayList<>();
        this.llenarListaProductos();
    }

    /**
     * Método encargado de llenar la lista de productos a partir de un archivo JSON.
     * @throws IOException Este método utiliza un lector de JSON que podría lanzar esta excepción en 
     * caso de tener una ruta path errónea.
     */
    public void llenarListaProductos() throws IOException {

        ControlProveedor controlProveedor = new ControlProveedor();

        LectorJSON lector = new LectorJSON();
        String data = lector.leerJSON("./data/productos.json");
        JSONArray jsonProductos = new JSONArray(data);

        for (int i = 0; i < jsonProductos.length(); i++) {
            JSONObject jsonProducto = jsonProductos.getJSONObject(i);
            try {
                Proveedor proveedor = controlProveedor.buscarProveedorPorNombre(jsonProducto.getString("proveedor"));
                productos.add(new Producto(jsonProducto, proveedor));
            } catch (ProveedorNoExisteException e) {
                /*Si el proveedor no existe, no se agrega el producto a la lista de productos */
            } 
        }

    }

    /**
     * Método encargado de buscar productos según edad y precio.
     * @param edad Edad de la persona que recibe el producto.
     * @param precioMaximo Precio máximo que se puede pagar por el producto.
     * @return Lista de tipo Producto con aquellos que cumplen los requisitos.
     */
    public List<Producto> buscarProductosPorEdadYPrecio(int edad, double precioMaximo) {

        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getPrecioTotal() <= precioMaximo && producto.getEdadRecomendada() == edad) {
                productosEncontrados.add(producto);
            }
        }

        return productosEncontrados;
    }

}
