/*
Quiero implementar en Java un programa que ejecute Quicksort sobre un arreglo que ya está ordenado[cite: 15].
El razonamiento principal aquí es analizar el rendimiento del algoritmo. Si elegimos siempre el primer elemento como pivote en un arreglo que ya está ordenado, el algoritmo no logra dividir el problema en dos mitades equilibradas[cite: 15]. En su lugar, deja un subarreglo vacío y otro con todos los demás elementos, lo que genera un mal rendimiento (comportamiento de O(n^2)) y un exceso de llamadas recursivas[cite: 15].
El programa recibe un arreglo previamente ordenado, específicamente: {1, 2, 3, 4, 5, 6, 7}[cite: 15].
El programa debe mostrar por pantalla el arreglo final y, fundamentalmente, debe contar la cantidad de llamadas recursivas realizadas[cite: 15].
El código debe incluir comentarios que expliquen la variable contadora y por qué elegir el primer elemento como pivote en este escenario degrada el rendimiento.
Para verificar que el resultado sea correcto, debemos notar una cantidad de llamadas recursivas significativamente alta para un arreglo tan pequeño.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demuestra el peor caso de Quicksort al usar el primer elemento como pivote.
 */
public class PeorCasoQuickSort {

    /**
     * Resultado del ordenamiento y cantidad de llamadas recursivas a particiones hijas.
     */
    public static class ResultadoOrdenamiento {
        private final int[] arregloOrdenado;
        private final int llamadasRecursivas;

        public ResultadoOrdenamiento(int[] arregloOrdenado, int llamadasRecursivas) {
            this.arregloOrdenado = arregloOrdenado;
            this.llamadasRecursivas = llamadasRecursivas;
        }

        public int[] getArregloOrdenado() {
            return arregloOrdenado;
        }

        public int getLlamadasRecursivas() {
            return llamadasRecursivas;
        }
    }

    /** Contador mutable que se comparte con todas las llamadas de una ejecución. */
    private static class ContadorLlamadas {
        private int cantidad;
    }

    /**
     * Ordena una copia lógica del arreglo y cuenta las llamadas recursivas hijas.
     * La invocación inicial a este método auxiliar no se incluye en el contador.
     *
     * @param numeros arreglo de enteros que se ordenará
     * @return arreglo ordenado y cantidad de llamadas recursivas, excluida la inicial
     */
    public static ResultadoOrdenamiento ordenarQuickSort(int[] numeros) {
        ContadorLlamadas contador = new ContadorLlamadas();
        int[] arregloOrdenado = quickSort(numeros, contador);
        return new ResultadoOrdenamiento(arregloOrdenado, contador.cantidad);
    }

    private static int[] quickSort(int[] numeros, ContadorLlamadas contador) {
        // Caso base: una partición vacía o unitaria ya está ordenada.
        if (numeros.length <= 1) {
            return numeros;
        }

        // Siempre se elige el primer elemento de la partición actual.
        int pivote = numeros[0];
        List<Integer> izquierda = new ArrayList<>();
        List<Integer> derecha = new ArrayList<>();

        for (int indice = 1; indice < numeros.length; indice++) {
            if (numeros[indice] < pivote) {
                izquierda.add(numeros[indice]);
            } else {
                derecha.add(numeros[indice]);
            }
        }

        // En un arreglo ascendente, cada pivote es el menor y deja vacía la izquierda,
        // produciendo una partición derecha de tamaño n - 1 y una cadena profunda de llamadas.
        int[] izquierdaSinOrdenar = izquierda.stream().mapToInt(Integer::intValue).toArray();
        int[] derechaSinOrdenar = derecha.stream().mapToInt(Integer::intValue).toArray();

        // Se cuentan las llamadas a ambas particiones hijas, incluso si son casos base.
        // La primera entrada a quickSort desde ordenarQuickSort no se cuenta.
        contador.cantidad++;
        int[] izquierdaOrdenada = quickSort(izquierdaSinOrdenar, contador);

        contador.cantidad++;
        int[] derechaOrdenada = quickSort(derechaSinOrdenar, contador);

        int[] resultado = new int[izquierdaOrdenada.length + 1 + derechaOrdenada.length];
        System.arraycopy(izquierdaOrdenada, 0, resultado, 0, izquierdaOrdenada.length);
        resultado[izquierdaOrdenada.length] = pivote;
        System.arraycopy(
                derechaOrdenada,
                0,
                resultado,
                izquierdaOrdenada.length + 1,
                derechaOrdenada.length);

        return resultado;
    }

    /**
     * Ejecuta Quicksort con el arreglo ordenado del ejercicio.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7};
        ResultadoOrdenamiento resultado = ordenarQuickSort(numeros);

        System.out.println("Arreglo final: " + Arrays.toString(resultado.getArregloOrdenado()));
        System.out.println("Llamadas recursivas (sin la inicial): "
                + resultado.getLlamadasRecursivas());
    }
}