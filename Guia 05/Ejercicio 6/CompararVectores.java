import java.util.Arrays;

/**
 * Compara arreglos de enteros por longitud y contenido.
 */
public class CompararVectores {

    /**
     * Indica si dos arreglos tienen la misma longitud y los mismos valores en orden.
     *
     * @param primero primer arreglo que se va a comparar
     * @param segundo segundo arreglo que se va a comparar
     * @return true si ambos arreglos tienen el mismo contenido; false en caso contrario
     * @throws IllegalArgumentException si alguno de los arreglos es null
     * Complejidad: O(n) en el peor caso y O(1) en espacio auxiliar.
     */
    public static boolean sonIguales(int[] primero, int[] segundo) {
        if (primero == null || segundo == null) {
            throw new IllegalArgumentException("Los arreglos no pueden ser null.");
        }

        if (primero.length != segundo.length) {
            return false;
        }

        for (int indice = 0; indice < primero.length; indice++) {
            if (primero[indice] != segundo[indice]) {
                return false;
            }
        }

        return true;
    }

    /** Demuestra comparaciones de arreglos iguales y diferentes. */
    public static void main(String[] args) {
        int[] primeroIgual = {3, -1, 8, 4};
        int[] segundoIgual = {3, -1, 8, 4};
        int[] primeroDiferente = {3, 7, 8, 4};
        int[] segundoDiferente = {3, -1, 8, 4};
        int[] longitudDistinta = {3, -1, 8};
        int[] vacio = {};

        mostrarComparacion("Mismo contenido", primeroIgual, segundoIgual);
        mostrarComparacion("Diferencia en una posicion temprana", primeroDiferente, segundoDiferente);
        mostrarComparacion("Longitudes distintas", primeroIgual, longitudDistinta);
        mostrarComparacion("Dos arreglos vacios", vacio, new int[0]);
    }

    private static void mostrarComparacion(String descripcion, int[] primero, int[] segundo) {
        System.out.println(descripcion + ": " + Arrays.toString(primero)
                + " y " + Arrays.toString(segundo)
                + " -> " + sonIguales(primero, segundo));
    }
}
