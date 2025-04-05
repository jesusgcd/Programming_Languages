package com.IC4700.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.IC4700.model.Cita;
import com.IC4700.model.Medico;
import com.IC4700.service.CitaService;
import com.IC4700.service.MedicoService;

/**
 * Controlador para manejar las solicitudes relacionadas con la agenda de un
 * médico.
 * 
 * @version 1.4 - 08/10/2023
 */
@Controller
public class ControladorAgendaMedico {

    @Autowired
    private CitaService citaService;

    @Autowired
    private MedicoService medicosService;

    /**
     * Maneja las solicitudes GET en la ruta "/ControladorAgendaMedico".
     * Devuelve la vista "agendaMedico" con un mensaje de error.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "agendaMedico".
     */
    @GetMapping("/ControladorAgendaMedico")
    public String agendaMedico(Model model) {
        model.addAttribute("mensajeError", "Aún no se ha ingresado una cédula de médico");
        return "agendaMedico";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/buscarMedico".
     * Busca citas asociadas a un médico según su cédula y muestra los resultados.
     *
     * @param model        El modelo de datos para la vista.
     * @param cedulaMedico La cédula del médico a buscar.
     * @return El nombre de la vista "agendaMedico" con los resultados de la
     *         búsqueda.
     */
    @PostMapping("/buscarMedico")
    public String buscarMedico(Model model, @RequestParam("cedulaMedico") String cedulaMedico) {
        List<Cita> citas = citaService.getCitas();

        // Filtra las citas para mostrar solo las del médico con la cédula
        // proporcionada.
        for (int i = 0; i < citas.size(); i++) {
            if (!citas.get(i).getCedulaMedico().equals(cedulaMedico)) {
                citas.remove(i);
                i--;
            }
        }

        Optional<Medico> medico = medicosService.findByCedula(cedulaMedico);

        // Validar que el médico exista.
        if (!medico.isPresent()) {
            model.addAttribute("mensajeError", "No se encontró un médico con cédula " + cedulaMedico);
            return "agendaMedico";
        }

        String especialidadMedico = medico.get().getEspecialidad();

        if (citas.isEmpty()) {
            model.addAttribute("mensajeError", "No se encontraron citas para el médico con cédula " + cedulaMedico);
        } else {
            model.addAttribute("citas", citas);
            model.addAttribute("especialidadMedico", especialidadMedico);
        }

        return "agendaMedico";
    }
}
