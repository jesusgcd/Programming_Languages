package com.IC4700.config;

import com.IC4700.model.Cita;
import com.IC4700.model.Medico;
import com.IC4700.model.Paciente;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Esta clase proporciona una instancia única de SessionFactory para la gestión
 * de sesiones de Hibernate.
 * Utiliza el patrón Singleton para garantizar que solo haya una instancia de
 * SessionFactory en la aplicación.
 * 
 * @version 1.3 - 05/10/2023
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Obtiene la instancia de SessionFactory.
     * Si no existe, crea una nueva utilizando la configuración de Hibernate y las
     * clases anotadas.
     *
     * @return La instancia de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // Configura el registro de servicios estándar para Hibernate.
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            // Crea un origen de metadatos y agrega las clases anotadas.
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            metadataSources.addAnnotatedClass(Medico.class);
            metadataSources.addAnnotatedClass(Paciente.class);
            // metadataSources.addAnnotatedClass(Cita.class); // Descomenta esta línea si
            // necesitas agregar la clase Cita.

            // Construye la instancia de SessionFactory utilizando los metadatos.
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }

        return sessionFactory;
    }
}
