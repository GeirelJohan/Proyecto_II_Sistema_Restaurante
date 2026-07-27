/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Bgamb
 */
import datos.DetallePedidoRepositorio;
import java.util.List;
import modelo.DetallePedido;

public class DetallePedidoNegocio {

    private DetallePedidoRepositorio repositorio;

    public DetallePedidoNegocio() {
        repositorio = new DetallePedidoRepositorio();
    }

    private void validar(DetallePedido detalle) throws ValidacionException {

        if (detalle == null) {
            throw new ValidacionException("El detalle del pedido no puede ser nulo.");
        }

        if (detalle.getIdPedido() <= 0) {
            throw new ValidacionException("Debe seleccionar un pedido.");
        }

        if (detalle.getIdProducto() <= 0) {
            throw new ValidacionException("Debe seleccionar un producto.");
        }

        if (detalle.getCantidad() <= 0) {
            throw new ValidacionException("La cantidad debe ser mayor que cero.");
        }

        if (detalle.getPrecio() <= 0) {
            throw new ValidacionException("El precio debe ser mayor que cero.");
        }

    }

    public boolean insertar(DetallePedido detalle) throws ValidacionException {

        validar(detalle);

        try {

            return repositorio.insertar(detalle);

        } catch (Exception e) {

            throw new ValidacionException("Error al insertar el detalle del pedido.");

        }

    }

    public List<DetallePedido> listar() {

        return repositorio.listar();

    }

    public DetallePedido buscarPorId(int idDetalle) throws ValidacionException {

        if (idDetalle <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        return repositorio.buscarPorId(idDetalle);

    }

    public boolean actualizar(DetallePedido detalle) throws ValidacionException {

        if (detalle.getIdDetalle() <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        validar(detalle);

        try {

            return repositorio.actualizar(detalle);

        } catch (Exception e) {

            throw new ValidacionException("Error al actualizar el detalle del pedido.");

        }

    }

    public boolean eliminar(int idDetalle) throws ValidacionException {

        if (idDetalle <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        try {

            return repositorio.eliminar(idDetalle);

        } catch (Exception e) {

            throw new ValidacionException("Error al eliminar el detalle del pedido.");

        }

    }

}