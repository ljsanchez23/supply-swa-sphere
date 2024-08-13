package com.swasphere.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja las operaciones de la base de datos para la entidad
 * Producto.
 *
 * @autor ljsanchez23
 */
public class ProductoDAO {

    private Connection conn;

    /**
     * Constructor de la clase ProductoDAO.
     *
     * @param conn La conexión a la base de datos.
     */
    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto El producto a agregar.
     * @return true si el producto fue insertado exitosamente, false en caso
     * contrario.
     */
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, cantidad, precio) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getCantidad());
            preparedStatement.setDouble(3, producto.getPrecio());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param nombre El nombre del producto a eliminar.
     * @return true si el producto fue eliminado exitosamente, false en caso
     * contrario.
     */
    public boolean eliminarProducto(String nombre) {
        String sql = "DELETE FROM productos WHERE nombre = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza la cantidad de un producto en la base de datos.
     *
     * @param nombre El nombre del producto.
     * @param nuevaCantidad La nueva cantidad del producto.
     * @return true si la cantidad fue actualizada exitosamente, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public boolean actualizarCantidad(String nombre, int nuevaCantidad) throws SQLException {
        String sql = "UPDATE productos SET cantidad = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nuevaCantidad);
            pstmt.setString(2, nombre);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Actualiza el precio de un producto en la base de datos.
     *
     * @param nombre El nombre del producto.
     * @param nuevoPrecio El nuevo precio del producto.
     * @return true si el precio fue actualizado exitosamente, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public boolean actualizarPrecio(String nombre, double nuevoPrecio) throws SQLException {
        String sql = "UPDATE productos SET precio = ? WHERE nombre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoPrecio);
            pstmt.setString(2, nombre);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Busca un producto por su nombre en la base de datos.
     *
     * @param nombre El nombre del producto.
     * @return Un objeto Producto si el producto fue encontrado, null en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Producto buscarProducto(String nombre) throws SQLException {
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) = LOWER(?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombreProducto = rs.getString("nombre");
                    int cantidad = rs.getInt("cantidad");
                    double precio = rs.getDouble("precio");
                    return new Producto(nombreProducto, cantidad, precio);
                }
            }
        }
        return null;
    }

    /**
     * Obtiene una lista con todos los productos de la base de datos.
     *
     * @return Una lista de objetos Producto.
     */
    public List<Producto> obtenerTodosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int cantidad = resultSet.getInt("cantidad");
                double precio = resultSet.getDouble("precio");
                Producto producto = new Producto(nombre, cantidad, precio);
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    /**
     * Elimina todos los registros de la tabla productos.
     *
     * @return true si los registros fueron eliminados exitosamente, false en
     * caso contrario.
     */
    public boolean limpiarTabla() {
        String sql = "DELETE FROM productos";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
