package com.swasphere.modelo;

/**
 * Representa un usuario en el sistema.
 *
 * @autor ljsanchez23
 */
public class Usuario {

    // Atributos de la clase Usuario
    private String nombreUsuario;
    private String password;

    /**
     * Constructor de la clase Usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param password La contraseña del usuario.
     */
    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param nombreUsuario El nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
