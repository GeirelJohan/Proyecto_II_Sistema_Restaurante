/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Bgamb
 */
import datos.EmpleadoRepositorio;
import java.util.List;
import modelo.Empleado;

public class EmpleadoNegocio {

    private EmpleadoRepositorio repositorio;

    public EmpleadoNegocio() {
        repositorio = new EmpleadoRepositorio();
    }

    private void validar(Empleado empleado) throws ValidacionException {

        if (empleado == null) {
            throw new ValidacionException("El empleado no puede ser nulo.");
        }

        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre es obligatorio.");
        }

        if (empleado.getCargo() == null || empleado.getCargo().trim().isEmpty()) {
            throw new ValidacionException("El cargo es obligatorio.");
        }

    }

    public boolean insertar(Empleado empleado) throws ValidacionException {

        validar(empleado);

        try {

            return repositorio.insertar(empleado);

        } catch (Exception e) {

            throw new ValidacionException("Error al insertar el empleado.");

        }

    }

    public List<Empleado> listar() {

        return repositorio.listar();

    }

    public Empleado buscarPorId(int idEmpleado) throws ValidacionException {

        if (idEmpleado <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        return repositorio.buscarPorId(idEmpleado);

    }

    public boolean actualizar(Empleado empleado) throws ValidacionException {

        if (empleado.getIdEmpleado() <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        validar(empleado);

        try {

            return repositorio.actualizar(empleado);

        } catch (Exception e) {

            throw new ValidacionException("Error al actualizar el empleado.");

        }

    }

    public boolean eliminar(int idEmpleado) throws ValidacionException {

        if (idEmpleado <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        try {

            return repositorio.eliminar(idEmpleado);

        } catch (Exception e) {

            throw new ValidacionException("Error al eliminar el empleado.");

        }

    }

}