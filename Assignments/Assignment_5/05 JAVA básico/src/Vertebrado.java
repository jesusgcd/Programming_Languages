
public class Vertebrado extends Animal {

    private static int contadorVertebrados = 0;

    public Vertebrado(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro,
            String enfermedad) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro, enfermedad);
        ++contadorVertebrados;
    }

    public Vertebrado(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro);
        ++contadorVertebrados;
    }

    @Override
    public String informacionAnimal() {
        String mensaje = "";
        mensaje += "El nombre del animal es " + this.getNombre() + "\n";
        mensaje += "Pertenece a los vertebrados " + "\n";
        mensaje += "Pertenece a la especie " + this.getTipoEspecie() + "\n";
        mensaje += "La edad del animal es " + this.getEdad() + " años humanos" + "\n";
        mensaje += "El dueño del animal es " + this.getDuenno() + "\n";
        mensaje += "El nivel de peligro del animal es " + this.getNivelPeligro() + "\n";
        mensaje += "Las enfermedades del animal son: " + super.getEnfermedad() + "\n";
        return mensaje;
    }

    @Override
    public void moverse() {
        String nombre = this.getNombre();
        System.out.println(nombre + " que pertenece a los vertebrados se desplazan utilizando su sistema esquelético.");
    }

    public static String getContadorVertebrados() {
        String mensaje = "El número de vertebrados es de " + contadorVertebrados;
        return mensaje;
    }

}
