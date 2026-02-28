package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/speedfast_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "Cristi7.";

    public static Connection getConnection() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos 🎉");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar:");
            e.printStackTrace();
            return null;
        }
    }
}