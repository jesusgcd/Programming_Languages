package com.IC4700;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/*
 * Clase App que inicia la aplicacion
 * 
 * @version 1.0 - 14/10/2023
 * @autores: 
 *         William Alfaro Quirós – 2022437996
 *         Jesus Cordero Díaz – 2020081049
 */
@SpringBootApplication
@EntityScan("com.IC4700.model")
public class App {

    /*
     * Inicia la aplicacion
     * 
     * @param args Argumentos de la aplicacion
     * 
     * @return Ninguno
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
