package com.IC4700.model;

import lombok.AllArgsConstructor;

/**
 * Clase abstracta UsuarioAbs
 * 
 * @version 1.0 - 20/09/2023
 */
@AllArgsConstructor
public abstract class UsuarioAbs {

    // Metodos de la clase abstracta que deberan ser implementados por las clases
    // que la hereden
    // Getters
    public abstract int getId();

    public abstract String getCedula();

    public abstract String getNombre();

    public abstract String getApellido();

    public abstract String getCorreo();

    public abstract String getContrasenna();

    // Setters
    public abstract void setId(int id);

    public abstract void setCedula(String cedula);

    public abstract void setNombre(String nombre);

    public abstract void setApellido(String apellido);

    public abstract void setCorreo(String correo);

    public abstract void setContrasenna(String contrasenna);
}
