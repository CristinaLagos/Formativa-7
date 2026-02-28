package org.example.view;

import org.example.dao.EntregaDAO;
import org.example.dao.PedidoDAO;
import org.example.dao.RepartidorDAO;
import org.example.model.Entrega;
import org.example.model.Pedido;
import org.example.model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaEntrega extends JFrame {

    private JComboBox<Pedido> comboPedido;
    private JComboBox<Repartidor> comboRepartidor;
    private JTable tabla;
    private DefaultTableModel modelo;

    private EntregaDAO entregaDAO = new EntregaDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private RepartidorDAO repartidorDAO = new RepartidorDAO();

    public VentanaEntrega() {

        setTitle("Gestión de Entregas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3,2));

        panel.add(new JLabel("Pedido:"));
        comboPedido = new JComboBox<>();
        panel.add(comboPedido);

        panel.add(new JLabel("Repartidor:"));
        comboRepartidor = new JComboBox<>();
        panel.add(comboRepartidor);

        JButton btnRegistrar = new JButton("Registrar");
        panel.add(btnRegistrar);

        add(panel, BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Pedido");
        modelo.addColumn("Repartidor");

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarCombos();
        cargarTabla();

        btnRegistrar.addActionListener(e -> registrar());
    }

    private void cargarCombos() {

        for (Pedido p : pedidoDAO.readAll()) {
            comboPedido.addItem(p);
        }

        for (Repartidor r : repartidorDAO.readAll()) {
            comboRepartidor.addItem(r);
        }
    }

    private void registrar() {

        Pedido p = (Pedido) comboPedido.getSelectedItem();
        Repartidor r = (Repartidor) comboRepartidor.getSelectedItem();

        if (p == null || r == null) {
            JOptionPane.showMessageDialog(this, "Seleccione datos");
            return;
        }

        entregaDAO.create(new Entrega(p.getId(), r.getId()));
        cargarTabla();
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        for (Entrega e : entregaDAO.readAll()) {
            modelo.addRow(new Object[]{
                    e.getId(),
                    e.getIdPedido(),
                    e.getIdRepartidor()
            });
        }
    }
}