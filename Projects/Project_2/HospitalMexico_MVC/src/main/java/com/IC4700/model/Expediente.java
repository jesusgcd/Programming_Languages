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
 * Clase Expediente
 * 
 * @version 1.0 - 20/09/2023
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Expediente")
public class Expediente implements Informacion {

    // Atributos de la clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "padecimiento")
    private String padecimiento;

    @Column(name = "procedimiento_realizado")
    private String procedimiento_realizado;

    @Column(name = "medicamentos")
    private String medicamentos;

    // Getters
    public Long getId() {
        return id;
    }

    public String getCedula_paciente() {
        return cedula;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public String getProcedimiento_realizado() {
        return procedimiento_realizado;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCedula_paciente(String cedula) {
        this.cedula = cedula;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public void setProcedimiento_realizado(String procedimiento_realizado) {
        this.procedimiento_realizado = procedimiento_realizado;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    // Métodos
    // implementación de la interfaz Informacion
    @Override
    public String toString() {
        return "Expediente [cedula=" + cedula + ", fecha=" + fecha + ", id=" + id + ", medicamentos=" + medicamentos
                + ", padecimiento=" + padecimiento + ", procedimiento_realizado=" + procedimiento_realizado + "]";
    }
}
