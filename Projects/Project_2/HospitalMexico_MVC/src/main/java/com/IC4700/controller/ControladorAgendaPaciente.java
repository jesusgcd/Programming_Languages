package com.IC4700.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IC4700.model.Cita;
import com.IC4700.model.Medico;
import com.IC4700.model.Paciente;
import com.IC4700.service.CitaService;
import com.IC4700.service.MedicoService;
import com.IC4700.service.PacienteService;

import org.springframework.ui.Model;

/**
 * Controlador para manejar las solicitudes relacionadas con la agenda de un
 * paciente.
 * 
 * @version 1.7 - 08/10/2023
 */
@Controller
public class ControladorAgendaPaciente {

    @Autowired
    private CitaService citaService;
    @Autowired
    private MedicoService medicosService;
    @Autowired
    private PacienteService pacienteService;
    private List<Cita> citas;

    /**
     * Maneja las solicitudes GET en la ruta "/ControladorAgendaPaciente".
     * Devuelve la vista "agendaPaciente" con un mensaje de error.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "agendaPaciente".
     */
    @GetMapping("/ControladorAgendaPaciente")
    public String agendaPaciente(Model model) {

        // coloca mensaje de que aún no se ha ingresado una cédula de paciente
        model.addAttribute("mensajeError", "Aún no se ha ingresado una cédula de paciente");

        return "agendaPaciente";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/buscarPaciente".
     * Busca un paciente por su cédula y muestra sus citas en la vista
     * "agendaPaciente".
     *
     * @param cedulaPaciente La cédula del paciente a buscar.
     * @param model          El modelo de datos para la vista.
     * @return El nombre de la vista "agendaPaciente".
     */
    @PostMapping("/buscarPaciente")
    public String agendaPaciente(@RequestParam("cedulaPaciente") String cedulaPaciente, Model model) {

        // Buscar el paciente por su cédula
        Paciente paciente = pacienteService.getPacienteByCedula(cedulaPaciente);

        // Verificar si el paciente no se encontró
        if (paciente == null) {
            model.addAttribute("mensajeError", "No se encontró un paciente con la cédula ingresada");
            return "agendaPaciente";
        }

        try {
            // Obtener la lista de todas las citas
            citas = citaService.getCitas();

            // Filtrar las citas para mostrar solo las citas del paciente con la cédula
            // proporcionada
            for (int i = 0; i < citas.size(); i++) {
                if (!citas.get(i).getCedulaPaciente().equals(cedulaPaciente)) {
                    citas.remove(i);
                    i--;
                }
            }

            // Recorrer la lista de citas y agregar el nombre y especialidad del médico
            for (int i = 0; i < citas.size(); i++) {
                String cedulaMedico = citas.get(i).getCedulaMedico();
                // Buscar el nombre del médico con la cédula del médico de la cita
                String nombreMedico;
                String especialidadMedico;

                Optional<Medico> medicoOptional = medicosService.findByCedula(cedulaMedico);

                nombreMedico = medicoOptional.get().getNombre();
                especialidadMedico = medicoOptional.get().getEspecialidad();

                citas.get(i).setNombreMedico(nombreMedico);
                citas.get(i).setEspecialidadMedico(especialidadMedico);
            }

            // Agregar la lista de citas al modelo para que se muestre en la vista
            model.addAttribute("citas", citas);

            // Devuelve el nombre de la vista "agendaPaciente"
            return "agendaPaciente";

        } catch (Exception e) {
            // En caso de excepción, redirige a "agendaPaciente"
            return "agendaPaciente";
        }
    }

    /**
     * Maneja las solicitudes GET en la ruta "/citas/agendar".
     * Devuelve la vista "agendar-cita" para permitir a los usuarios agendar una
     * cita médica.
     *
     * @return El nombre de la vista "agendar-cita".
     */
    @GetMapping("/citas/agendar")
    public String agregarCita() {
        return "agendar-cita";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/citas/agendar/form".
     * Permite a los usuarios agregar una nueva cita médica con la información
     * proporcionada en el formulario.
     * Valida los datos ingresados y verifica si el médico seleccionado es el médico
     * de cabecera del paciente.
     *
     * @param model          El modelo de datos para la vista.
     * @param cedulaPaciente La cédula del paciente para quien se agendará la cita.
     * @param fecha          La fecha de la cita.
     * @param hora           La hora de la cita.
     * @param especialidad   La especialidad médica de la cita.
     * @param medico         El médico seleccionado para la cita.
     * @return El nombre de la vista a la que se redirige después de procesar la
     *         solicitud.
     */
    @PostMapping("/citas/agendar/form")
    public String agregarCitaForm(Model model,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("fecha") String fecha,
            @RequestParam("hora") String hora,
            @RequestParam("especialidad") String especialidad,
            @RequestParam("medico") String medico) {

        // Validar que los datos ingresados no sean vacíos
        if (cedulaPaciente.equals("") || fecha.equals("") || hora.equals("") || especialidad.equals("")
                || medico.equals("")) {
            model.addAttribute("mensajeError",
                    "Error al actualizar la cita, verifique los datos ingresados y vuelva a intentarlo");
            return "agendar-cita";
        }

        // Buscar el paciente por su cédula
        Paciente paciente = pacienteService.getPacienteByCedula(cedulaPaciente);

        // Verificar si el paciente no se encontró
        if (paciente == null) {
            model.addAttribute("mensajeError", "No se encontró un paciente con la cédula ingresada");
            return "agendar-cita";
        }

        try {
            List<Medico> medicos = medicosService.getMedicos();

            // Recorrer la lista de médicos y buscar el primero que coincida con la
            // especialidad
            String cedulaMedico = "";
            for (int i = 0; i < medicos.size(); i++) {
                if (medicos.get(i).getEspecialidad().equals(especialidad)) {
                    cedulaMedico = medicos.get(i).getCedula();
                    break;
                }
            }

            // Obtener el médico de cabecera del paciente
            String medicoCabeceraPaciente = paciente.getMedicoCabecera();

            // Validar si el médico seleccionado es el médico de cabecera del paciente o si
            // la especialidad es "General"
            if (medicoCabeceraPaciente.equals(medico) || especialidad.equals("General")) {
                // Agendar la cita
                // Crear una nueva cita con los datos recibidos del formulario
                Cita cita = new Cita();
                cita.setCedulaPaciente(cedulaPaciente);
                cita.setCedulaMedico(cedulaMedico);
                cita.setFecha(fecha);
                cita.setHora(hora);
                cita.setDuracion("30 minutos");

                // Guardar la cita en la base de datos
                citaService.saveCita(cita);

                // Redirige a la lista de citas del paciente
                return "redirect:/ControladorAgendaPaciente";
            }

            model.addAttribute("mensajeError",
                    "El médico que está intentando agendar la cita no es el médico de cabecera del paciente");

            // Redirige de nuevo a la vista de agendar cita
            return "agendar-cita";
        } catch (Exception e) {
            model.addAttribute("mensajeError",
                    "Error al actualizar la cita, verifique los datos ingresados y vuelva a intentarlo");
            return "agendar-cita";
        }
    }

    /**
     * Maneja las solicitudes GET en la ruta "/citas/borrar/{citaId}".
     * Muestra la vista "borrar-cita" con los detalles de la cita que se desea
     * borrar.
     *
     * @param model  El modelo de datos para la vista.
     * @param citaId El ID de la cita que se desea borrar, obtenido de la URL.
     * @return El nombre de la vista "borrar-cita".
     */
    @GetMapping("/citas/borrar/{citaId}")
    public String borrarCita(Model model, @PathVariable("citaId") int citaId) {

        // Obtener la cita por su ID
        Cita cita = citaService.getCitaById(citaId);

        // Agregar la cita al modelo para mostrar los detalles en la vista
        model.addAttribute("cita", cita);

        // Devolver el nombre de la vista "borrar-cita"
        return "borrar-cita";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/citas/borrar/form".
     * Permite a los usuarios confirmar y borrar una cita médica específica por su
     * ID.
     * Valida que la cédula del paciente coincida con la cédula del paciente
     * asociada a la cita antes de eliminarla.
     *
     * @param model          El modelo de datos para la vista.
     * @param idCita         El ID de la cita que se desea borrar.
     * @param cedulaPaciente La cédula del paciente, utilizada para validar la
     *                       asociación de la cita con el paciente.
     * @return El nombre de la vista a la que se redirige después de procesar la
     *         solicitud.
     */
    @PostMapping("/citas/borrar/form")
    public String borrarCitaForm(Model model,
            @RequestParam("idCita") int idCita,
            @RequestParam("cedulaPaciente") String cedulaPaciente) {

        // Obtener la cita por su ID
        Cita cita = citaService.getCitaById(idCita);

        // Verificar si la cita no se encontró o si la cédula del paciente no coincide
        if (cita == null || !cita.getCedulaPaciente().equals(cedulaPaciente)) {
            model.addAttribute("mensajeError",
                    "No se encontró una cita con el ID ingresado o la cédula del paciente no coincide con la cédula del paciente de la cita");
            return "borrar-cita";
        }

        // Eliminar la cita de la base de datos
        citaService.deleteCita(idCita);

        // Redirigir a la lista de citas del paciente
        return "redirect:/ControladorAgendaPaciente";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/citas/editar/{citaId}".
     * Muestra la vista "editar-cita" con los detalles de la cita que se desea
     * editar.
     *
     * @param model  El modelo de datos para la vista.
     * @param citaId El ID de la cita que se desea editar, obtenido de la URL.
     * @return El nombre de la vista "editar-cita".
     */
    @GetMapping("/citas/editar/{citaId}")
    public String editarCita(Model model, @PathVariable("citaId") int citaId) {
        // Obtener la cita por su ID
        Cita cita = citaService.getCitaById(citaId);

        // Agregar la cita al modelo para mostrar los detalles en la vista
        model.addAttribute("cita", cita);

        // Devolver el nombre de la vista "editar-cita"
        return "editar-cita";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/citas/editar/form".
     * Actualiza los detalles de una cita en la base de datos después de la edición.
     *
     * @param model          El modelo de datos para la vista.
     * @param idCita         El ID de la cita que se desea editar.
     * @param cedulaPaciente La cédula del paciente para la cita.
     * @param fecha          La fecha de la cita.
     * @param hora           La hora de la cita.
     * @param duracion       La duración de la cita.
     * @param especialidad   La especialidad de la cita.
     * @param medico         El médico asignado para la cita.
     * @return Redirige a la lista de citas o muestra un mensaje de error si la
     *         actualización no es exitosa.
     */
    @PostMapping("/citas/editar/form")
    public String editarCitaForm(Model model,
            @RequestParam("idCita") int idCita,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("fecha") String fecha,
            @RequestParam("hora") String hora,
            @RequestParam("duracion") String duracion,
            @RequestParam("especialidad") String especialidad,
            @RequestParam("medico") String medico) {

        // validar que los datos ingresados sean no sean vacios
        if (cedulaPaciente.equals("") || fecha.equals("") || hora.equals("") || duracion.equals("")
                || especialidad.equals("") || medico.equals("")) {
            model.addAttribute("mensajeError",
                    "Error al actualizar la cita, verifique los datos ingresados y vuelva a intentarlo");
            // Redirige a la lista de citas
            return "redirect:/citas/editar/" + idCita;
        }
        // validar que el id de la cita exista
        if (citaService.getCitaById(idCita) == null || citaService
                .getCitaById(idCita).getCedulaPaciente().equals(cedulaPaciente) == false) {
            model.addAttribute("mensajeError",
                    "No se encontro una cita con el id ingresado o la cedula del paciente no coincide con la cedula del paciente de la cita");
            // Redirige a la lista de citas
            return "redirect:/citas/editar/" + idCita;
        }

        try {

            List<Medico> medicos = medicosService.getMedicos();
            // recorrer la lista de mediso y buscar el primero medico que concida con la
            // especialidad
            String cedulaMedico = "";
            for (int i = 0; i < medicos.size(); i++) {
                if (medicos.get(i).getEspecialidad().equals(especialidad)) {
                    cedulaMedico = medicos.get(i).getCedula();
                    break;
                }
            }

            // System.out.println("cedulaMedico: " + cedulaMedico);
            // traer la paciente con la cedula del paciente
            Paciente paciente = pacienteService.getPacienteByCedula(cedulaPaciente);
            // System.out.println("paciente: " + paciente);
            String medicoCabeceraPaciente = paciente.getMedicoCabecera();
            // System.out.println("medicoCabeceraPaciente: " + medicoCabeceraPaciente);

            // Validar si el medico de cabecera del paciente es el mismo que se selecciono
            // en la cita, o si la especialidad seleccionada es igual a General
            if (medicoCabeceraPaciente.equals(medico) || especialidad.equals("General")) {
                Cita cita = citaService.getCitaById(idCita);
                if (cita == null) {
                    return "redirect:/citas/editar/" + idCita;
                }
                // Actualizar los datos de la cita
                cita.setCedulaPaciente(cedulaPaciente);
                cita.setCedulaMedico(cedulaMedico);
                cita.setFecha(fecha);
                cita.setHora(hora);
                cita.setDuracion(duracion);

                // Guardar los cambios en la base de datos\
                citaService.updateCita(cita);

                return "redirect:/ControladorAgendaPaciente";

            }
            model.addAttribute("mensajeError",
                    "El medico que esta intentando actualizar la cita no es el medico de cabecera del paciente");
            // Redirige a la lista de citas
            return "redirect:/citas/editar/" + idCita;
        } catch (Exception e) {
            model.addAttribute("mensajeError",
                    "Error al actualizar la cita, verifique los datos ingresados y vuelva a intentarlo");
            // Redirige a la lista de citas
            return "redirect:/citas/editar/" + idCita;
        }
    }

}