package com.gestorfinanzas.models;

import java.time.LocalDate;

public class Transaccion {
    private String descripcion;
    private double monto;
    private LocalDate fecha;
    private String categoria;

    public Transaccion(String descripcion, double monto, LocalDate fecha, String categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public String getDescripcion() { return descripcion; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
    public String getCategoria() { return categoria; }
}
