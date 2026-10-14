/*
Quiero implementar en Java un programa que ordene un arreglo utilizando el algoritmo ShellSort.
El razonamiento de este algoritmo es una mejora directa sobre Insertion Sort. En lugar de comparar y desplazar solo elementos vecinos (lo cual es lento si un número pequeño está al final del arreglo), ShellSort utiliza 'saltos' o 'gaps' para comparar elementos que están separados por una cierta distancia. Esto permite que los elementos se muevan grandes distancias rápidamente hacia su posición correcta. A medida que avanza el algoritmo, el gap se va reduciendo hasta llegar a 1, donde realiza un último Insertion Sort, pero sobre un arreglo que ya está casi ordenado (haciéndolo ultra eficiente).
El programa recibe un arreglo de números enteros desordenados.
El programa debe mostrar por pantalla el valor del gap en cada etapa y el estado del arreglo luego de cada pasada correspondiente a ese gap.
El código debe incluir comentarios que expliquen cómo se calcula y se reduce el gap.
Para verificar que el resultado sea correcto, se debe comprobar que los gaps disminuyan progresivamente y el arreglo quede ordenado de menor a mayor.
*/

import java.util.Arrays;

/**
 * Ordena números enteros mediante ShellSort e imprime cada etapa de gap.
 */
public class OrdenamientoShell {

    /**
     * Ordena el arreglo recibido en orden ascendente usando ShellSort.
     *
     * @param numeros arreglo de enteros que se ordenará
     */
    public static void ordenarShell(int[] numeros) {
        // Se empieza con la mitad de la longitud y el gap se reduce a la mitad
        // después de cada pasada hasta completar la etapa final con gap igual a 1.
        for (int gap = numeros.length / 2; gap > 0; gap /= 2) {
            for (int indice = gap; indice < numeros.length; indice++) {
                int clave = numeros[indice];
                int posicion = indice;

                // El límite se comprueba antes de leer posicion - gap; si el valor anterior
                // separado por el gap es mayor que la clave, se desplaza hacia la derecha.
                while (posicion >= gap && numeros[posicion - gap] > clave) {
                    numeros[posicion] = numeros[posicion - gap];
                    posicion -= gap;
                }

                numeros[posicion] = clave;
            }

            // Se imprime una vez al terminar de procesar todos los elementos para este gap.
            System.out.println("Gap " + gap + ": " + Arrays.toString(numeros));
        }
    }

    /**
     * Demuestra ShellSort con un arreglo de ocho elementos.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {23, 12, 1, 8, 34, 54, 2, 3};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        ordenarShell(numeros);
    }
}