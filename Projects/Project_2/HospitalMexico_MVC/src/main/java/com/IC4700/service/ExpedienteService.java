package com.IC4700.service;

import com.IC4700.model.Expediente;
import com.IC4700.repository.ExpedienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * Servicio de la clase Expediente
 * 
 * @version 1.3 - 29/09/2023
 */
@Service
public class ExpedienteService {

    // Atributos de la clase
    private final ExpedienteRepository repository;

    // Constructor
    public ExpedienteService(ExpedienteRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda un expediente en la base de datos.
     *
     * @param expediente El expediente que se desea guardar en la base de datos.
     * @return El expediente guardado en la base de datos con su identificador único
     *         asignado.
     */
    public Expediente saveExpediente(Expediente expediente) {
        // Guarda el expediente en la base de datos utilizando el repositorio
        // (ExpedienteRepository).
        return repository.save(expediente);
    }

    /**
     * Guarda una lista de expedientes en la base de datos.
     *
     * @param expedientes La lista de expedientes que se desea guardar en la base de
     *                    datos.
     * @return La lista de expedientes guardados en la base de datos con sus
     *         identificadores únicos asignados.
     */
    public List<Expediente> saveExpedientes(List<Expediente> expedientes) {
        // Guarda la lista de expedientes en la base de datos utilizando el repositorio
        // (ExpedienteRepository).
        return repository.saveAll(expedientes);
    }

    /**
     * Recupera todos los expedientes de la base de datos.
     *
     * @return Una lista de expedientes que se encuentran en la base de datos.
     */
    public List<Expediente> getExpedientes() {
        // Recupera todos los expedientes de la base de datos utilizando el repositorio
        // (ExpedienteRepository).
        return repository.findAll();
    }

    /**
     * Recupera un expediente de la base de datos por su ID.
     *
     * @param id El ID del expediente que se desea recuperar.
     * @return El expediente encontrado en la base de datos, o null si no se
     *         encuentra.
     */
    public Expediente getExpedienteById(long id) {
        // Busca un expediente en la base de datos por su ID utilizando el repositorio
        // (ExpedienteRepository).
        // Si se encuentra, devuelve el expediente; de lo contrario, devuelve null.
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina un expediente de la base de datos por su ID.
     *
     * @param id El ID del expediente que se desea eliminar.
     * @return Un mensaje que indica que el expediente ha sido eliminado con éxito,
     *         incluyendo el ID del expediente eliminado.
     */
    public String deleteExpediente(long id) {
        // Elimina un expediente de la base de datos por su ID utilizando el repositorio
        // (ExpedienteRepository).
        repository.deleteById(id);

        // Retorna un mensaje que indica que el expediente ha sido eliminado con éxito,
        // incluyendo el ID del expediente eliminado.
        return "Expediente eliminado: " + id;
    }

    /**
     * Actualiza un expediente en la base de datos.
     *
     * @param expediente El expediente con los datos actualizados que se desea
     *                   guardar.
     * @return El expediente actualizado después de guardar en la base de datos.
     */
    public Expediente updateExpediente(Expediente expediente) {
        // Busca el expediente existente en la base de datos por su ID.
        Expediente existingExpediente = repository.findById((long) expediente.getId()).orElse(null);

        // Actualiza los campos del expediente existente con los nuevos valores del
        // expediente proporcionado.
        existingExpediente.setCedula_paciente(expediente.getCedula_paciente());
        existingExpediente.setFecha(expediente.getFecha());
        existingExpediente.setPadecimiento(expediente.getPadecimiento());
        existingExpediente.setProcedimiento_realizado(expediente.getProcedimiento_realizado());
        existingExpediente.setMedicamentos(expediente.getMedicamentos());

        // Guarda los cambios en la base de datos y retorna el expediente actualizado.
        return repository.save(existingExpediente);
    }

    /**
     * Busca un expediente en la base de datos por la cédula del paciente.
     *
     * @param cedula La cédula del paciente que se utiliza como criterio de
     *               búsqueda.
     * @return Un objeto Optional que contiene el expediente encontrado, si existe.
     *         Si no se encuentra un expediente con la cédula proporcionada,
     *         Optional estará vacío.
     */
    public Optional<Expediente> findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

}
