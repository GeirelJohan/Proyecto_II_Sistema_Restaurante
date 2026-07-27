/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Bgamb
 */
import datos.ProductoRepositorio;
import java.util.List;
import modelo.Producto;

public class ProductoNegocio {

    private ProductoRepositorio repositorio;

    public ProductoNegocio() {
        repositorio = new ProductoRepositorio();
    }

    private void validar(Producto producto) throws ValidacionException {

        if (producto == null) {
            throw new ValidacionException("El producto no puede ser nulo.");
        }

        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new ValidacionException("El nombre es obligatorio.");
        }

        if (producto.getPrecio() <= 0) {
            throw new ValidacionException("El precio debe ser mayor que cero.");
        }

        if (producto.getStock() < 0) {
            throw new ValidacionException("El stock no puede ser negativo.");
        }

    }

    public boolean insertar(Producto producto) throws ValidacionException {

        validar(producto);

        try {

            return repositorio.insertar(producto);

        } catch (Exception e) {

            throw new ValidacionException("Error al insertar el producto.");

        }

    }

    public List<Producto> listar() {

        return repositorio.listar();

    }

    public Producto buscarPorId(int idProducto) throws ValidacionException {

        if (idProducto <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        return repositorio.buscarPorId(idProducto);

    }

    public boolean actualizar(Producto producto) throws ValidacionException {

        if (producto.getIdProducto() <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        validar(producto);

        try {

            return repositorio.actualizar(producto);

        } catch (Exception e) {

            throw new ValidacionException("Error al actualizar el producto.");

        }

    }

    public boolean eliminar(int idProducto) throws ValidacionException {

        if (idProducto <= 0) {
            throw new ValidacionException("ID inválido.");
        }

        try {

            return repositorio.eliminar(idProducto);

        } catch (Exception e) {

            throw new ValidacionException("Error al eliminar el producto.");

        }

    }

}