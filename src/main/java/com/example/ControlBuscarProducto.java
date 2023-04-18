package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.example.controllers.ControlProducto;
import com.example.model.Producto;

/**
 * Clase donde se piden los requisitos del producto al usuario y se realiza la búsqueda 
 * e impresión de aquellos que cumplen dichos requisitos.
 */
public class ControlBuscarProducto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n\u001B[34mIngresa la edad de la persona que recibirá tu regalo: \u001B[0m");
        int edad = sc.nextInt();

        System.out.print("\u001B[34mIngresa el precio máximo que puedes pagar por el regalo: \u001B[0m");
        double precioMaximo = sc.nextDouble();
        sc.close();

        buscarProductos(edad, precioMaximo);
    }

    /**
     * Método que se comunica con el control de productos para buscar los productos requeridos y mostrar el resultado
     * al usuario.
     * @param edad Edad de la persona que va a recibir el producto.
     * @param precioMaximo Precio máximo que se puede pagar por el producto.
     */
    private static void buscarProductos(int edad, double precioMaximo) {
        try {

            ControlProducto controlBusqueda = new ControlProducto();

            List<Producto> productos = controlBusqueda.buscarProductosPorEdadYPrecio(edad, precioMaximo);

            if (!productos.isEmpty()) {
                System.out.printf(
                        "\n\u001B[32mEstos son los resultados para edad %d años y precio máximo $%.1f: \u001B[0m\n",
                        edad, precioMaximo);
                for (Producto regalo : productos) {
                    System.out.print(regalo.toString());
                }

            } else {
                System.out.printf(
                        "\n\u001B[31m¡Lo sentimos! No han sido encontrados productos para la edad %d años y precio máximo $%.1f\u001B[0m",
                        edad, precioMaximo);
            }

        } catch (IOException e) {
            System.out.println("\u001B[31m¡Lo sentimos! Ha ocurrido un problema interno.\u001B[0m");
        }
    }
}
