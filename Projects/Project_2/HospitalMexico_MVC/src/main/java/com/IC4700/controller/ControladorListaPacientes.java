package com.IC4700.controller;

import com.IC4700.model.Paciente;
import com.IC4700.model.Cita;
import com.IC4700.model.Medico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.IC4700.service.PacienteService;
import com.IC4700.service.CitaService;
import com.IC4700.service.MedicoService;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Controlador para manejar las solicitudes relacionadas con la lista de pacientes
 * 
 * @version 1.5 - 08/10/2023
 */
@Controller
public class ControladorListaPacientes {

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private CitaService citaService;
    @Autowired
    private MedicoService medicoService;

    // Clase para almacenar un paciente y sus citas
    private class PacienteConCitas {

        // Atributos de la clase
        private Paciente paciente;
        private String citas;

        public PacienteConCitas(Paciente paciente, String citas) {
            this.paciente = paciente;
            this.citas = citas;
        }

        // Getters
        public Paciente getPaciente() {
            return paciente;
        }

        public String getCitas() {
            return citas;
        }

    }

    @GetMapping("/ControladorListaPacientes")
    public String listaPacientes(Model model) {

        List<Paciente> listaPacientes = pacienteService.getPacientes();
        List<Cita> listaCitas = citaService.getCitas();
        List<PacienteConCitas> listaPacientesConCitas = new ArrayList<PacienteConCitas>();

        // recorrer la lista de pacientes y obtener las citas de cada uno
        for (Paciente miPaciente : listaPacientes) {
            String tempCedula = miPaciente.getCedula();
            String fechaCitas = "";
            for (Cita miCita : listaCitas) {
                if (miCita.getCedulaPaciente().equals(tempCedula)) {
                    fechaCitas += miCita.getFecha() + ", ";
                }
            }
            if (fechaCitas.equals("")) {
                fechaCitas = "No tiene citas";
            }
            PacienteConCitas pacienteConCitas = new PacienteConCitas(miPaciente, fechaCitas);
            listaPacientesConCitas.add(pacienteConCitas);
        }

        model.addAttribute("listaPacientesConCitas", listaPacientesConCitas);
        return "listaPacientes";
    }

    @GetMapping("/paciente/agregar/")
    public String agregarPaciente(Model model) {
        return "agregar-paciente";
    }

    @PostMapping("/pacientes/crear/form")
    public String agregarPacienteForm(Model model,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("nombrePaciente") String nombrePaciente,
            @RequestParam("apellidoPaciente") String apellidoPaciente,
            @RequestParam("correoPaciente") String correoPaciente,
            @RequestParam("medicoCabecera") String medicoCabecera) {

        // validar que los campos no esten vacios
        if (cedulaPaciente.equals("") || nombrePaciente.equals("") || apellidoPaciente.equals("")
                || correoPaciente.equals("") || medicoCabecera.equals("")) {
            model.addAttribute("mensajeError", "Todos los campos son obligatorios");
            return "agregar-paciente";
        }

        // validar que la cedula no exista
        Paciente paciente = pacienteService.getPacienteByCedula(cedulaPaciente);
        if (paciente != null) {
            model.addAttribute("mensajeError", "Ya existe un paciente con esa cedula");
            return "agregar-paciente";
        }

        // vaiidar que el medicocabecera exista
        Optional<Medico> medico = medicoService.findByCedula(medicoCabecera);
        if (!medico.isPresent()) {
            model.addAttribute("mensajeError", "No existe un medico con esa cedula");
            return "agregar-paciente";
        }

        // crear el paciente
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setCedula(cedulaPaciente);
        nuevoPaciente.setNombre(nombrePaciente);
        nuevoPaciente.setApellido(apellidoPaciente);
        nuevoPaciente.setCorreo(correoPaciente);
        nuevoPaciente.setMedicoCabecera(medicoCabecera);

        // guardar el paciente
        pacienteService.savePaciente(nuevoPaciente);

        return "redirect:/ControladorListaPacientes";
    }

    @GetMapping("/pacientes/editar/{pacienteId}")
    public String editarPaciente(Model model,
            @PathVariable("pacienteId") int pacienteId) {
        Paciente paciente = pacienteService.getPacienteById(pacienteId);
        model.addAttribute("paciente", paciente);

        return "editar-paciente";
    }

    @PostMapping("/pacientes/editar/form")
    public String editarPacienteForm(Model model,
            @RequestParam("idPaciente") int idPaciente,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("nombrePaciente") String nombrePaciente,
            @RequestParam("apellidoPaciente") String apellidoPaciente,
            @RequestParam("correoPaciente") String correoPaciente,
            @RequestParam("medicoCabecera") String medicoCabecera) {

        // validar que los campos no esten vacios
        if (nombrePaciente.equals("") || apellidoPaciente.equals("")
                || correoPaciente.equals("") || medicoCabecera.equals("")) {
            model.addAttribute("mensajeError", "Todos los campos son obligatorios");
            return "redirect:/pacientes/editar/" + idPaciente;
        }

        // validar que el medicocabecera exista
        Optional<Medico> medico = medicoService.findByCedula(medicoCabecera);
        if (!medico.isPresent()) {
            model.addAttribute("mensajeError", "No existe un medico con esa cedula");
            return "redirect:/pacientes/editar/" + idPaciente;
        }

        // crear el paciente
        Paciente nuevoPaciente = new Paciente();
        nuevoPaciente.setId(idPaciente);
        nuevoPaciente.setCedula(cedulaPaciente);
        nuevoPaciente.setNombre(nombrePaciente);
        nuevoPaciente.setApellido(apellidoPaciente);
        nuevoPaciente.setCorreo(correoPaciente);
        nuevoPaciente.setMedicoCabecera(medicoCabecera);

        // actualizar el paciente
        pacienteService.updatePaciente(nuevoPaciente);

        return "redirect:/ControladorListaPacientes";
    }

    @GetMapping("/pacientes/borrar/{pacienteId}")
    public String borrarPaciente(Model model,
            @PathVariable("pacienteId") int pacienteId) {
        Paciente paciente = pacienteService.getPacienteById(pacienteId);
        model.addAttribute("paciente", paciente);

        return "borrar-paciente";
    }

    @PostMapping("/pacientes/borrar/form")
    public String borrarPacienteForm(Model model,
            @RequestParam("idPaciente") int idPaciente) {

        // borrar el paciente
        pacienteService.deletePaciente((long) idPaciente);

        return "redirect:/ControladorListaPacientes";
    }

}
