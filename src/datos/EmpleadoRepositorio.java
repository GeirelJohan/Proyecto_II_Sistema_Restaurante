/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Geirel
 */
import conexion.Conexion;
import modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepositorio {
    
    public boolean insertar(Empleado empleado) {

        String sql = "INSERT INTO Empleado(nombre, cargo) VALUES (?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    public List<Empleado> listar() {

        List<Empleado> lista = new ArrayList<>();

        String sql = "SELECT * FROM Empleado";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Empleado empleado = new Empleado();

                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setCargo(rs.getString("cargo"));

                lista.add(empleado);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }

        return lista;
    }

    public Empleado buscarPorId(int idEmpleado) {

        String sql = "SELECT * FROM Empleado WHERE idEmpleado = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Empleado empleado = new Empleado();

                    empleado.setIdEmpleado(rs.getInt("idEmpleado"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setCargo(rs.getString("cargo"));

                    return empleado;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar empleado: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizar(Empleado empleado) {

        String sql = "UPDATE Empleado SET nombre=?, cargo=? WHERE idEmpleado=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());
            ps.setInt(3, empleado.getIdEmpleado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idEmpleado) {

        String sql = "DELETE FROM Empleado WHERE idEmpleado=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

}