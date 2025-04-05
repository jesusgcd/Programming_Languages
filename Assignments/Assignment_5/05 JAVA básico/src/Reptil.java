

public class Reptil extends Animal {

    private static int contadorReptils = 0;

    public Reptil(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro,
            String enfermedades) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro, enfermedades);
        ++contadorReptils;
    }

    public Reptil(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro);
        ++contadorReptils;
    }

    @Override
    public String informacionAnimal() {
        String mensaje = "";
        mensaje += "El nombre del animal es " + this.getNombre() + "\n";
        mensaje += "Pertenece a los Reptiles " + "\n";
        mensaje += "Pertenece a la especie " + this.getTipoEspecie() + "\n";
        mensaje += "La edad del animal es " + this.getEdad() + " años humanos" + "\n";
        mensaje += "El dueño del animal es " + this.getDuenno() + "\n";
        mensaje += "El nivel de peligro del animal es " + this.getNivelPeligro() + "\n";
        mensaje += "Las enfermedades del animal son " + super.getEnfermedad() + "\n";
        return mensaje;
    }

    @Override
    public void moverse() {
        String nombre = this.getNombre();
        System.out.println(nombre + " que pertenece a los  Reptiles pueden caminar o saltar.");
    }

    public static String getContadorReptiles() {
        String mensaje = "El número de Reptils es de " + contadorReptils;
        return mensaje;
    }

}
