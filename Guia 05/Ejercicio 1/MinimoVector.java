/**
 * Encuentra el valor minimo de un arreglo de enteros mediante un recorrido lineal.
 */
public class MinimoVector {

    /**
     * Devuelve el menor valor del arreglo sin modificarlo.
     *
     * @param numeros arreglo de enteros que se va a evaluar
     * @return el menor valor del arreglo
     * @throws IllegalArgumentException si el arreglo es null o esta vacio
     * Complejidad: O(n) en tiempo y O(1) en espacio adicional.
     */
    public static int encontrarMinimo(int[] numeros) {
        if (numeros == null || numeros.length == 0) {
            throw new IllegalArgumentException("El arreglo no puede ser null ni estar vacio.");
        }

        int minimo = numeros[0];

        // Basta recorrer los elementos una vez y conservar el menor encontrado.
        for (int indice = 1; indice < numeros.length; indice++) {
            if (numeros[indice] < minimo) {
                minimo = numeros[indice];
            }
        }

        return minimo;
    }

    /** Ejecuta una demostracion del metodo con un arreglo definido en el codigo. */
    public static void main(String[] args) {
        int[] numeros = {8, -3, 5, 0, -3};
        int minimo = encontrarMinimo(numeros);

        System.out.println("El valor minimo es: " + minimo);
    }
}