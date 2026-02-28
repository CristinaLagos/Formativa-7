package org.example;

import org.example.model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTable tabla;
    private DefaultTableModel modelo;

    private RepartidorDAO dao = new RepartidorDAO();

    public VentanaPrincipal() {

        setTitle("Gestión de Repartidores");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(3, 2));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        JButton btnRegistrar = new JButton("Registrar");
        panelFormulario.add(btnRegistrar);

        add(panelFormulario, BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarTabla();

        btnRegistrar.addActionListener(e -> registrarRepartidor());
    }

    private void registrarRepartidor() {

        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        dao.create(new Repartidor(nombre, telefono));

        txtNombre.setText("");
        txtTelefono.setText("");

        cargarTabla();

        JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente");
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Repartidor> lista = dao.readAll();

        for (Repartidor r : lista) {
            modelo.addRow(new Object[]{
                    r.getId(),
                    r.getNombre(),
                    r.getTelefono()
            });
        }
    }
}