/*
Quiero implementar en Java un programa que compare el rendimiento de tres algoritmos de ordenamiento simples: Bubble Sort, Selection Sort e Insertion Sort.
El razonamiento de este problema es aplicar las tres estrategias sobre copias exactas del mismo arreglo inicial para poder evaluar objetivamente su eficiencia. Sabemos que Bubble Sort compara e intercambia vecinos constantemente, Selection Sort busca el menor y realiza un solo intercambio por pasada, e Insertion Sort toma un elemento y desplaza los mayores para insertarlo en su lugar.
El programa recibe un arreglo de números enteros desordenados.
El programa debe procesar el arreglo con los tres métodos y mostrar por pantalla para cada algoritmo: el arreglo final ordenado, la cantidad total de comparaciones y la cantidad total de intercambios o desplazamientos realizados.
El código debe incluir comentarios que identifiquen dónde se inicializan las copias del arreglo y dónde se incrementan los contadores en cada método.
Para verificar que el resultado sea correcto, se debe observar en la consola que los tres algoritmos devuelvan exactamente el mismo arreglo ordenado, pero reporten distintas cantidades de operaciones, permitiendo al alumno comparar las estrategias y no solo el resultado final.
*/

import java.util.Arrays;

/**
 * Compara Bubble Sort, Selection Sort e Insertion Sort con contadores de operaciones.
 */
public class ComparacionAlgoritmosOrdenamiento {

    /**
     * Resultado común de un ordenamiento y sus contadores.
     */
    public static class ResultadoOrdenamiento {
        private final int[] arregloOrdenado;
        private final int comparaciones;
        private final int movimientos;

        public ResultadoOrdenamiento(int[] arregloOrdenado, int comparaciones, int movimientos) {
            this.arregloOrdenado = arregloOrdenado;
            this.comparaciones = comparaciones;
            this.movimientos = movimientos;
        }

        public int[] getArregloOrdenado() {
            return arregloOrdenado;
        }

        public int getComparaciones() {
            return comparaciones;
        }

        public int getMovimientos() {
            return movimientos;
        }
    }

    /**
     * Ordena el arreglo recibido con Bubble Sort.
     *
     * @param array copia independiente que se ordenará
     * @return arreglo ordenado, comparaciones e intercambios
     */
    public static ResultadoOrdenamiento bubbleSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int pasada = 0; pasada < array.length - 1; pasada++) {
            for (int indice = 0; indice < array.length - 1 - pasada; indice++) {
                // Cuenta la comparación entre este par de vecinos.
                comparaciones++;
                if (array[indice] > array[indice + 1]) {
                    int temporal = array[indice];
                    array[indice] = array[indice + 1];
                    array[indice + 1] = temporal;
                    // Cuenta solo el intercambio que realmente se realizó.
                    intercambios++;
                }
            }
        }

        return new ResultadoOrdenamiento(array, comparaciones, intercambios);
    }

    /**
     * Ordena el arreglo recibido con Selection Sort.
     *
     * @param array copia independiente que se ordenará
     * @return arreglo ordenado, comparaciones e intercambios
     */
    public static ResultadoOrdenamiento selectionSort(int[] array) {
        int comparaciones = 0;
        int intercambios = 0;

        for (int indiceActual = 0; indiceActual < array.length - 1; indiceActual++) {
            int indiceMenor = indiceActual;

            for (int candidato = indiceActual + 1; candidato < array.length; candidato++) {
                // Cuenta la comparación del candidato con el menor actual.
                comparaciones++;
                if (array[candidato] < array[indiceMenor]) {
                    indiceMenor = candidato;
                }
            }

            if (indiceMenor != indiceActual) {
                int temporal = array[indiceActual];
                array[indiceActual] = array[indiceMenor];
                array[indiceMenor] = temporal;
                // Cuenta solo el intercambio real al final de esta pasada.
                intercambios++;
            }
        }

        return new ResultadoOrdenamiento(array, comparaciones, intercambios);
    }

    /**
     * Ordena el arreglo recibido con Insertion Sort.
     *
     * @param array copia independiente que se ordenará
     * @return arreglo ordenado, comparaciones de valores y desplazamientos
     */
    public static ResultadoOrdenamiento insertionSort(int[] array) {
        int comparaciones = 0;
        int desplazamientos = 0;

        for (int indice = 1; indice < array.length; indice++) {
            int clave = array[indice];
            int posicion = indice - 1;

            while (posicion >= 0) {
                // Cuenta solo la comparación entre valores; no cuenta posicion >= 0.
                comparaciones++;
                if (array[posicion] > clave) {
                    array[posicion + 1] = array[posicion];
                    // Cuenta el movimiento de un elemento una posición a la derecha.
                    desplazamientos++;
                    posicion--;
                } else {
                    break;
                }
            }

            // Colocar la clave en el hueco no se incluye en el contador de desplazamientos.
            array[posicion + 1] = clave;
        }

        return new ResultadoOrdenamiento(array, comparaciones, desplazamientos);
    }

    private static void imprimirResultado(
            String nombre,
            ResultadoOrdenamiento resultado,
            String etiquetaMovimientos) {
        System.out.println(nombre + ": " + Arrays.toString(resultado.getArregloOrdenado()));
        System.out.println("Comparaciones: " + resultado.getComparaciones());
        System.out.println(etiquetaMovimientos + ": " + resultado.getMovimientos());
    }

    /**
     * Ejecuta los tres algoritmos sobre copias exactas del arreglo original.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] arregloOriginal = {64, 34, 25, 12, 22, 11, 90};

        // Cada algoritmo recibe su propia copia para partir exactamente de los mismos datos.
        int[] copiaBubble = arregloOriginal.clone();
        int[] copiaSelection = arregloOriginal.clone();
        int[] copiaInsertion = arregloOriginal.clone();

        System.out.println("Arreglo original: " + Arrays.toString(arregloOriginal));
        imprimirResultado("Bubble Sort", bubbleSort(copiaBubble), "Intercambios");
        System.out.println();
        imprimirResultado("Selection Sort", selectionSort(copiaSelection), "Intercambios");
        System.out.println();
        imprimirResultado("Insertion Sort", insertionSort(copiaInsertion), "Desplazamientos");
    }
}