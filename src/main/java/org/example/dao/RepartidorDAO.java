package org.example;

import org.example.model.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public void create(Repartidor r) {
        String sql = "INSERT INTO repartidor (nombre, telefono) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNombre());
            stmt.setString(2, r.getTelefono());
            stmt.executeUpdate();

            System.out.println("Repartidor registrado correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Repartidor r = new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("telefono")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void update(Repartidor r) {
        String sql = "UPDATE repartidor SET nombre = ?, telefono = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, r.getNombre());
            stmt.setString(2, r.getTelefono());
            stmt.setInt(3, r.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}