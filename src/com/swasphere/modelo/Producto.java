package com.swasphere.modelo;

/**
 * Representa un producto en el sistema.
 *
 * @autor ljsanchez23
 */
public class Producto {

    // Atributos de la clase Producto
    private String nombre;
    private int cantidad;
    private double precio;

    /**
     * Constructor de la clase Producto.
     *
     * @param nombre El nombre del producto.
     * @param cantidad La cantidad del producto.
     * @param precio El precio del producto.
     */
    public Producto(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre El nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad del producto.
     *
     * @return La cantidad del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad La cantidad del producto.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio El precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método que permite crear una descripción textual del objeto.
     *
     * @return Una cadena de texto con los detalles del producto.
     */
    @Override
    public String toString() {
        return "Producto{" + "nombre='" + nombre + '\'' + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
