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
import modelo.Pedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorio {
    
    public boolean insertar(Pedido pedido) {

        String sql = "INSERT INTO Pedido(idCliente, idEmpleado, fecha, estado) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setInt(1, pedido.getIdCliente());
            ps.setInt(2, pedido.getIdEmpleado());
            ps.setDate(3, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setString(4, pedido.getEstado());


            int filas = ps.executeUpdate();


            if(filas > 0){

                ResultSet rs = ps.getGeneratedKeys();

                if(rs.next()){

                    pedido.setIdPedido(rs.getInt(1));

                }

                return true;
            }


        } catch(SQLException e){
            System.out.println("Error al insertar pedido: " + e.getMessage());
        }
        return false;
    }

    public List<Pedido> listar() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedido";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setIdCliente(rs.getInt("idCliente"));
                pedido.setIdEmpleado(rs.getInt("idEmpleado"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setEstado(rs.getString("estado"));

                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    public Pedido buscarPorId(int idPedido) {

        String sql = "SELECT * FROM Pedido WHERE idPedido = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPedido);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Pedido pedido = new Pedido();

                    pedido.setIdPedido(rs.getInt("idPedido"));
                    pedido.setIdCliente(rs.getInt("idCliente"));
                    pedido.setIdEmpleado(rs.getInt("idEmpleado"));
                    pedido.setFecha(rs.getDate("fecha"));
                    pedido.setEstado(rs.getString("estado"));

                    return pedido;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pedido: " + e.getMessage());
        }

        return null;
    }

    public boolean actualizar(Pedido pedido) {

        String sql = "UPDATE Pedido SET idCliente=?, idEmpleado=?, fecha=?, estado=? WHERE idPedido=?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getIdCliente());
            ps.setInt(2, pedido.getIdEmpleado());
            ps.setDate(3, new java.sql.Date(pedido.getFecha().getTime()));
            ps.setString(4, pedido.getEstado());
            ps.setInt(5, pedido.getIdPedido());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idPedido) {

        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            String sqlDetalle = "DELETE FROM DetallePedido WHERE idPedido = ?";
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
            psDetalle.setInt(1, idPedido);
            psDetalle.executeUpdate();

            String sqlPedido = "DELETE FROM Pedido WHERE idPedido = ?";
            PreparedStatement psPedido = con.prepareStatement(sqlPedido);
            psPedido.setInt(1, idPedido);

            boolean eliminado = psPedido.executeUpdate() > 0;

            con.commit();

            return eliminado;

        } catch (SQLException e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
            }

            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;

        } finally {

            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException ex) {
            }

        }

    }
}