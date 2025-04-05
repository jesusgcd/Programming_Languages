package com.IC4700.model;

import com.IC4700.model.interfaces.Informacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Clase Cita
 * 
 * @version 1.0 - 20/09/2023
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cita")
public class Cita implements Informacion {

    // Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hora")
    private String hora;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "cedula_medico")
    private String cedula_medico;

    @Column(name = "cedula_paciente")
    private String cedula_paciente;

    @Column(name = "duracion")
    private String duracion;

    private String nombreMedico;
    private String especialidadMedico;

    // Getters
    public int getId() {
        return this.id;
    }

    public String getHora() {
        return this.hora;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getCedulaMedico() {
        return this.cedula_medico;
    }

    public String getCedulaPaciente() {
        return this.cedula_paciente;
    }

    public String getDuracion() {
        return this.duracion;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCedulaMedico(String cedula_medico) {
        this.cedula_medico = cedula_medico;
    }

    public void setCedulaPaciente(String cedula_paciente) {
        this.cedula_paciente = cedula_paciente;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    // Metodos
    // implementación de la interfaz Informacion
    @Override
    public String toString() {
        return "Cita [id=" + id + ", hora=" + hora + ", fecha=" + fecha + ", cedula_medico=" + cedula_medico
                + ", cedula_paciente=" + cedula_paciente + ", duracion=" + duracion + "]";
    }
}
