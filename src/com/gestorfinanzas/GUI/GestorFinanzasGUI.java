package com.gestorfinanzas.GUI;

import com.gestorfinanzas.models.Transaccion;
import com.gestorfinanzas.services.GestorFinanzas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class GestorFinanzasGUI {
    private GestorFinanzas gestor;
    private JFrame frame;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField txtDescripcion, txtMonto, txtCategoria;
    private JButton btnAgregar, btnEliminar, btnFiltrar;

    public GestorFinanzasGUI() {
        gestor = new GestorFinanzas();
        frame = new JFrame("Gestor de Finanzas Personales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel de entrada
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(4, 2));

        panelSuperior.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelSuperior.add(txtDescripcion);

        panelSuperior.add(new JLabel("Monto:"));
        txtMonto = new JTextField();
        panelSuperior.add(txtMonto);

        panelSuperior.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panelSuperior.add(txtCategoria);

        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(this::agregarTransaccion);
        panelSuperior.add(btnAgregar);

        // BOTÓN ELIMINAR
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::eliminarTransaccion);
        panelSuperior.add(btnEliminar); // 💡 Aquí lo agregamos al panel

        frame.add(panelSuperior, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"Fecha", "Descripción", "Categoría", "Monto"}, 0);
        tabla = new JTable(modeloTabla);
        frame.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Panel de filtro
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        JTextField txtFiltroCategoria = new JTextField(10);
        panelInferior.add(new JLabel("Filtrar por categoría:"));
        panelInferior.add(txtFiltroCategoria);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> filtrarPorCategoria(txtFiltroCategoria.getText()));
        panelInferior.add(btnFiltrar);

        frame.add(panelInferior, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Método para agregar transacción
    private void agregarTransaccion(ActionEvent e) {
        String descripcion = txtDescripcion.getText();
        double monto = Double.parseDouble(txtMonto.getText());
        String categoria = txtCategoria.getText();
        LocalDate fecha = LocalDate.now();

        Transaccion t = new Transaccion(descripcion, monto, fecha, categoria);
        gestor.agregarTransaccion(t);

        modeloTabla.addRow(new Object[]{fecha, descripcion, categoria, monto});
    }

    // Método para eliminar transacción
    private void eliminarTransaccion(ActionEvent e) {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow != -1) {
            String descripcion = (String) modeloTabla.getValueAt(selectedRow, 1);
            String categoria = (String) modeloTabla.getValueAt(selectedRow, 2);
            double monto = (double) modeloTabla.getValueAt(selectedRow, 3);

            gestor.eliminarTransaccion(descripcion, categoria, monto);
            modeloTabla.removeRow(selectedRow);
        }
    }

    // Método para filtrar por categoría
    private void filtrarPorCategoria(String categoria) {
        modeloTabla.setRowCount(0);
        for (Transaccion t : gestor.filtrarPorCategoria(categoria)) {
            modeloTabla.addRow(new Object[]{t.getFecha(), t.getDescripcion(), t.getCategoria(), t.getMonto()});
        }
    }

    public static void main(String[] args) {
        new GestorFinanzasGUI();
    }
}
