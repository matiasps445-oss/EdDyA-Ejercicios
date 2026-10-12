import java.util.Scanner;

/**
 * Busca secuencialmente un entero en un arreglo sin modificarlo.
 */
public class BusquedaSecuencialVector {

    /**
     * Resultado de una busqueda secuencial.
     */
    public static final class ResultadoBusqueda {
        private final int indice;
        private final int posicionesRecorridas;

        private ResultadoBusqueda(int indice, int posicionesRecorridas) {
            this.indice = indice;
            this.posicionesRecorridas = posicionesRecorridas;
        }

        public int getIndice() {
            return indice;
        }

        public int getPosicionesRecorridas() {
            return posicionesRecorridas;
        }
    }

    /**
     * Devuelve el indice de la primera coincidencia y cuantas posiciones inspecciono.
     * Si el objetivo no aparece, el indice del resultado es -1.
     *
     * @param numeros arreglo de enteros que se va a buscar
     * @param objetivo valor que se desea encontrar
     * @return indice de la primera coincidencia y cantidad de posiciones inspeccionadas
     * @throws IllegalArgumentException si el arreglo es null o esta vacio
     * Complejidad: O(n) en tiempo y O(1) en espacio auxiliar.
     */
    public static ResultadoBusqueda buscar(int[] numeros, int objetivo) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser null ni estar vacio.");
        }

        int posicionesRecorridas = 0;
        for (int indice = 0; indice < numeros.length; indice++) {
            posicionesRecorridas++;
            if (numeros[indice] == objetivo) {
                return new ResultadoBusqueda(indice, posicionesRecorridas);
            }
        }

        return new ResultadoBusqueda(-1, posicionesRecorridas);
    }

    /** Ejecuta la busqueda con datos ingresados por consola. */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int tamano = leerTamanoPositivo(scanner);
            int[] numeros = new int[tamano];

            for (int indice = 0; indice < numeros.length; indice++) {
                numeros[indice] = leerEntero(scanner, "Ingrese el elemento " + (indice + 1) + ": ");
            }

            int objetivo = leerEntero(scanner, "Ingrese el valor que desea buscar: ");
            ResultadoBusqueda resultado = buscar(numeros, objetivo);

            if (resultado.getIndice() == -1) {
                System.out.println("El valor no se encontro en el arreglo.");
            } else {
                System.out.println("El valor se encontro en el indice " + resultado.getIndice() + ".");
            }
            System.out.println("Posiciones inspeccionadas: " + resultado.getPosicionesRecorridas());
        }
    }

    private static int leerTamanoPositivo(Scanner scanner) {
        while (true) {
            int tamano = leerEntero(scanner, "Ingrese un tamano positivo para el arreglo: ");
            if (tamano > 0) {
                return tamano;
            }
            System.out.println("El tamano debe ser mayor que cero.");
        }
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("No hay mas datos disponibles en la entrada.");
            }

            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException excepcion) {
                System.out.println("Entrada no valida. Ingrese un numero entero.");
            }
        }
    }
}
