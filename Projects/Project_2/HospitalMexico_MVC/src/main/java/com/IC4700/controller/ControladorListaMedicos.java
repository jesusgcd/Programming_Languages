package com.IC4700.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.IC4700.config.HibernateUtil;
import com.IC4700.model.Medico;
import com.IC4700.service.MedicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador para manejar las solicitudes relacionadas con la lista de médicos
 * 
 * @version 1.4 - 08/10/2023
 */
@Controller
public class ControladorListaMedicos {

    @Autowired
    private MedicoService medicosService;
    private List<Medico> medicos;

    /**
     * Maneja las solicitudes GET en la ruta "/ControladorListaMedicos" para listar
     * todos los médicos disponibles en la base de datos.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "listaMedicos" con la lista de médicos obtenida
     *         de la base de datos.
     */
    @GetMapping("/ControladorListaMedicos")
    public String listarMedicos(Model model) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        // Elimina el código para crear y guardar un nuevo paciente
        // Utiliza Hibernate para obtener todos los médicos de la base de datos
        medicos = session.createQuery("FROM Medico", Medico.class).list();

        // Agrega la lista de médicos al modelo para que se muestre en la vista
        model.addAttribute("medicos", medicos);

        return "listaMedicos";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/medicos/editar/{medicoId}" para
     * mostrar el formulario de edición de un médico específico.
     *
     * @param model    El modelo de datos para la vista.
     * @param medicoId El identificador único del médico que se desea editar.
     * @return El nombre de la vista "editar-medico" con el médico cargado en el
     *         modelo.
     */
    @GetMapping("/medicos/editar/{medicoId}")
    public String editarMedico(Model model,
            @PathVariable("medicoId") int medicoId) {
        Medico medico = medicosService.getMedicoById(medicoId);
        model.addAttribute("medico", medico);

        return "editar-medico";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/medicos/editar/form" para procesar
     * la edición de un médico en la base de datos.
     *
     * @param idMedico           El identificador único del médico que se desea
     *                           editar.
     * @param cedulaMedico       La nueva cédula del médico.
     * @param especialidadMedico La nueva especialidad del médico.
     * @param correoMedico       El nuevo correo electrónico del médico.
     * @return Redirecciona a la vista "listaMedicos" después de editar el médico en
     *         la base de datos.
     */
    @PostMapping("/medicos/editar/form")
    public String editarMedicoForm(@RequestParam("idMedico") long idMedico,
            @RequestParam("cedulaMedico") String cedulaMedico,
            @RequestParam("especialidadMedico") String especialidadMedico,
            @RequestParam("correoMedico") String correoMedico) {

        // validar que los datos no estén vacíos
        if (cedulaMedico.equals("") || especialidadMedico.equals("") || correoMedico.equals("")) {
            return "redirect:/medicos/editar/" + idMedico;
        }

        // Buscar el médico en la base de datos
        Medico medico = medicosService.getMedicoById(idMedico);

        if (medico == null) {
            return "redirect:/medicos/editar/" + idMedico;
        }
        // Actualizar los datos del médico
        medico.setCedula(cedulaMedico);
        medico.setEspecialidad(especialidadMedico);
        medico.setCorreo(correoMedico);

        // Guardar los cambios en la base de datos
        medicosService.updateMedico(medico);

        // Regresar a la vista "listaMedicos"
        return "redirect:/ControladorListaMedicos";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/medicos/borrar/{medicoId}" para
     * mostrar el formulario de confirmación de eliminación de un médico.
     *
     * @param model    El modelo de datos para la vista.
     * @param medicoId El identificador único del médico que se desea eliminar.
     * @return La vista "borrar-medico" con los detalles del médico a ser eliminado.
     */
    @GetMapping("/medicos/borrar/{medicoId}")
    public String borrarMedico(Model model,
            @PathVariable("medicoId") int medicoId) {
        Medico medico = medicosService.getMedicoById(medicoId);
        model.addAttribute("medico", medico);

        return "borrar-medico";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/medicos/borrar/form" para eliminar
     * un médico de la base de datos.
     *
     * @param idMedico     El identificador único del médico que se desea eliminar.
     * @param cedulaMedico La cédula del médico (no utilizada en la eliminación).
     * @return Redirecciona a la vista "listaMedicos" después de eliminar el médico.
     */
    @PostMapping("/medicos/borrar/form")
    public String borrarMedicoForm(@RequestParam("idMedico") long idMedico,
            @RequestParam("cedulaMedico") String cedulaMedico) {

        // Borrar el médico de la base de datos
        medicosService.deleteMedico(idMedico);

        // Regresar a la vista "listaMedicos"
        return "redirect:/ControladorListaMedicos";
    }

    /**
     * Maneja las solicitudes GET en la ruta "/medicos/agregar/" para mostrar el
     * formulario de registro de médico.
     *
     * @param model El modelo de datos para la vista.
     * @return El nombre de la vista "agregar-medico" que muestra el formulario de
     *         registro de médico.
     */
    @GetMapping("/medicos/agregar/")
    public String agregarMedico(Model model) {

        return "agregar-medico";
    }

    /**
     * Maneja las solicitudes POST en la ruta "/medicos/crear/form" para crear un
     * nuevo médico.
     *
     * @param model              El modelo de datos para la vista.
     * @param cedulaMedico       La cédula del médico a registrar.
     * @param nombreMedico       El nombre del médico a registrar.
     * @param apellidoMedico     El apellido del médico a registrar.
     * @param especialidadMedico La especialidad del médico a registrar.
     * @param correoMedico       El correo electrónico del médico a registrar.
     * @return El nombre de la vista "ControladorListaMedicos" después de crear
     *         exitosamente un nuevo médico, o "agregar-medico" en caso de error.
     */
    @PostMapping("/medicos/crear/form")
    public String agregarMedicoForm(Model model,
            @RequestParam("cedulaMedico") String cedulaMedico,
            @RequestParam("nombreMedico") String nombreMedico,
            @RequestParam("apellidoMedico") String apellidoMedico,
            @RequestParam("especialidadMedico") String especialidadMedico,
            @RequestParam("correoMedico") String correoMedico) {

        // validar que los datos no estén vacíos
        if (cedulaMedico.equals("") || nombreMedico.equals("") || apellidoMedico.equals("")
                || especialidadMedico.equals("") || correoMedico.equals("")) {
            model.addAttribute("mensajeError", "Todos los campos son obligatorios");
            return "agregar-medico";
        }

        // Crear un nuevo médico
        Medico medico = new Medico();
        medico.setCedula(cedulaMedico);
        medico.setNombre(nombreMedico);
        medico.setApellido(apellidoMedico);
        medico.setEspecialidad(especialidadMedico);
        medico.setCorreo(correoMedico);

        // Guardar el médico en la base de datos
        medicosService.saveMedico(medico);

        return "redirect:/ControladorListaMedicos";
    }

}
