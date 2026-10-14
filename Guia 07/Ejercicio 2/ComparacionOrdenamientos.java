/*
Quiero implementar en Java un programa que incluya dos métodos: bubbleSort(int[] array) y selectionSort(int[] array), y que cuente las operaciones de cada uno.
El algoritmo Bubble Sort razona comparando elementos vecinos e intercambiándolos si están en el orden incorrecto. En cambio, Selection Sort razona buscando directamente el elemento menor en la parte no ordenada para colocarlo en su posición final. La principal diferencia radica en hacer múltiples intercambios locales (vecinos) versus hacer una búsqueda global del menor para un solo intercambio por pasada.
El programa recibe un arreglo de números enteros desordenados (por ejemplo: {64, 34, 25, 12, 22, 11, 90}).
El programa debe ordenar copias exactas del mismo arreglo original y mostrar por pantalla: el arreglo ordenado, la cantidad de comparaciones realizadas y la cantidad de intercambios realizados por cada algoritmo.
El código debe incluir comentarios indicando exactamente dónde ocurren las comparaciones, dónde los intercambios y dónde se incrementan los contadores.
Para verificar que el resultado sea correcto, se debe comprobar en la consola que ambos métodos logran el mismo arreglo ordenado final, pero reportan cantidades diferentes en sus contadores de operaciones.
*/

import java.util.Arrays;

/**
 * Compara Bubble Sort y Selection Sort contando comparaciones e intercambios.
 */
public class ComparacionOrdenamientos {

    /**
     * Agrupa el arreglo ordenado y las operaciones contadas por un algoritmo.
     */
    public static class ResultadoOrdenamiento {
        private final int[] arregloOrdenado;
        private final int comparaciones;
        private final int intercambios;

        public ResultadoOrdenamiento(int[] arregloOrdenado, int comparaciones, int intercambios) {
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
     * Ordena el arreglo recibido en forma ascendente mediante Bubble Sort.
     *
     * @param array copia del arreglo que se ordenará
     * @return resultado con el arreglo ordenado, comparaciones e intercambios
     */
    public static ResultadoOrdenamiento bubbleSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int pasada = 0; pasada < array.length - 1; pasada++) {
            // El mayor de la zona activa queda al final; por eso se reduce el límite.
            for (int indice = 0; indice < array.length - 1 - pasada; indice++) {
                comparaciones++;
                // Cuenta una comparación entre elementos adyacentes.
                if (array[indice] > array[indice + 1]) {
                    // Intercambia el par cuando está en orden incorrecto.
                    int temporal = array[indice];
                    array[indice] = array[indice + 1];
                    array[indice + 1] = temporal;
                    intercambios++;
                }
            }
        }

        return new ResultadoOrdenamiento(array, comparaciones, intercambios);
    }

    /**
     * Ordena el arreglo recibido en forma ascendente mediante Selection Sort.
     *
     * @param array copia del arreglo que se ordenará
     * @return resultado con el arreglo ordenado, comparaciones e intercambios
     */
    public static ResultadoOrdenamiento selectionSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int indiceActual = 0; indiceActual < array.length - 1; indiceActual++) {
            int indiceMenor = indiceActual;

            for (int candidato = indiceActual + 1; candidato < array.length; candidato++) {
                comparaciones++;
                // Compara cada candidato con el menor encontrado hasta el momento.
                if (array[candidato] < array[indiceMenor]) {
                    indiceMenor = candidato;
                }
            }

            if (indiceMenor != indiceActual) {
                // Coloca el menor al inicio de la zona no ordenada con un intercambio real.
                int temporal = array[indiceActual];
                array[indiceActual] = array[indiceMenor];
                array[indiceMenor] = temporal;
                intercambios++;
            }
        }

        return new ResultadoOrdenamiento(array, comparaciones, intercambios);
    }

    private static void imprimirResultado(String nombre, ResultadoOrdenamiento resultado) {
        System.out.println(nombre + ": " + Arrays.toString(resultado.getArregloOrdenado()));
        System.out.println("Comparaciones: " + resultado.getComparaciones());
        System.out.println("Intercambios: " + resultado.getIntercambios());
    }

    /**
     * Ejecuta ambos algoritmos sobre copias independientes del mismo arreglo.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] arregloOriginal = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Arreglo original: " + Arrays.toString(arregloOriginal));

        ResultadoOrdenamiento resultadoBurbuja = bubbleSort(arregloOriginal.clone());
        ResultadoOrdenamiento resultadoSeleccion = selectionSort(arregloOriginal.clone());

        imprimirResultado("Bubble Sort", resultadoBurbuja);
        System.out.println();
        imprimirResultado("Selection Sort", resultadoSeleccion);
    }
}