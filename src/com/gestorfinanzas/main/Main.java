package com.gestorfinanzas.main;

import com.gestorfinanzas.models.Transaccion;
import com.gestorfinanzas.services.GestorFinanzas;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GestorFinanzas gestor = new GestorFinanzas();

        // Agregar algunas transacciones de prueba
        gestor.agregarTransaccion(new Transaccion("Sueldo", 1000, LocalDate.now(), "Ingreso"));
        gestor.agregarTransaccion(new Transaccion("Supermercado", -200, LocalDate.now(), "Gasto"));
        gestor.agregarTransaccion(new Transaccion("Cine", -50, LocalDate.now(), "Entretenimiento"));

        // Mostrar todas las transacciones
        System.out.println("Lista de transacciones:");
        for (Transaccion t : gestor.filtrarPorCategoria("")) {
            System.out.println(t);
        }

        // Mostrar balance total
        System.out.println("Balance total: $" + gestor.calcularBalance());
    }
}
