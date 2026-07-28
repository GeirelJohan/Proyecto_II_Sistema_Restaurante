/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Bgamb
 */
import datos.ClienteRepositorio;
import java.util.List;
import modelo.Cliente;

public class ClienteNegocio {

    private ClienteRepositorio repositorio;

    public ClienteNegocio() {
        repositorio = new ClienteRepositorio();
    }

    private void validar(Cliente cliente) throws ValidacionException {

        if (cliente == null) {
            throw new ValidacionException("El cliente no puede ser nulo.");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre es obligatorio.");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new ValidacionException("El teléfono es obligatorio.");
        }

        if (cliente.getCorreo() == null || cliente.getCorreo().trim().isEmpty()) {
            throw new ValidacionException("El correo es obligatorio.");
        }

        if (!cliente.getCorreo().contains("@")) {
            throw new ValidacionException("Correo electrónico inválido.");
        }
    }

    public boolean insertar(Cliente cliente) throws ValidacionException {

        validar(cliente);

        try {

            return repositorio.insertar(cliente);

        } catch (Exception e) {

            throw new ValidacionException("Error al insertar el cliente.");

        }

    }

    public List<Cliente> listar() {

        return repositorio.listar();

    }

    public Cliente buscarPorNombre(String nombre) throws ValidacionException {

        if(nombre == null || nombre.trim().isEmpty()) {
        throw new ValidacionException("Ingrese un nombre.");

       }

       return repositorio.buscarPorNombre(nombre);
   }
    public boolean actualizar(Cliente cliente) throws ValidacionException {

        if (cliente.getIdCliente() <= 0) {

            throw new ValidacionException("ID inválido.");

        }

        validar(cliente);

        try {

            return repositorio.actualizar(cliente);

        } catch (Exception e) {

            throw new ValidacionException("Error al actualizar el cliente.");

        }

    }

    public boolean eliminar(int idCliente) throws ValidacionException {

        if (idCliente <= 0) {

            throw new ValidacionException("ID inválido.");

        }

        try {

            return repositorio.eliminar(idCliente);

        } catch (Exception e) {

            throw new ValidacionException("Error al eliminar el cliente.");

        }

    }

}