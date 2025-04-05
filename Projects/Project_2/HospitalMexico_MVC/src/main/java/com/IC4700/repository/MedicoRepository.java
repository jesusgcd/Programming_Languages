package com.IC4700.repository;

import com.IC4700.model.Medico;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repositorio de la clase Medico
 * 
 * @version 1.1 - 26/09/2023
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    /*
     * Busca un medico por su cedula
     * 
     * @param cedula Cedula del medico a buscar
     * 
     * @return Medico encontrado
     */
    Optional<Medico> findByCedula(String cedula);

}
