

public abstract class Animal {

    private static int contadorAnimales = 0;
    private int id;
    private String nombre;
    private String tipoEspecie;
    private int edad;
    private String duenno;
    private String nivelPeligro;
    private String enfermedad;

    public Animal(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro, String enfermedad) {
        this.id = ++contadorAnimales;
        this.nombre = nombre;
        this.tipoEspecie = tipoEspecie;
        this.edad = edad;
        this.duenno = duenno;
        this.nivelPeligro = nivelPeligro;
        this.enfermedad = enfermedad;
    }

    public Animal(String nombre, String tipoEspecie, int edad, String duenno, String nivelPeligro) {
        this.id = ++contadorAnimales;
        this.nombre = nombre;
        this.tipoEspecie = tipoEspecie;
        this.edad = edad;
        this.duenno = duenno;
        this.nivelPeligro = nivelPeligro;
        this.enfermedad = "";
    }

    public String convercionEdad() {
        String mensaje = "La edad de " + this.nombre + " es de " + this.edad + " años";
        return mensaje;
    }

    public String convercionEdad(int constanteConvercion) {
        String mensaje = "La edad de " + this.nombre + " es de " + (this.edad * constanteConvercion) + " años";
        return mensaje;
    }

    public void moverse() {
        System.out.println("El animal se mueve de alguna manera.");
    }

    public abstract String informacionAnimal();

    // Getters
    public static String getContadorAnimales() {
        String mensaje = "El número total de animales es de " + contadorAnimales;
        return mensaje;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipoEspecie() {
        return this.tipoEspecie;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getDuenno() {
        return this.duenno;
    }

    public String getNivelPeligro() {
        return this.nivelPeligro;
    }

    public String getEnfermedad() {
        return this.enfermedad;
    }

}
