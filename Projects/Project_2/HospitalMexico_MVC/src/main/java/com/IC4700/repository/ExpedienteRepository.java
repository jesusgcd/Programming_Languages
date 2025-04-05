package com.IC4700.repository;

import com.IC4700.model.Expediente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repositorio de la clase Expediente
 * 
 * @version 1.1 - 26/09/2023
 */
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

    /*
     * Busca un expediente por su cedula
     * 
     * @param cedula Cedula del expediente a buscar
     * 
     * @return Expediente encontrado
     */
    Optional<Expediente> findByCedula(String cedula);
}
