import java.util.HashSet;
import java.util.Set;

/**
 * Proporciona estrategias para detectar si un arreglo contiene duplicados.
 */
public class DuplicadosVector {

    /**
     * Determina si el arreglo contiene al menos un valor repetido comparando parejas.
     *
     * @param numeros arreglo de enteros que se va a revisar
     * @return true si existe algun duplicado; false en caso contrario
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(n^2) en tiempo y O(1) en espacio auxiliar.
     */
    public static boolean contieneDuplicadosConCiclos(int[] numeros) {
        validarArreglo(numeros);

        for (int indice = 0; indice < numeros.length - 1; indice++) {
            for (int siguiente = indice + 1; siguiente < numeros.length; siguiente++) {
                if (numeros[indice] == numeros[siguiente]) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Determina si el arreglo contiene al menos un valor repetido usando un conjunto.
     *
     * @param numeros arreglo de enteros que se va a revisar
     * @return true si existe algun duplicado; false en caso contrario
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(n) esperada en tiempo y O(n) en espacio auxiliar.
     */
    public static boolean contieneDuplicadosConHashSet(int[] numeros) {
        validarArreglo(numeros);

        Set<Integer> valoresVistos = new HashSet<>();
        for (int numero : numeros) {
            if (!valoresVistos.add(numero)) {
                return true;
            }
        }

        return false;
    }

    private static void validarArreglo(int[] numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }
    }

    /** Demuestra ambas implementaciones con arreglos con y sin valores repetidos. */
    public static void main(String[] args) {
        int[] conDuplicados = {8, -3, 5, 8, 0};
        int[] sinDuplicados = {8, -3, 5, 0};

        mostrarResultados("Arreglo con duplicados", conDuplicados);
        mostrarResultados("Arreglo sin duplicados", sinDuplicados);
    }

    private static void mostrarResultados(String descripcion, int[] numeros) {
        System.out.println(descripcion + ":");
        System.out.println("  Ciclos anidados: " + contieneDuplicadosConCiclos(numeros));
        System.out.println("  HashSet: " + contieneDuplicadosConHashSet(numeros));
    }
}
