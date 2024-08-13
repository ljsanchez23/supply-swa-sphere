package com.swasphere.controlador;

import com.swasphere.modelo.Producto;
import com.swasphere.modelo.ProductoDAO;
import java.util.List;
import java.sql.SQLException;

/**
 * Controlador de productos, permite la comunicación entre la vista y el modelo.
 *
 * @autor ljsanchez23
 */
public class ProductoControlador {

    private ProductoDAO productoDAO;

    /**
     * Constructor de la clase ProductoControlador.
     *
     * @param productoDAO La instancia de ProductoDAO para interactuar con la
     * base de datos.
     */
    public ProductoControlador(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    /**
     * Agrega un nuevo producto.
     *
     * @param producto El producto a agregar.
     * @return true si el producto fue agregado exitosamente, false en caso
     * contrario.
     */
    public boolean agregarProducto(Producto producto) {
        return productoDAO.agregarProducto(producto);
    }

    /**
     * Elimina un producto por su nombre.
     *
     * @param nombre El nombre del producto a eliminar.
     * @return true si el producto fue eliminado exitosamente, false en caso
     * contrario.
     */
    public boolean eliminarProducto(String nombre) {
        return productoDAO.eliminarProducto(nombre);
    }

    /**
     * Muestra el inventario completo en la consola.
     */
    public void mostrarInventario() {
        List<Producto> inventario = productoDAO.obtenerTodosProductos();
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (Producto producto : inventario) {
                System.out.println(producto);
            }
        }
    }

    /**
     * Obtiene la lista completa de productos en el inventario.
     *
     * @return Una lista de objetos Producto.
     */
    public List<Producto> getInventario() {
        return productoDAO.obtenerTodosProductos();
    }

    /**
     * Busca un producto por su nombre.
     *
     * @param nombre El nombre del producto a buscar.
     * @return Un objeto Producto si el producto fue encontrado, null en caso
     * contrario.
     */
    public Producto buscarProducto(String nombre) {
        try {
            return productoDAO.buscarProducto(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Actualiza la cantidad de un producto.
     *
     * @param nombre El nombre del producto.
     * @param nuevaCantidad La nueva cantidad del producto.
     * @return true si la cantidad fue actualizada exitosamente, false en caso
     * contrario.
     */
    public boolean actualizarCantidad(String nombre, int nuevaCantidad) {
        try {
            return productoDAO.actualizarCantidad(nombre, nuevaCantidad);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza el precio de un producto.
     *
     * @param nombre El nombre del producto.
     * @param nuevoPrecio El nuevo precio del producto.
     * @return true si el precio fue actualizado exitosamente, false en caso
     * contrario.
     */
    public boolean actualizarPrecio(String nombre, double nuevoPrecio) {
        try {
            return productoDAO.actualizarPrecio(nombre, nuevoPrecio);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Valida si el producto existe en la base de datos
     *
     * @param nombre El nombre del producto.
     * @return true si el producto existe, false en caso contrario.
     */
    public boolean productoExiste(String nombre) {
        try {
            Producto producto = productoDAO.buscarProducto(nombre);
            return producto != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
