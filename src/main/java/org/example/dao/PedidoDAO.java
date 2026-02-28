package org.example;

import org.example.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void guardar(Pedido pedido) {

        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado());

            ps.executeUpdate();
            System.out.println("Pedido guardado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarTodos() {

        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        rs.getString("estado")
                );
                lista.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
