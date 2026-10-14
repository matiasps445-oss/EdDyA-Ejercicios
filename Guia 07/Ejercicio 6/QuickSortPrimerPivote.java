/*
Quiero implementar en Java un programa que ordene un arreglo utilizando el algoritmo Quicksort, eligiendo siempre el primer elemento de la partición como pivote.
El razonamiento de este algoritmo se basa profundamente en la estrategia de 'divide y vencerás' y en la recursividad. Se elige un pivote (en este caso, el primer elemento) y se recorre el resto del arreglo para dividirlo en dos subarreglos: uno con los elementos menores al pivote y otro con los elementos mayores. Luego, la función se llama a sí misma recursivamente para ordenar cada subarreglo de forma independiente.
El programa recibe un arreglo de números enteros.
El programa debe mostrar por pantalla en cada paso recursivo: cuál fue el pivote elegido, cómo quedó conformado el subarreglo izquierdo (menores), cómo quedó conformado el subarreglo derecho (mayores) y, al finalizar toda la ejecución, el arreglo final ordenado.
El código debe incluir comentarios que expliquen la división del arreglo y las llamadas recursivas.
Para verificar que el resultado sea correcto, se debe comprobar en consola que el arreglo final quede ordenado y que los subarreglos impresos correspondan lógicamente al pivote de ese momento.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ordena un arreglo mediante Quicksort usando el primer elemento como pivote.
 */
public class QuickSortPrimerPivote {

    /**
     * Ordena una copia lógica del arreglo y devuelve el resultado ordenado.
     *
     * @param numeros arreglo de enteros que se ordenará
     * @return nuevo arreglo ordenado de menor a mayor
     */
    public static int[] ordenarQuickSort(int[] numeros) {
        return quickSort(numeros);
    }

    private static int[] quickSort(int[] numeros) {
        // Caso base: los arreglos vacíos y unitarios ya están ordenados.
        if (numeros.length <= 1) {
            return numeros;
        }

        // El pivote siempre es el primer elemento de la partición actual.
        int pivote = numeros[0];
        List<Integer> izquierda = new ArrayList<>();
        List<Integer> derecha = new ArrayList<>();

        // Divide el resto de la partición: menores a la izquierda; iguales o mayores a la derecha.
        for (int indice = 1; indice < numeros.length; indice++) {
            if (numeros[indice] < pivote) {
                izquierda.add(numeros[indice]);
            } else {
                derecha.add(numeros[indice]);
            }
        }

        // Muestra la partición antes de ordenar recursivamente cada lado.
        System.out.println("Pivote: " + pivote
                + " | Izquierda: " + izquierda
                + " | Derecha: " + derecha);

        // Ordena por separado las dos particiones que se formaron alrededor del pivote.
        int[] izquierdaOrdenada = quickSort(izquierda.stream().mapToInt(Integer::intValue).toArray());
        int[] derechaOrdenada = quickSort(derecha.stream().mapToInt(Integer::intValue).toArray());

        // Combina ambas particiones ya ordenadas con el pivote entre ellas.
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
     * Ejecuta Quicksort con el arreglo de demostración del plan.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {5, 3, 8, 4, 2, 7, 1, 10};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        int[] arregloOrdenado = ordenarQuickSort(numeros);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arregloOrdenado));
    }
}