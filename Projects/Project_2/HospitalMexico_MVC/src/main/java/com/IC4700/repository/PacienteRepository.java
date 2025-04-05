package com.IC4700.repository;

import com.IC4700.model.Paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repositorio de la clase Paciente
 * 
 * @version 1.1 - 26/09/2023
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /*
     * Busca un paciente por su cedula
     * 
     * @param cedula Cedula del paciente a buscar
     * 
     * @return Paciente encontrado
     */
    Optional<Paciente> findByCedula(String cedula);

}
