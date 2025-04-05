// integrantes del equipo:

// grupo 2:
// Pablo Venegas Sánchez 
// Alejandro Campos Paredes
// Marco Alvarez Grijalba
// Rohi Prendas Regalado 
// Santiago Ramos Arroyo
// Jesus Cordero Diaz


// Definición de las interfaces
interface Animal {
    String getNombre();
    double getPeso();
    String getAlimentacion();
    String ToString();
}

interface Carnivoro {
    String cazar();
}

interface Herbivoro {
    String pastar();
}

// Clases que implementan las interfaces
class Leon implements Animal, Carnivoro {
    private String nombre;
    private double peso;

    public Leon(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public String getAlimentacion() {
        return "carne";
    }

    @Override
    public String cazar() {
        return "El león caza a su presa.";
    }

    @Override
    public String ToString() {
        String sms = "León: " + this.getNombre() + ", Peso: " + this.getPeso() +
                           ", Alimentacion: " + this.getAlimentacion() + ", Metodo de Alimentacion: " + this.cazar();

        return sms;
    }
}

class Elefante implements Animal, Herbivoro {
    private String nombre;
    private double peso;

    public Elefante(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPeso() {
        return peso;
    }

    @Override
    public String getAlimentacion() {
        return "vegetales";
    }

    @Override
    public String pastar() {
        return "El elefante pasta.";
    }

    @Override
    public String ToString() {
        String sms = "Elefante: " + this.getNombre() + ", Peso: " + this.getPeso() +
                           ", Alimentación: " + this.getAlimentacion() + ", Metodo de Alimentacion: " + this.pastar();

        return sms;
    }
}


// Clase de prueba
public class Test {
    public static void main(String[] args) {
        Leon leon = new Leon("Simba", 200);
        Elefante elefante = new Elefante("Dumbo", 500);


        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(leon.ToString());

        System.out.println(elefante.ToString());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
