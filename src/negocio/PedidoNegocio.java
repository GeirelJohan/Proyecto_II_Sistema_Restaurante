/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Bgamb
 */
import datos.PedidoRepositorio;
import java.util.List;
import modelo.Pedido;

public class PedidoNegocio {

    private PedidoRepositorio repositorio;

    public PedidoNegocio() {
        repositorio = new PedidoRepositorio();
    }

    private void validar(Pedido pedido) throws ValidacionException {

        if (pedido == null) {
            throw new ValidacionException("El pedido no puede ser nulo.");
        }

        if (pedido.getIdCliente() <= 0) {
            throw new ValidacionException("Debe seleccionar un cliente.");
        }

        if (pedido.getIdEmpleado() <= 0) {
            throw new ValidacionException("Debe seleccionar un empleado.");
        }

        if (pedido.getFecha() == null) {
            throw new ValidacionException("La fecha es obligatoria.");
        }

        if (pedido.getEstado() == null || pedido.getEstado().trim().isEmpty()) {
            throw new ValidacionException("El estado es obligatorio.");
        }

    }

    public boolean insertar(Pedido pedido) throws ValidacionException {

        validar(pedido);

        try {

            return repositorio.insertar(pedido);

        } catch (Exception e) {

            throw new ValidacionException("Error al insertar el pedido.");

        }

    }

    public List<Pedido> listar() {

        return repositorio.listar();

    }

    public Pedido buscarPorId(int idPedido) throws ValidacionException {

        if (idPedido <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        return repositorio.buscarPorId(idPedido);

    }

    public boolean actualizar(Pedido pedido) throws ValidacionException {

        if (pedido.getIdPedido() <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        validar(pedido);

        try {

            return repositorio.actualizar(pedido);

        } catch (Exception e) {

            throw new ValidacionException("Error al actualizar el pedido.");

        }

    }

    public boolean eliminar(int idPedido) throws ValidacionException {

        if (idPedido <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        try {

            return repositorio.eliminar(idPedido);

        } catch (Exception e) {

            throw new ValidacionException("Error al eliminar el pedido.");

        }

    }

}