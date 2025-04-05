package com.IC4700.service;

import com.IC4700.model.Paciente;
import com.IC4700.repository.PacienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Servicio de la clase Paciente
 * 
 * @version 1.3 - 29/09/2023
 */
@Service
public class PacienteService {

    // Atributos de la clase
    private final PacienteRepository repository;

    // Constructor
    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un paciente en la base de datos.
     *
     * @param paciente El paciente que se va a guardar en la base de datos.
     * @return El paciente guardado en la base de datos, incluyendo su identificador
     *         único asignado.
     */
    public Paciente savePaciente(Paciente paciente) {
        return repository.save(paciente);
    }

    /**
     * Guarda una lista de pacientes en la base de datos.
     *
     * @param pacientes La lista de pacientes que se van a guardar en la base de
     *                  datos.
     * @return La lista de pacientes guardados en la base de datos, incluyendo sus
     *         identificadores únicos asignados.
     */
    public List<Paciente> savePacientes(List<Paciente> pacientes) {
        return repository.saveAll(pacientes);
    }

    /**
     * Recupera todos los pacientes almacenados en la base de datos.
     *
     * @return Una lista de pacientes almacenados en la base de datos.
     */
    public List<Paciente> getPacientes() {
        return repository.findAll();
    }

    /**
     * Recupera un paciente de la base de datos por su identificador único.
     *
     * @param id El identificador único del paciente que se desea recuperar.
     * @return El paciente con el identificador único especificado, o null si no se
     *         encuentra.
     */
    public Paciente getPacienteByCedula(String cedula) {
        return repository.findByCedula(cedula).orElse(null);
    }

    /**
     * Recupera un paciente de la base de datos por su ID.
     *
     * @param id El ID del paciente que se desea recuperar.
     * @return El paciente encontrado en la base de datos, o null si no se
     *         encuentra.
     */
    public Paciente getPacienteById(long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina un paciente de la base de datos por su ID.
     *
     * @param id El ID del paciente que se desea eliminar.
     * @return Un mensaje indicando que el paciente ha sido eliminado.
     */

    public String deletePaciente(Long id) {
        repository.deleteById(id);
        return "Paciente eliminado: " + id;
    }

    /**
     * Actualiza un paciente en la base de datos.
     *
     * @param paciente El paciente que se desea actualizar en la base de datos.
     * @return El paciente actualizado en la base de datos.
     */
    public Paciente updatePaciente(Paciente paciente) {
        Paciente existingPaciente = repository.findByCedula(paciente.getCedula()).orElse(null);
        existingPaciente.setNombre(paciente.getNombre());
        existingPaciente.setApellido(paciente.getApellido());
        existingPaciente.setCorreo(paciente.getCorreo());
        existingPaciente.setMedicoCabecera(paciente.getMedicoCabecera());
        existingPaciente.setContrasenna(paciente.getContrasenna());
        return repository.save(existingPaciente);
    }

    /**
     * Busca un paciente en la base de datos por su número de cédula.
     *
     * @param cedula El número de cédula del paciente que se desea buscar.
     * @return El paciente encontrado en la base de datos, o null si no se
     *         encuentra.
     */
    public Paciente findByCedula(String cedula) {
        return repository.findByCedula(cedula).orElse(null);
    }

}
