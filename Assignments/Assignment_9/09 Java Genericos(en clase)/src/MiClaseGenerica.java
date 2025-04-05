import java.util.ArrayList;

public class MiClaseGenerica<T> {
    private T atributoGenerico;

    public MiClaseGenerica(T valor) {
        this.atributoGenerico = valor;
    }

    public T getAtributoGenerico() {
        return atributoGenerico;
    }

    public void setAtributoGenerico(T valor) {
        this.atributoGenerico = valor;
    }

  

    public static void main(String[] args) {


        // Crear una instancia de MiClaseGenerica con Integer como tipo genérico
        MiClaseGenerica<Integer> instancia1 = new MiClaseGenerica<>(10);

        // Obtener el valor del atributo genérico
        Integer valor1 = instancia1.getAtributoGenerico();
        System.out.println("Valor de instancia1: " + valor1);

        // Crear una instancia de MiClaseGenerica con String como tipo genérico
        MiClaseGenerica<String> instancia2 = new MiClaseGenerica<>("Hola, mundo!");

        // Obtener el valor del atributo genérico
        String valor2 = instancia2.getAtributoGenerico();
        System.out.println("Valor de instancia2: " + valor2);


        
        // Crear un ArrayList genérico
        ArrayList<Object> listaGenerica = new ArrayList<>();

        // Agregar diferentes tipos de objetos
        listaGenerica.add("Hola");             // Agregar una cadena (String)
        listaGenerica.add(42);                 // Agregar un entero (Integer)
        listaGenerica.add(3.14);               // Agregar un número decimal (Double)
        listaGenerica.add(new ArrayList<>());  // Agregar otro ArrayList

        // Iterar y mostrar los elementos en la lista
        for (Object elemento : listaGenerica) {
            System.out.println(elemento);
        }
    }
}
