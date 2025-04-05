package com.IC4700.service;

import com.IC4700.model.Medico;
import com.IC4700.repository.MedicoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Servicio de la clase Medico
 * 
 * @version 1.3 - 29/09/2023
 */
@Service
public class MedicoService {

    // Atributos de la clase
    private final MedicoRepository repository;

    // Constructor
    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un objeto Medico en la base de datos.
     *
     * @param medico El objeto Medico que se va a guardar en la base de datos.
     * @return El objeto Medico guardado en la base de datos, incluyendo cualquier
     *         identificador generado automáticamente.
     */
    public Medico saveMedico(Medico medico) {
        return repository.save(medico);
    }

    /**
     * Guarda una lista de objetos Medico en la base de datos.
     *
     * @param medicos La lista de objetos Medico que se van a guardar en la base de
     *                datos.
     * @return Una lista de objetos Medico guardados en la base de datos, incluyendo
     *         cualquier identificador generado automáticamente.
     */
    public List<Medico> saveMedicos(List<Medico> medicos) {
        return repository.saveAll(medicos);
    }

    /**
     * Obtiene una lista de todos los objetos Medico almacenados en la base de
     * datos.
     *
     * @return Una lista de objetos Medico almacenados en la base de datos.
     */
    public List<Medico> getMedicos() {
        return repository.findAll();
    }

    /**
     * Busca y devuelve un objeto Medico en la base de datos por su ID.
     *
     * @param id El ID del objeto Medico que se desea buscar.
     * @return El objeto Medico encontrado o null si no se encuentra ningún objeto
     *         con ese ID.
     */
    public Medico getMedicoById(long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina un objeto Medico de la base de datos por su ID.
     *
     * @param id El ID del objeto Medico que se desea eliminar.
     * @return Un mensaje indicando que el Medico ha sido eliminado.
     */
    public String deleteMedico(long id) {
        repository.deleteById(id);
        return "Medico eliminado: " + id;
    }

    /**
     * Actualiza un objeto Medico en la base de datos.
     *
     * @param Medico El objeto Medico con los nuevos datos a actualizar.
     * @return El objeto Medico actualizado en la base de datos.
     */
    public Medico updateMedico(Medico Medico) {
        // Busca el médico existente en la base de datos por su cédula
        Medico existingMedico = repository.findByCedula(Medico.getCedula()).orElse(null);

        // Actualiza los campos del médico existente con los nuevos datos
        existingMedico.setNombre(Medico.getNombre());
        existingMedico.setApellido(Medico.getApellido());
        existingMedico.setCorreo(Medico.getCorreo());
        existingMedico.setContrasenna(Medico.getContrasenna());

        // Guarda los cambios en la base de datos y retorna el médico actualizado
        return repository.save(existingMedico);
    }

    /**
     * Busca un objeto Medico en la base de datos por su número de cédula.
     *
     * @param cedula El número de cédula del médico que se desea buscar.
     * @return Un objeto Optional que contiene el médico encontrado (si existe) o un
     *         valor nulo si no se encuentra.
     */
    public Optional<Medico> findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

}
