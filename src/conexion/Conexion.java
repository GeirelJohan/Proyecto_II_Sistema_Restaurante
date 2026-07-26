/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author Geirel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String URL =
        "jdbc:sqlserver://DESKTOP-5H54R8H:65456;"
        + "databaseName=RestauranteDB;"
        + "encrypt=true;"
        + "trustServerCertificate=true;";
    
    private static final String USUARIO = "sa";
    private static final String PASSWORD = "Geirel2025";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}