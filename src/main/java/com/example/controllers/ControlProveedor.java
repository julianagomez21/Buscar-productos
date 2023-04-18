package com.example.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.helpers.LectorJSON;
import com.example.model.Proveedor;

/**
 * Clase encargada del manejo de la lista de proveedores y de algunas funciones 
 * CRUD: crear y buscar.
 */
public class ControlProveedor {
    private List<Proveedor> proveedores;

    public ControlProveedor() throws IOException {
        proveedores = new ArrayList<>();
        this.llenarListaProveedores();
    }

    /**
     * Método encargado de llenar la lista de proveedores a partir de un archivo JSON.
     * @throws IOException Este método utiliza un lector de JSON que podría lanzar esta excepción en 
     * caso de tener una ruta path errónea.
     */
    public void llenarListaProveedores() throws IOException {

        LectorJSON lector = new LectorJSON();
        String data = lector.leerJSON("./data/proveedores.json");
        JSONArray jsonProveedores = new JSONArray(data);

        for (int i = 0; i < jsonProveedores.length(); i++) {
            JSONObject jsonProveedor = jsonProveedores.getJSONObject(i);
            proveedores.add(new Proveedor(jsonProveedor));               
        }
    }

    /**Método encargado de buscar un proveedor según su nombre.
     * @param nombre Corresponde al nombre del proveedor.
     * @return Un objeto Proveedor que coincida con el nombre solicitado. 
     * @throws ProveedorNoExisteException En caso de que no exista un proveedor con ese nombre,
     * se lanza una excepción.
     */
    public Proveedor buscarProveedorPorNombre(String nombre) throws ProveedorNoExisteException{

        for (Proveedor proveedor : proveedores) {
            if(proveedor.getNombre().equals(nombre)) {
                return proveedor;
            }
        }

        throw new ProveedorNoExisteException();
    } 
    
}
