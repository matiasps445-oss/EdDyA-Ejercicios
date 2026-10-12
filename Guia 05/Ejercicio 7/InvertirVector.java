import java.util.Arrays;

/**
 * Demuestra dos estrategias para invertir arreglos de enteros.
 */
public class InvertirVector {

    /**
     * Devuelve un arreglo nuevo con los elementos en orden inverso.
     * El arreglo recibido no se modifica.
     *
     * @param numeros arreglo que se va a invertir
     * @return un nuevo arreglo con los elementos en orden inverso
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(n) en tiempo y O(n) en espacio auxiliar.
     */
    public static int[] invertirConAuxiliar(int[] numeros) {
        validarArreglo(numeros);

        int[] invertido = new int[numeros.length];
        for (int indice = 0; indice < numeros.length; indice++) {
            invertido[indice] = numeros[numeros.length - 1 - indice];
        }

        return invertido;
    }

    /**
     * Invierte el arreglo recibido mediante intercambios, sin crear otro arreglo.
     *
     * @param numeros arreglo que se modifica en el mismo sitio
     * @throws IllegalArgumentException si el arreglo es null
     * Complejidad: O(n) en tiempo y O(1) en espacio auxiliar.
     */
    public static void invertirInSitu(int[] numeros) {
        validarArreglo(numeros);

        for (int inicio = 0, fin = numeros.length - 1; inicio < fin; inicio++, fin--) {
            int temporal = numeros[inicio];
            numeros[inicio] = numeros[fin];
            numeros[fin] = temporal;
        }
    }

    private static void validarArreglo(int[] numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }
    }

    /** Compara los resultados de las dos estrategias con arreglos independientes. */
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        int[] copiaParaInvertirInSitu = original.clone();

        System.out.println("Arreglo original: " + Arrays.toString(original));

        int[] invertidoConAuxiliar = invertirConAuxiliar(original);
        System.out.println("Resultado con vector auxiliar: " + Arrays.toString(invertidoConAuxiliar));
        System.out.println("Original despues de usar auxiliar: " + Arrays.toString(original));

        invertirInSitu(copiaParaInvertirInSitu);
        System.out.println("Resultado in situ: " + Arrays.toString(copiaParaInvertirInSitu));
    }
}
