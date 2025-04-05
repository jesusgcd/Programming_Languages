// Creación de la clase Test
public class Test {
    public static void main(String[] args) {

        // Creación de objetos de ejemplo para cada clase
        Vertebrado vert_perro = new Vertebrado("Paco", "Perro", 5, "Juan", "Alto", "Ninguna");
        Vertebrado vert_gato = new Vertebrado("Pepito", "Gato", 3, "Pedro", "Medio");

        Anfibio anfi_rana = new Anfibio("Rana", "Rana", 1, "Juan", "Bajo", "Ninguna");
        Anfibio anfi_sapo = new Anfibio("Peta", "Sapo", 2, "Pedro", "Bajo");

        Reptil rept_cocodrilo = new Reptil("Coco", "Cocodrilo", 10, "Juan", "Alto", "Ninguna");
        Reptil rept_tortuga = new Reptil("Tortu", "Tortuga", 5, "Pedro", "Medio");

        Insecto inse_abeja = new Insecto("Abeja", "Abeja", 1, "Juan", "Bajo", "Ninguna");
        Insecto inse_mosca = new Insecto("Mosca", "Mosca", 1, "Pedro", "Bajo");

        Aranna aranna = new Aranna("Araña", "Araña", 1, "Juan", "Extremo, matar a cualquier costo",
                "Existir y poner huevos");

        // Uso de los metodos abstractos implementados en cada clase

        System.out.println("\n" + "Ejemplo uso de los metodos abstractos implementados en cada clase:" + "\n");

        System.out.println(vert_perro.informacionAnimal());
        System.out.println(rept_cocodrilo.informacionAnimal());

        System.out.println(anfi_sapo.informacionAnimal());
        System.out.println(inse_abeja.informacionAnimal());

        System.out.println(aranna.informacionAnimal());

        // ejemplo de uso polimosfismo por sobrecarga de metodos
        System.out.println("\n" + "Ejemplo de uso polimosfismo por sobrecarga de metodos:" + "\n");
        System.out.println(vert_perro.convercionEdad());
        System.out.println(vert_perro.convercionEdad(7));

        System.out.println(anfi_rana.convercionEdad());
        System.out.println(anfi_rana.convercionEdad(2));

        System.out.println(anfi_sapo.convercionEdad());
        System.out.println(anfi_sapo.convercionEdad(2));

        // ejemplo de uso polimosfismo por herencia
        System.out.println("\n" + "Ejemplo de uso polimosfismo por herencia:" + "\n");

        vert_gato.moverse();
        rept_tortuga.moverse();
        inse_mosca.moverse();

        // uso de los metodos estaticos
        System.out.println("\n" + "Ejemplo uso de los metodos estaticos:" + "\n");
        System.out.println(Animal.getContadorAnimales());
        System.out.println(Vertebrado.getContadorVertebrados());
        System.out.println(Anfibio.getContadorAnfibios());
        System.out.println(Reptil.getContadorReptiles());
        System.out.println(Insecto.getContadorInsectos());
        System.out.println(Aranna.getContadorArannas());

    }
}