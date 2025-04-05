package com.IC4700.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las solicitudes relacionadas con la página de
 * inicio.
 * 
 * @version 1.0 - 30/09/2023
 */
@Controller
public class ContralardorIndex {

    /**
     * Maneja las solicitudes GET en la ruta "/" y devuelve la vista "index".
     *
     * @return El nombre de la vista "index" que se mostrará al usuario.
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

}
