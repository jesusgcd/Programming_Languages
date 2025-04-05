package com.IC4700.service;

import com.IC4700.model.Cita;
import com.IC4700.repository.CitaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Servicio de la clase Cita
 * 
 * @version 1.3 - 29/09/2023
 */
@Service
public class CitaService {

    // Atributos de la clase
    private final CitaRepository repository;

    // Constructor
    public CitaService(CitaRepository repository) {
        this.repository = repository;
    }

    /**
     * Guarda una cita en la base de datos.
     *
     * @param cita La cita que se va a guardar en la base de datos.
     * @return La cita guardada en la base de datos, incluyendo su identificador
     *         único asignado.
     */
    public Cita saveCita(Cita cita) {
        // Utiliza el repositorio (CitaRepository) para guardar la entidad en la base de
        // datos.
        // El método save de Spring Data JPA manejará la operación de guardado.
        // Si la entidad ya existe en la base de datos, se actualizará; de lo contrario,
        // se creará una nueva entrada.
        return repository.save(cita);
    }

    /**
     * Guarda una lista de citas en la base de datos.
     *
     * @param citas La lista de citas que se van a guardar en la base de datos.
     * @return La lista de citas guardadas en la base de datos, incluyendo sus
     *         identificadores únicos asignados.
     */
    public List<Cita> saveCitas(List<Cita> citas) {
        // Utiliza el repositorio (CitaRepository) para guardar la lista de entidades en
        // la base de datos.
        // El método saveAll de Spring Data JPA manejará la operación de guardado para
        // cada entidad en la lista.
        // Si las entidades ya existen en la base de datos, se actualizarán; de lo
        // contrario, se crearán nuevas entradas.
        return repository.saveAll(citas);
    }

    /**
     * Recupera todas las citas almacenadas en la base de datos.
     *
     * @return Una lista de todas las citas almacenadas en la base de datos.
     */
    public List<Cita> getCitas() {
        // Utiliza el repositorio (CitaRepository) para recuperar todas las citas
        // almacenadas en la base de datos.
        // El método findAll de Spring Data JPA realiza una consulta para obtener todas
        // las citas.
        return repository.findAll();
    }

    /**
     * Recupera una cita por su identificador único.
     *
     * @param id El identificador único de la cita que se desea recuperar.
     * @return La cita con el identificador proporcionado o null si no se encuentra.
     */
    public Cita getCitaById(long id) {
        // Utiliza el repositorio (CitaRepository) para buscar una cita por su
        // identificador.
        // El método findById de Spring Data JPA busca una cita por su identificador en
        // la base de datos.
        // Si se encuentra la cita, se devuelve; de lo contrario, se devuelve null.
        return repository.findById(id).orElse(null);
    }

    /**
     * Elimina una cita de la base de datos por su identificador único.
     *
     * @param id El identificador único de la cita que se desea eliminar.
     * @return Un mensaje que indica que la cita ha sido eliminada junto con su
     *         identificador.
     */
    public String deleteCita(long id) {
        // Utiliza el repositorio (CitaRepository) para eliminar una cita de la base de
        // datos
        // por su identificador. El método deleteById de Spring Data JPA realiza la
        // eliminación.
        repository.deleteById(id);

        // Devuelve un mensaje que indica que la cita ha sido eliminada junto con su
        // identificador.
        return "Cita eliminado: " + id;
    }

    /**
     * Actualiza una cita en la base de datos con nuevos datos.
     *
     * @param cita La cita que se desea actualizar con los nuevos datos.
     * @return La cita actualizada después de guardar los cambios en la base de
     *         datos.
     */
    public Cita updateCita(Cita cita) {
        // Busca la cita existente en la base de datos por su identificador único.
        Cita existingCita = repository.findById((long) cita.getId()).orElse(null);

        // Actualiza los datos de la cita existente con los nuevos datos proporcionados.
        existingCita.setCedulaMedico(cita.getCedulaMedico());
        existingCita.setCedulaPaciente(cita.getCedulaPaciente());
        existingCita.setDuracion(cita.getDuracion());
        existingCita.setFecha(cita.getFecha());
        existingCita.setHora(cita.getHora());

        // Guarda los cambios en la base de datos utilizando el repositorio
        // (CitaRepository).
        return repository.save(existingCita);
    }

}
