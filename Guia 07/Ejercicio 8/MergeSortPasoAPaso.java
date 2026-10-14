/*
Quiero implementar en Java un programa que ordene un arreglo utilizando el algoritmo MergeSort.
El razonamiento de este algoritmo se basa en la estrategia 'divide y vencerás', separando claramente dos etapas: 'dividir' y 'fusionar'. En la etapa de división, el arreglo se parte a la mitad recursivamente hasta llegar a la condición de corte (subarreglos de 1 o 0 elementos). En la etapa de fusión, estos pequeños subarreglos ordenados se combinan (merge) para formar arreglos más grandes y ordenados, hasta reconstruir el arreglo original completo.
El programa recibe un arreglo de números enteros desordenados.
El programa debe mostrar por pantalla paso a paso: cómo se divide el arreglo en cada llamada, cuándo se alcanza la condición de corte, y cómo se van fusionando los subarreglos en cada etapa.
El código debe incluir comentarios que expliquen la diferencia entre el método que divide recursivamente y el método que fusiona.
Para verificar que el resultado sea correcto, se debe visualizar en consola todo el árbol de división y el proceso inverso de reconstrucción ordenada.
*/

import java.util.Arrays;

/**
 * Ordena un arreglo mediante MergeSort e imprime cada división y fusión.
 */
public class MergeSortPasoAPaso {

    /**
     * Divide recursivamente el arreglo y delega la combinación a {@link #fusionar(int[], int[])}.
     *
     * @param numeros arreglo que se desea ordenar
     * @return nuevo arreglo ordenado de menor a mayor
     */
    public static int[] mergeSort(int[] numeros) {
        // Caso base: los arreglos vacíos y unitarios ya están ordenados.
        if (numeros.length <= 1) {
            System.out.println("Caso base alcanzado: " + Arrays.toString(numeros));
            return numeros;
        }

        int puntoMedio = numeros.length / 2;
        int[] izquierda = Arrays.copyOfRange(numeros, 0, puntoMedio);
        int[] derecha = Arrays.copyOfRange(numeros, puntoMedio, numeros.length);

        // Este método divide y coordina la recursión; la fusión ocurre en fusionar().
        System.out.println("Dividiendo " + Arrays.toString(numeros)
                + " -> izquierda " + Arrays.toString(izquierda)
                + " | derecha " + Arrays.toString(derecha));

        int[] izquierdaOrdenada = mergeSort(izquierda);
        int[] derechaOrdenada = mergeSort(derecha);

        return fusionar(izquierdaOrdenada, derechaOrdenada);
    }

    /**
     * Combina dos arreglos ordenados en uno nuevo también ordenado.
     * Este método no divide ni hace llamadas recursivas.
     *
     * @param izquierda primera mitad ordenada
     * @param derecha segunda mitad ordenada
     * @return arreglo que contiene ambas mitades en orden ascendente
     */
    private static int[] fusionar(int[] izquierda, int[] derecha) {
        int[] resultado = new int[izquierda.length + derecha.length];
        int indiceIzquierda = 0;
        int indiceDerecha = 0;
        int indiceResultado = 0;

        // Copia en cada paso el menor de los elementos disponibles de ambas mitades.
        while (indiceIzquierda < izquierda.length && indiceDerecha < derecha.length) {
            if (izquierda[indiceIzquierda] <= derecha[indiceDerecha]) {
                resultado[indiceResultado++] = izquierda[indiceIzquierda++];
            } else {
                resultado[indiceResultado++] = derecha[indiceDerecha++];
            }
        }

        // Si una mitad se agotó, copia al resultado todo lo que queda de la otra.
        while (indiceIzquierda < izquierda.length) {
            resultado[indiceResultado++] = izquierda[indiceIzquierda++];
        }
        while (indiceDerecha < derecha.length) {
            resultado[indiceResultado++] = derecha[indiceDerecha++];
        }

        System.out.println("Fusionando " + Arrays.toString(izquierda)
                + " + " + Arrays.toString(derecha)
                + " -> " + Arrays.toString(resultado));
        return resultado;
    }

    /**
     * Ejecuta MergeSort con el arreglo de demostración del plan.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {38, 27, 43, 3, 9, 82, 10, 5};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        int[] arregloOrdenado = mergeSort(numeros);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arregloOrdenado));
    }
}