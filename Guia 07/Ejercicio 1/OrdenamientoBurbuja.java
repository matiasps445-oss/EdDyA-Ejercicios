/*
Quiero implementar en Java un programa que ordene un arreglo de números enteros utilizando el algoritmo Bubble Sort (Ordenamiento Burbuja).
El razonamiento de este algoritmo se basa en comparar elementos adyacentes y cambiarlos de posición si están en el orden equivocado. Esto genera que, en cada iteración completa, el elemento de mayor valor se vaya desplazando hacia el final del arreglo, ocupando su posición definitiva.
El programa recibirá un arreglo de números enteros desordenados, por ejemplo: {64, 34, 25, 12, 22, 11, 90}.
El programa debe mostrar por pantalla el arreglo original y, como requisito fundamental, el estado del arreglo después de cada pasada (iteración externa).
El código debe incluir comentarios que indiquen dónde se realiza la comparación, dónde ocurre el intercambio y por qué el límite del bucle interno se va reduciendo.
Para verificar que el resultado sea correcto, se debe comprobar visualmente en la consola que, iteración tras iteración, los números más grandes quedan acomodados al final hasta que todo el arreglo esté ordenado de menor a mayor.
*/

import java.util.Arrays;

/**
 * Ordena un arreglo de enteros con Bubble Sort y muestra cada pasada.
 */
public class OrdenamientoBurbuja {

    /**
     * Ordena el arreglo recibido en orden ascendente e imprime cada pasada.
     *
     * @param numeros arreglo de enteros que se ordenará
     */
    public static void ordenarBurbuja(int[] numeros) {
        for (int pasada = 0; pasada < numeros.length - 1; pasada++) {
            // El mayor valor del tramo revisado queda al final tras cada pasada,
            // por eso se reduce en uno la zona que debe recorrer el ciclo interno.
            for (int indice = 0; indice < numeros.length - 1 - pasada; indice++) {
                // Se comparan elementos adyacentes para detectar si están invertidos.
                if (numeros[indice] > numeros[indice + 1]) {
                    // Intercambia el par cuando el izquierdo debe quedar después del derecho.
                    int temporal = numeros[indice];
                    numeros[indice] = numeros[indice + 1];
                    numeros[indice + 1] = temporal;
                }
            }

            // Se muestra el arreglo completo al terminar esta pasada externa.
            System.out.println("Pasada " + (pasada + 1) + ": " + Arrays.toString(numeros));
        }
    }

    /**
     * Demuestra Bubble Sort con el arreglo indicado en el ejercicio.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Arreglo original: " + Arrays.toString(numeros));
        ordenarBurbuja(numeros);
    }
}