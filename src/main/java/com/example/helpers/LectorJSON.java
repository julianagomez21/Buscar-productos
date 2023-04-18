package com.example.helpers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase encargada de leer archivos tipo JSON.
 */
public class LectorJSON {
    
    /**
     * Método encargado de leer un archivo JSON local. 
     * @param ruta Corresponde a la ruta o dirección del archivo en la memoria local.
     * @return El contenido del JSON en una cadena de texto.
     * @throws IOException En caso de que la ruta sea errónea o el archivo no exista, se lanza una 
     * excepción IOException.
     */
    public String leerJSON(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        return Files.readString(path, StandardCharsets.UTF_8);
    };
}
