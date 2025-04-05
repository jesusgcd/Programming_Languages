/*
 * Instituto Tecnologico de Costa Rica
 *
 * Escuela de Ingenieria en Computacion
 *
 * Lenguajes de Programacion
 *
 * Proyecto 03 - Programación Lógica con Prolog
 *
 * William Alfaro Quiros - 2022437996
 *
 * Jesus Cordero Diaz - 2020081049
 *
 */

import org.jpl7.*;
import java.util.Scanner;

public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Obtener la ruta actual del sistema
        String rutaActual = System.getProperty("user.dir");
        rutaActual = rutaActual.replace("\\", "/");
        // Construir la ruta al archivo Prolog
        String rutaPL = rutaActual + "/src/data/baseConocimineto.pl";
        // Consultar el archivo Prolog
        Query q1 = new Query("consult('" + rutaPL + "')");

        if (q1.hasSolution()) {
            System.out.println("Base de conocimiento cargada correctamente.");

            // Configurar la biblioteca JPL
            Query.hasSolution("use_module(library(jpl))");
            Query.hasSolution("set_prolog_flag(legacy_char_classification, false)");

            Scanner scanner = new Scanner(System.in);

            OUTER: while (true) {
                System.out.println("\nMenu de opciones:");
                System.out.println("1. Ver lista de infectados");
                System.out.println("2. Ver si una persona esta infectada");
                System.out.println("3. Ver si una persona podría estar infectada");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opcion: ");
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> {
                        // Consultar la lista de infectados
                        Query consultaInfectados = new Query("obtener_infectados(Persona)");
                        String listaInfectados = consultaInfectados.oneSolution().get("Persona").toString();
                        System.out.println("Lista de infectados: " + listaInfectados);
                    }
                    case 2 -> {
                        System.out.print("Ingrese el nombre de la persona: ");
                        scanner.nextLine(); // Consumir la nueva línea pendiente
                        String nombre = scanner.nextLine();
                        // Consultar si una persona está infectada o puede infectar
                        Query consultaInfectados1 = new Query("esta_infectado(" + nombre + ")");
                        Query consultaInfectados2 = new Query("puede_infectar(" + nombre + ")");

                        if (consultaInfectados1.hasSolution() || consultaInfectados2.hasSolution()) {
                            System.out.println(nombre + " está infectado.");
                        } else {
                            System.out.println(nombre + " no está infectado.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Ingrese el nombre de la persona a verificar: ");
                        scanner.nextLine();
                        String nombre = scanner.nextLine();

                        System.out.print("¿La persona tiene síntomas de COVID-19? (s/n): ");
                        String tieneSintomas = scanner.nextLine();

                        boolean tieneSintomasCovid = false;

                        if (tieneSintomas.equalsIgnoreCase("s")) {
                            // Obtener los síntomas de la base de conocimiento
                            Query sintomasListas = new Query("obtener_sintomas(Sintomas)");
                            String[] listaSB = sintomasListas.oneSolution().get("Sintomas").toString().split(",");
                            System.out.println("Síntomas más comunes: "
                                    + String.join(", ", listaSB[0], listaSB[1], listaSB[2], listaSB[3] + "]"));
                            System.out.print(
                                    "Ingrese los síntomas separados por comas (ejemplo: " + listaSB[1] + ","
                                            + listaSB[2] + "): ");
                            String sintomas = scanner.nextLine();
                            String[] listaSintomas = sintomas.split(", ");

                            for (String sintoma : listaSintomas) {
                                // Consultar si la persona tiene un síntoma
                                Query consultaSintoma = new Query("con_sintoma(" + sintoma + ").");
                                if (consultaSintoma.hasSolution()) {
                                    tieneSintomasCovid = true;
                                    break;
                                }
                            }
                        }
                        System.out.print("¿La persona tiene factores de riesgo para COVID-19? (s/n): ");
                        String tieneRiesgo = scanner.nextLine();

                        boolean tieneRiesgoCovid = false;

                        if (tieneRiesgo.equalsIgnoreCase("s")) {
                            // Obtener los factores de riesgo de la base de conocimiento
                            Query riesgosListas = new Query("obtener_factores_de_riesgo(Factores)");
                            String[] listaFR = riesgosListas.oneSolution().get("Factores").toString().split(",");
                            System.out.println("Factores de riesgo más comunes: "
                                    + String.join(", ", listaFR[0], listaFR[1], listaFR[2], listaFR[3] + "]"));
                            System.out.print(
                                    "Ingrese los factores de riesgo separados por comas (ejemplo: " + listaFR[1] + ","
                                            + listaFR[2] + "): ");
                            String riesgos = scanner.nextLine();
                            String[] listaRiesgos = riesgos.split(", ");

                            for (String riesgo : listaRiesgos) {
                                // Consultar si la persona tiene un factor de riesgo
                                Query consultaRiesgo = new Query("con_factor_riesgo(" + riesgo + ")");
                                if (consultaRiesgo.hasSolution()) {
                                    tieneRiesgoCovid = true;
                                    break;
                                }
                            }
                        }

                        System.out.print("¿La persona ha visitado países con un alto índice de contagio? (s/n): ");
                        String nomPais = scanner.nextLine();
                        System.out.println(nomPais);

                        boolean visitoPais = false;

                        if (nomPais.equalsIgnoreCase("s")) {
                            // Obtener la lista de países con alto índice de contagio
                            Query listaVP = new Query("obtener_paises_con_alto_indice_de_contagio(Pais)");
                            String[] listaPaises = listaVP.oneSolution().get("Pais").toString().split(",");
                            System.out.println("Países más comunes: "
                                    + String.join(", ", listaPaises[0], listaPaises[1], listaPaises[2],
                                            listaPaises[3] + "]"));
                            System.out.print("Ingrese los países separados por comas (ejemplo: "
                                    + listaPaises[1] + ","
                                    + listaPaises[2] + "): ");
                            String paisVisitado = scanner.nextLine();
                            String[] listaPaisesVisitados = paisVisitado.split(", ");

                            for (String pais : listaPaisesVisitados) {
                                // Consultar si la persona ha visitado un país con alto índice de contagio
                                Query consultaPais = new Query(
                                        "es_pais_con_alto_indice_de_contagio(" + pais + ")");
                                if (consultaPais.hasSolution()) {
                                    visitoPais = true;
                                    break;
                                }
                            }
                        }

                        System.out.print("¿La persona ha visitado provincias con un alto índice de contagio? (s/n): ");
                        String nomProvincia = scanner.nextLine();
                        System.out.println(nomProvincia);

                        boolean visitoProvincia = false;

                        if (nomProvincia.equalsIgnoreCase("s")) {
                            // Obtener la lista de provincias con alto índice de contagio
                            Query listaVProv = new Query("obtener_provincias_con_alto_indice_de_contagio(Provincia)");
                            String[] listaProvincias = listaVProv.oneSolution().get("Provincia").toString().split(",");
                            System.out.println("Provincias más comunes: "
                                    + String.join(", ", listaProvincias[1], listaProvincias[2] + "]"));
                            System.out.print("Ingrese las provincias separadas por comas (ejemplo: "
                                    + listaProvincias[1] + ","
                                    + listaProvincias[2] + "): ");
                            String provinciaVisitada = scanner.nextLine();
                            String[] listaProvinciasVisitadas = provinciaVisitada.split(", ");

                            for (String provincia : listaProvinciasVisitadas) {
                                // Consultar si la persona ha visitado una provincia con alto índice de contagio
                                Query consultaProvincia = new Query(
                                        "es_provincia_con_alto_indice_de_contagio(" + provincia + ")");
                                if (consultaProvincia.hasSolution()) {
                                    visitoProvincia = true;
                                    break;
                                }
                            }
                        }

                        System.out.print("¿La persona ha visitado cantones con un alto índice de contagio? (s/n): ");
                        String nomCantones = scanner.nextLine();
                        System.out.println(nomCantones);

                        boolean visitoCantones = false;

                        if (nomCantones.equalsIgnoreCase("s")) {
                            // Obtener la lista de cantones con alto índice de contagio
                            Query listaDist = new Query("obtener_cantones_con_alto_indice_de_contagio(Cantones)");
                            String[] listaCantones = listaDist.oneSolution().get("Cantones").toString().split(",");
                            System.out.println("Cantones más comunes: "
                                    + String.join(", ", listaCantones[1], listaCantones[2] + "]"));
                            System.out.print("Ingrese los cantones separados por comas (ejemplo: "
                                    + listaCantones[1] + ", "
                                    + listaCantones[2] + "): ");
                            String cantonesVisitados = scanner.nextLine();
                            String[] listaCantonesVisitados = cantonesVisitados.split(", ");

                            for (String cantones : listaCantonesVisitados) {
                                // Consultar si la persona ha visitado un cantón con alto índice de contagio
                                Query consultaCantones = new Query(
                                        "es_canton_con_alto_indice_de_contagio(" + cantones + ")");
                                if (consultaCantones.hasSolution()) {
                                    visitoCantones = true;
                                    break;
                                }
                            }
                        }

                        System.out.print("¿La persona ha visitado distritos con un alto índice de contagio? (s/n): ");
                        String nomDistritos = scanner.nextLine();
                        System.out.println(nomDistritos);

                        boolean visitoDistritos = false;

                        if (nomDistritos.equalsIgnoreCase("s")) {
                            // Obtener la lista de distritos con alto índice de contagio
                            Query listaVProv = new Query("obtener_distritos_con_alto_indice_de_contagio(Distritos)");
                            String[] listaDistritos = listaVProv.oneSolution().get("Distritos").toString().split(",");
                            System.out.println("Distritos más comunes: "
                                    + String.join(", ", listaDistritos[1], listaDistritos[2] + "]"));
                            System.out.print("Ingrese los distritos separados por comas (ejemplo: "
                                    + listaDistritos[1] + ", "
                                    + listaDistritos[2] + "): ");
                            String distritosVisitados = scanner.nextLine();
                            String[] listaDistritosVisitados = distritosVisitados.split(", ");

                            for (String distritos : listaDistritosVisitados) {
                                // Consultar si la persona ha visitado un distrito con alto índice de contagio
                                Query consultaDistritos = new Query(
                                        "es_distrito_con_alto_indice_de_contagio(" + distritos + ")");
                                if (consultaDistritos.hasSolution()) {
                                    visitoDistritos = true;
                                    break;
                                }
                            }
                        }

                        System.out.print("¿La persona ha tomado medidas preventivas contra el COVID-19? (s/n): ");
                        String tomoMediPreventivas = scanner.nextLine();

                        boolean tieneMP = true;

                        if (tomoMediPreventivas.equalsIgnoreCase("s")) {
                            // Obtener la lista de medidas preventivas
                            Query listaMP = new Query("obtener_medidas_preventivas(Medidas)");
                            String[] listaMedidas = listaMP.oneSolution().get("Medidas").toString().split(",");
                            System.out.println("Medidas preventivas más comunes: "
                                    + String.join(", ", listaMedidas[0], listaMedidas[1], listaMedidas[2],
                                            listaMedidas[3] + "]"));
                            System.out.print("Ingrese las medidas preventivas separadas por comas (ejemplo: "
                                    + listaMedidas[1] + ","
                                    + listaMedidas[2] + "): ");
                            String medidas = scanner.nextLine();
                            String[] listaMedidasPreventivas = medidas.split(", ");

                            for (String medida : listaMedidasPreventivas) {
                                // Consultar si la persona ha tomado una medida preventiva
                                Query consultaMedida = new Query("medida_preventiva(" + medida + ")");
                                if (consultaMedida.hasSolution()) {
                                    tieneMP = false;
                                    break;
                                }
                            }
                        }

                        String sms = (tieneSintomasCovid || tieneRiesgoCovid || visitoPais || visitoProvincia
                                || visitoCantones || visitoDistritos)
                                    ? " podría estar infectado."
                                    : " no tiene indicios de COVID-19";

                        sms = tieneMP ? ", podría estar infectado al no haber implementado medidas preventivas."
                                : ", ha implementado medidas preventivas, podría no estar infectado, pero se recomienda hacerse la prueba.";
                        System.out.println(nombre + sms);
                    }

                    case 4 -> {
                        break OUTER;
                    }
                    default ->
                        System.out.println("Opcion no valida. Intente nuevamente.");
                }
            }
        } else {
            System.out.println("Error al cargar la base de conocimiento en Prolog.");
        }
    }
}
