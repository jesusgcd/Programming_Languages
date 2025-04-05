package com.IC4700.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.IC4700.model.Expediente;
import com.IC4700.model.Paciente;
import com.IC4700.service.ExpedienteService;
import com.IC4700.service.PacienteService;

import org.springframework.ui.Model;

/**
 * Controlador para manejar las solicitudes relacionadas con el expediente de un
 * paciente.
 * 
 * @version 1.7 - 08/10/2023
 */
@Controller
public class ControladorExpedientePaciente {

    @Autowired
    private ExpedienteService expedienteService;
    @Autowired
    private PacienteService pacienteService;

    /**
     * Maneja las solicitudes GET en la ruta "/ControladorExpedientePaciente".
     * Devuelve la vista "expedientePaciente" con un mensaje de error.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "expedientePaciente".
     */
    @GetMapping("/ControladorExpedientePaciente")
    public String expedientePaciente(Model model) {

        model.addAttribute("mensajeError", "Aun no se ha ingresado una cedula de paciente");

        return "expedientePaciente";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/buscarExpedientePaciente".
     * Busca los expedientes de un paciente por su cédula y muestra los resultados
     * en la vista "expedientePaciente".
     *
     * @param model          El modelo de datos para la vista.
     * @param cedulaPaciente La cédula del paciente para buscar sus expedientes.
     * @return El nombre de la vista "expedientePaciente" con los resultados de la
     *         búsqueda o un mensaje de error si no se encontraron expedientes.
     */
    @PostMapping("/buscarExpedientePaciente")
    public String buscarExpedientesPaciente(Model model,
            @RequestParam("cedulaPaciente") String cedulaPaciente) {

        // System.out.println("Cedula del paciente: " + cedulaPaciente);

        Paciente paciente = pacienteService.findByCedula(cedulaPaciente);
        if (cedulaPaciente.isEmpty() || paciente == null) {
            model.addAttribute("mensajeError", "No se ha ingresado una cedula de paciente o la cedula es invalida");
            return "expedientePaciente";
        }

        List<Expediente> listaExpedientesPaciente = expedienteService.getExpedientes();
        for (int i = 0; i < listaExpedientesPaciente.size(); i++) {
            if (!listaExpedientesPaciente.get(i).getCedula().equals(cedulaPaciente)) {
                listaExpedientesPaciente.remove(i);
                i--;
            }
        }

        if (listaExpedientesPaciente.isEmpty()) {
            model.addAttribute("mensajeError", "No se ha encontrado ningun expediente con la cedula ingresada");
            return "expedientePaciente";
        }

        model.addAttribute("expedientesPaciente", listaExpedientesPaciente);

        return "expedientePaciente";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/expediente/agregar".
     * Muestra la vista "agregar-expediente" para agregar un nuevo expediente.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "agregar-expediente".
     */
    @GetMapping("/expediente/agregar")
    public String agregarExpediente(Model model) {

        return "agregar-expediente";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/expediente/agregar/form" para
     * agregar un nuevo expediente.
     *
     * @param model                   El modelo de datos para la vista.
     * @param cedulaPaciente          La cédula del paciente asociado al expediente.
     * @param fecha                   La fecha del expediente.
     * @param padecimiento            El padecimiento registrado en el expediente.
     * @param procedimiento_realizado El procedimiento realizado en el expediente.
     * @param medicamentos            Los medicamentos recetados en el expediente.
     * @return El nombre de la vista "expedientePaciente" después de agregar el
     *         expediente.
     */
    @PostMapping("/expediente/agregar/form")
    public String gregarExpedienteForm(Model model,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("fecha") String fecha,
            @RequestParam("padecimiento") String padecimiento,
            @RequestParam("procedimiento_realizado") String procedimiento_realizado,
            @RequestParam("medicamentos") String medicamentos) {

        // validar que los datos ingresados sean no sean vacios
        if (cedulaPaciente.equals("") || fecha.equals("") || padecimiento.equals("")
                || procedimiento_realizado.equals("")
                || medicamentos.equals("")) {
            model.addAttribute("mensajeError",
                    "Error al ingresar los datos, asegurese de llenar todos los campos");
            return "agregar-expediente";
        }
        Paciente paciente = pacienteService.findByCedula(cedulaPaciente);
        if (paciente == null) {
            model.addAttribute("mensajeError", "No se encontro un paciente con la cedula ingresada");
            return "agregar-expediente";
        }

        Expediente expediente = new Expediente();
        expediente.setCedula(cedulaPaciente);
        expediente.setPadecimiento(padecimiento);
        expediente.setProcedimiento_realizado(procedimiento_realizado);
        expediente.setMedicamentos(medicamentos);
        expediente.setFecha(fecha);

        // guardar expediente
        expedienteService.saveExpediente(expediente);

        return "expedientePaciente";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/expediente/borrar/{expedienteId}"
     * para mostrar la página de confirmación
     * de eliminación de un expediente.
     *
     * @param model        El modelo de datos para la vista.
     * @param idExpediente El identificador único del expediente que se desea
     *                     eliminar.
     * @return El nombre de la vista "borrar-expediente" con los detalles del
     *         expediente a eliminar.
     */
    @GetMapping("/expediente/borrar/{expedienteId}")
    public String borrarExpediente(Model model,
            @PathVariable("expedienteId") long idExpediente) {

        Expediente expediente = expedienteService.getExpedienteById(idExpediente);
        model.addAttribute("expediente", expediente);
        return "borrar-expediente";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/expediente/borrar/form" para
     * eliminar un expediente.
     *
     * @param model          El modelo de datos para la vista.
     * @param idExpediente   El identificador único del expediente que se desea
     *                       eliminar.
     * @param cedulaPaciente La cédula del paciente al que pertenece el expediente.
     * @return Redirige a la página "expedientePaciente" después de eliminar el
     *         expediente o muestra un mensaje de error si no es posible realizar la
     *         eliminación.
     */
    @PostMapping("/expediente/borrar/form")
    public String borrarExpedienteForm(Model model,
            @RequestParam("idExpediente") long idExpediente,
            @RequestParam("cedulaPaciente") String cedulaPaciente) {

        Expediente expediente = expedienteService.getExpedienteById(idExpediente);
        if (expediente == null) {
            model.addAttribute("mensajeError", "No se encontro un expediente con el id ingresado");
            return "borrar-expediente";
        }

        if (!expediente.getCedula().equals(cedulaPaciente)) {
            model.addAttribute("mensajeError", "El expediente no pertenece al paciente con la cedula ingresada");
            return "borrar-expediente";
        }

        expedienteService.deleteExpediente(idExpediente);
        return "expedientePaciente";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/expediente/editar/{expedienteId}"
     * para editar un expediente.
     *
     * @param model        El modelo de datos para la vista.
     * @param idExpediente El identificador único del expediente que se desea
     *                     editar.
     * @return La vista "editar-expediente" con los detalles del expediente a
     *         editar.
     */
    @GetMapping("/expediente/editar/{expedienteId}")
    public String editarExpediente(Model model,
            @PathVariable("expedienteId") long idExpediente) {

        Expediente expediente = expedienteService.getExpedienteById(idExpediente);
        model.addAttribute("expediente", expediente);
        return "editar-expediente";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/expediente/editar/form" para
     * actualizar los detalles de un expediente.
     *
     * @param model                   El modelo de datos para la vista.
     * @param idExpediente            El identificador único del expediente que se
     *                                desea editar.
     * @param cedulaPaciente          La cédula del paciente asociado al expediente.
     * @param fecha                   La fecha de registro del expediente.
     * @param padecimiento            El padecimiento registrado en el expediente.
     * @param procedimiento_realizado El procedimiento realizado registrado en el
     *                                expediente.
     * @param medicamentos            Los medicamentos recetados registrados en el
     *                                expediente.
     * @return La vista "expedientePaciente" después de actualizar el expediente en
     *         la base de datos.
     */
    @PostMapping("/expediente/editar/form")
    public String editarExpedinteForm(Model model,
            @RequestParam("idExpediente") long idExpediente,
            @RequestParam("cedulaPaciente") String cedulaPaciente,
            @RequestParam("fecha") String fecha,
            @RequestParam("padecimiento") String padecimiento,
            @RequestParam("procedimiento_realizado") String procedimiento_realizado,
            @RequestParam("medicamentos") String medicamentos) {

        // validar que ningun dato sea vacio
        if (cedulaPaciente.equals("") || fecha.equals("") || padecimiento.equals("")
                || procedimiento_realizado.equals("")
                || medicamentos.equals("")) {
            model.addAttribute("mensajeError",
                    "Error al ingresar los datos, asegurese de llenar todos los campos");
            return "redirect:/expediente/editar/" + idExpediente;
        }

        // validar que el expediente exista
        Expediente expediente = expedienteService.getExpedienteById(idExpediente);
        if (expediente == null) {
            model.addAttribute("mensajeError", "No se encontro un expediente con el id ingresado");
            return "redirect:/expediente/editar/" + idExpediente;
        }
        // validar que el expediente pertenezca al paciente
        if (!expediente.getCedula().equals(cedulaPaciente)) {
            model.addAttribute("mensajeError", "El expediente no pertenece al paciente con la cedula ingresada");
            return "redirect:/expediente/editar/" + idExpediente;
        }

        expediente.setPadecimiento(padecimiento);
        expediente.setProcedimiento_realizado(procedimiento_realizado);
        expediente.setMedicamentos(medicamentos);
        expediente.setFecha(fecha);

        expedienteService.updateExpediente(expediente);

        return "expedientePaciente";
    }

}
