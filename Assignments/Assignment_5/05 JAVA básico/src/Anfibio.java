
public class Anfibio extends Animal {

    private static int contadorAnfibios = 0;

    public Anfibio(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro,
            String enfermedades) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro, enfermedades);
        ++contadorAnfibios;
    }

    public Anfibio(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro) {
        super(nombre, tipoEspecie, edad, duenno, nivelPeligro);
        ++contadorAnfibios;
    }

    @Override
    public String informacionAnimal() {
        String mensaje = "";
        mensaje += "El nombre del animal es " + this.getNombre() + "\n";
        mensaje += "Pertenece a los anfibios " + "\n";
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
        System.out.println(nombre + " que pertenecea los  Anfibios pueden caminar o saltar.");
    }

    public static String getContadorAnfibios() {
        String mensaje = "El número de Anfibios es de " + contadorAnfibios;
        return mensaje;
    }

}
