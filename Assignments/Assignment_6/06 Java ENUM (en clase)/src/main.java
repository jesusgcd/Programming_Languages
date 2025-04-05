
// integrantes del equipo:
// 1.- Yarman Andres Charpentier Castellon 2022363082
// 2.- Valery Mishel Carvajal Oreamuno 2022314299
// 3.- Rohi Daniel Prendas Regalado 2019052258
// 4.- Jesus Gabriel Cordero Diaz  2020081049

public class main {

    enum Cafe {
        // Fuerza, tiempo de preparación en segundos y cafeina en mg
        ESPRESSO("Fuerte", 30, 80),
        AMERICANO("Suave", 120, 154),
        LATTE("Cremoso", 180, 37),
        CAPPUCCINO("Espumoso", 150, 65);

        private final String fuerza;
        private final int tiempoPreparacion;
        private final int cafeina;
    
        Cafe(String fuerza, int tiempoPreparacion, int cafeina) {
            this.fuerza = fuerza;
            this.tiempoPreparacion = tiempoPreparacion;
            this.cafeina = cafeina;
        }

        public String getFuerza() {
            return fuerza;
        }

        public int getTiempoPreparacion() {
            return tiempoPreparacion;
        }

        public String calculaCafeina(int cafeina, int cantCafeina, String cafe) {
            return "Usted necesita " + (cantCafeina / cafeina ) + " tasas de " + cafe + " para alcanzar lo que necesita";
        }

        public String calculaTiempo(Cafe miCafe) {
            return "Usted necesita " + (miCafe.getTiempoPreparacion() / 60) + " minutos para preparar su " + miCafe;
        }

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public static void main( String[] args) {
        Cafe miCafe = Cafe.LATTE;

        System.out.println("Pedí un " + miCafe);
        System.out.println("Es " + miCafe.getFuerza() + " y tarda aproximadamente " + miCafe.getTiempoPreparacion() + " segundos en prepararse.");
        
        System.out.println(miCafe.calculaCafeina(miCafe.cafeina, 100, miCafe.toString()));
        System.out.println(miCafe.calculaTiempo(miCafe));
    }
}
