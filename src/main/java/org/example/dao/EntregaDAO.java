package org.example;

import org.example.model.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {

    public void guardar(Entrega entrega) {

        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, java.sql.Date.valueOf(entrega.getFecha()));
            ps.setTime(4, java.sql.Time.valueOf(entrega.getHora()));

            ps.executeUpdate();

            System.out.println("Entrega registrada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
