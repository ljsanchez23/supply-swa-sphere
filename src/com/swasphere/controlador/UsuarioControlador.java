package com.swasphere.controlador;

import com.swasphere.modelo.Usuario;
import com.swasphere.modelo.UsuarioDAO;
import java.sql.SQLException;

/**
 * Controlador de usuarios, permite la comunicación entre la vista y el modelo.
 *
 * @autor ljsanchez23
 */
public class UsuarioControlador {

    private UsuarioDAO usuarioDAO;

    /**
     * Constructor de la clase UsuarioControlador.
     */
    public UsuarioControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Autentica un usuario con el nombre de usuario y contraseña
     * proporcionados.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return Un objeto Usuario si la autenticación es exitosa, null en caso
     * contrario.
     */
    public String autenticar(String nombreUsuario, String password) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNombre(nombreUsuario);
        if (usuario == null) {
            return "Usuario no existe";
        } else if (!usuario.getPassword().equals(password)) {
            return "Contraseña incorrecta";
        } else {
            return "Autenticado";
        }
    }

    /**
     * Cambia la contraseña de un usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param nuevoPassword La nueva contraseña del usuario.
     */
    public void cambiarPassword(String nombreUsuario, String nuevoPassword) {
        usuarioDAO.actualizarPassword(nombreUsuario, nuevoPassword);
        System.out.println("Contraseña actualizada correctamente.");
    }

    /**
     * Registra un nuevo usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return true si el usuario fue registrado exitosamente, false si el
     * nombre de usuario ya existe.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public boolean registrarUsuario(String nombreUsuario, String password) throws SQLException {
        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorNombre(nombreUsuario);
        if (usuarioExistente != null) {
            return false;
        } else {
            return usuarioDAO.insertarUsuario(nombreUsuario, password);
        }
    }

    /**
     * Elimina un usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     * @return true si el usuario fue eliminado exitosamente, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public boolean eliminarUsuario(String nombreUsuario) throws SQLException {
        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorNombre(nombreUsuario);
        if (usuarioExistente != null) {
            return usuarioDAO.eliminarUsuario(nombreUsuario);
        }
        return false;
    }
}
