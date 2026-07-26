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

public class PruebaConexion {
    
    public static void main(String[] args) {

        try (Connection con = Conexion.getConexion()) {
            System.out.println("Conexion exitosa a SQL Server.");
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }

    }

}