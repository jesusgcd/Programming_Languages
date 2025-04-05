package com.IC4700.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

import com.IC4700.model.interfaces.Informacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Clase Medico
 * 
 * @version 1.0 - 20/09/2023
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Medico")
public class Medico extends UsuarioAbs implements Serializable, Informacion {

    // Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasenna")
    private String contrasenna;

    @Column(name = "especialidad")
    private String especialidad;

    // Getters
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCedula() {
        return cedula;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    @Override
    public String getContrasenna() {
        return contrasenna;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // Setters
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    // Métodos
    // implementación de la interfaz Informacion
    @Override
    public String toString() {
        return "Medico [apellido=" + apellido + ", contrasenna=" + contrasenna + ", correo=" + correo + ", cedula="
                + cedula + ", especialidad=" + especialidad + ", id=" + id + ", nombre=" + nombre + "]";
    }

}