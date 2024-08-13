package com.swasphere.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Clase que maneja las operaciones de la base de datos para la entidad Usuario.
 *
 * @autor ljsanchez23
 */
public class UsuarioDAO {

    // Constantes para la conexión a la base de datos
    private static final String URL = System.getProperty("DB_URL").trim();
    private static final String USER = System.getProperty("DB_USER").trim();
    private static final String PASSWORD = System.getProperty("DB_PASSWORD").trim();

    /**
     * Método para obtener una conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     */
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si el usuario fue insertado exitosamente, false en caso
     * contrario.
     */
    public boolean insertarUsuario(String nombreUsuario, String password) {
        String sql = "INSERT INTO usuarios (nombreUsuario, password) VALUES (?, ?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, password);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     *
     * @param nombreUsuario El nombre de usuario.
     * @return true si el usuario fue eliminado exitosamente, false en caso
     * contrario.
     */
    public boolean eliminarUsuario(String nombreUsuario) {
        String sql = "DELETE FROM usuarios WHERE nombreUsuario = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza la contraseña de un usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param newPassword La nueva contraseña del usuario.
     * @return true si la contraseña fue actualizada exitosamente, false en caso
     * contrario.
     */
    public boolean actualizarPassword(String nombreUsuario, String newPassword) {
        String sql = "UPDATE usuarios SET password = ? WHERE nombreUsuario = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, nombreUsuario);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina todos los registros de la tabla usuarios.
     *
     * @return true si los registros fueron eliminados exitosamente, false en
     * caso contrario.
     */
    public boolean limpiarTabla() {
        String sql = "DELETE FROM usuarios";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca un usuario por su nombre de usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario.
     * @return Un objeto Usuario si el usuario fue encontrado, null en caso
     * contrario.
     */
    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreUsuario);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombreUsuario");
                String password = resultSet.getString("password");
                return new Usuario(nombre, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
