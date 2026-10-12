/**
 * Busca un entero en un arreglo ordenado de forma ascendente.
 */
public class BusquedaBinariaVector {

    /**
     * Devuelve el indice de una coincidencia o -1 si el objetivo no aparece.
     * El arreglo debe estar ordenado de forma ascendente.
     *
     * @param numeros arreglo ascendente en el que se realiza la busqueda
     * @param objetivo valor que se desea encontrar
     * @return indice base cero de una coincidencia o -1 si no existe
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(log n) en tiempo y O(1) en espacio auxiliar.
     */
    public static int buscarBinaria(int[] numeros, int objetivo) {
        return buscarBinaria(numeros, objetivo, false);
    }

    /**
     * Realiza la busqueda y puede mostrar el estado de cada iteracion.
     */
    private static int buscarBinaria(int[] numeros, int objetivo, boolean mostrarTraza) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }

        int inicio = 0;
        int fin = numeros.length - 1;
        int iteracion = 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int valorMedio = numeros[medio];

            if (mostrarTraza) {
                System.out.printf(
                        "Iteracion %d: inicio=%d, fin=%d, medio=%d, valor=%d%n",
                        iteracion, inicio, fin, medio, valorMedio);
            }

            if (valorMedio == objetivo) {
                if (mostrarTraza) {
                    System.out.println("Coincidencia: devolver indice " + medio + ".");
                }
                return medio;
            }

            if (valorMedio < objetivo) {
                inicio = medio + 1;
                if (mostrarTraza) {
                    System.out.println("El valor del medio es menor; nuevo inicio=" + inicio + ".");
                }
            } else {
                fin = medio - 1;
                if (mostrarTraza) {
                    System.out.println("El valor del medio es mayor; nuevo fin=" + fin + ".");
                }
            }

            iteracion++;
        }

        if (mostrarTraza) {
            System.out.println("El intervalo quedo vacio; el objetivo no esta en el arreglo.");
        }
        return -1;
    }

    /** Demuestra la busqueda y muestra como se actualiza el intervalo. */
    public static void main(String[] args) {
        int[] numeros = {-8, -3, 0, 4, 9, 15, 22};
        int objetivo = 9;

        System.out.println("Arreglo ordenado: {-8, -3, 0, 4, 9, 15, 22}");
        System.out.println("Objetivo: " + objetivo);

        int indice = buscarBinaria(numeros, objetivo, true);
        if (indice == -1) {
            System.out.println("El valor no se encontro en el arreglo.");
        } else {
            System.out.println("El valor se encontro en el indice " + indice + ".");
        }
    }
}
