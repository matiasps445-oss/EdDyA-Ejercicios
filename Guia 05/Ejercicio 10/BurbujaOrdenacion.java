/**
 * Ordena un arreglo de enteros mediante Bubble Sort y cuenta comparaciones e intercambios.
 * El enfoque se mantiene didactico y claro, como corresponde a un ejercicio de algoritmos.
 */
public class BurbujaOrdenacion {

    /**
     * Resultado del proceso de ordenacion: arreglo ordenado y contadores de comparaciones/intercambios.
     */
    public static class ResultadoOrdenacion {
        private final int[] arregloOrdenado;
        private final int comparaciones;
        private final int intercambios;

        public ResultadoOrdenacion(int[] arregloOrdenado, int comparaciones, int intercambios) {
            this.arregloOrdenado = arregloOrdenado;
            this.comparaciones = comparaciones;
            this.intercambios = intercambios;
        }

        public int[] getArregloOrdenado() {
            return arregloOrdenado;
        }

        public int getComparaciones() {
            return comparaciones;
        }

        public int getIntercambios() {
            return intercambios;
        }
    }

    /**
     * Ordena el arreglo en orden ascendente usando Bubble Sort.
     * Ademas, devuelve el numero de comparaciones e intercambios realizados.
     *
     * @param numeros arreglo de enteros a ordenar
     * @return un objeto con el arreglo ordenado y sus contadores
     * @throws IllegalArgumentException si el arreglo es null
     */
    public static ResultadoOrdenacion ordenarBurbuja(int[] numeros) {
        if (numeros == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null.");
        }

        // Copiamos el arreglo para no modificar la referencia original y para poder
        // devolver una version ordenada sin perder el contenido original.
        int[] arregloOrdenado = numeros.clone();
        int comparaciones = 0;
        int intercambios = 0;

        // Bubble Sort: se recorren varias pasadas. En cada pasada, el mayor valor
        // queda al final del segmento no ordenado.
        for (int i = 0; i < arregloOrdenado.length - 1; i++) {
            for (int j = 0; j < arregloOrdenado.length - 1 - i; j++) {
                comparaciones++;

                if (arregloOrdenado[j] > arregloOrdenado[j + 1]) {
                    int auxiliar = arregloOrdenado[j];
                    arregloOrdenado[j] = arregloOrdenado[j + 1];
                    arregloOrdenado[j + 1] = auxiliar;
                    intercambios++;
                }
            }
        }

        return new ResultadoOrdenacion(arregloOrdenado, comparaciones, intercambios);
    }

    /**
     * Demostracion del algoritmo con un arreglo desordenado.
     */
    public static void main(String[] args) {
        int[] ejemplo = {5, 1, 4, 2, 8, -3, 7};

        ResultadoOrdenacion resultado = ordenarBurbuja(ejemplo);

        System.out.println("Arreglo original: " + java.util.Arrays.toString(ejemplo));
        System.out.println("Arreglo ordenado: " + java.util.Arrays.toString(resultado.getArregloOrdenado()));
        System.out.println("Comparaciones: " + resultado.getComparaciones());
        System.out.println("Intercambios: " + resultado.getIntercambios());

        // Casos limite: arreglo vacio y arreglo con un elemento.
        int[] vacio = {};
        ResultadoOrdenacion vacioResultado = ordenarBurbuja(vacio);
        System.out.println("Arreglo vacio: " + java.util.Arrays.toString(vacioResultado.getArregloOrdenado()));

        int[] unElemento = {42};
        ResultadoOrdenacion unicoResultado = ordenarBurbuja(unElemento);
        System.out.println("Arreglo de un elemento: " + java.util.Arrays.toString(unicoResultado.getArregloOrdenado()));
    }
}
