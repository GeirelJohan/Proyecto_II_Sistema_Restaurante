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
import modelo.DetallePedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoRepositorio {
    
    public boolean insertar(DetallePedido detalle) {

        String sql = "INSERT INTO DetallePedido(idPedido, idProducto, cantidad, precio) VALUES (?, ?, ?, ?)";

        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());

            return ps.executeUpdate() > 0;

        } catch(SQLException e) {
            System.out.println("Error al insertar detalle: " + e.getMessage());
            return false;
        }
    }

    public List<DetallePedido> listar(){

        List<DetallePedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM DetallePedido";

        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                DetallePedido detalle = new DetallePedido();

                detalle.setIdDetalle(rs.getInt("idDetalle"));
                detalle.setIdPedido(rs.getInt("idPedido"));
                detalle.setIdProducto(rs.getInt("idProducto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio"));

                lista.add(detalle);
            }
        } catch(SQLException e){

            System.out.println("Error al listar detalles: " + e.getMessage());

        }


        return lista;

    }



    public boolean eliminar(int idDetalle){

        String sql = "DELETE FROM DetallePedido WHERE idDetalle=?";


        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){


            ps.setInt(1, idDetalle);

            return ps.executeUpdate() > 0;


        } catch(SQLException e){

            System.out.println("Error al eliminar detalle: " + e.getMessage());
            return false;
        }
    }
    public List<Object[]> listarDetalleCompleto() {

        List<Object[]> lista = new ArrayList<>();

        String sql =
            "SELECT p.idPedido, p.idCliente, p.idEmpleado, " +
            "pr.nombre AS producto, d.cantidad, d.precio, p.estado " +
            "FROM Pedido p " +
            "INNER JOIN DetallePedido d ON p.idPedido = d.idPedido " +
            "INNER JOIN Producto pr ON d.idProducto = pr.idProducto " +
            "ORDER BY p.idPedido";

        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                lista.add(new Object[]{

                    rs.getInt("idPedido"),
                    rs.getInt("idCliente"),
                    rs.getInt("idEmpleado"),
                    rs.getString("producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio"),
                    rs.getString("estado")

                });

            }

        } catch(SQLException e){

            System.out.println(e.getMessage());

        }

        return lista;
    }
}
