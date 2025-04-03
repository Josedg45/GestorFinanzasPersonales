package com.gestorfinanzas.services;

import com.gestorfinanzas.models.Transaccion;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorFinanzas {
    private List<Transaccion> transacciones;

    public GestorFinanzas() {
        transacciones = new ArrayList<>();
    }

    public void agregarTransaccion(Transaccion t) {
        transacciones.add(t);
    }

    public void eliminarTransaccion(String descripcion, String categoria, double monto) {
        transacciones.removeIf(t ->
                t.getDescripcion().equals(descripcion) &&
                        t.getCategoria().equals(categoria) &&
                        t.getMonto() == monto
        );
    }

    public List<Transaccion> filtrarPorCategoria(String categoria) {
        if (categoria.isEmpty()) {
            return new ArrayList<>(transacciones); // Devuelve todo si el filtro está vacío
        }
        return transacciones.stream()
                .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public double calcularBalance() {
        return transacciones.stream().mapToDouble(Transaccion::getMonto).sum();
    }
}
