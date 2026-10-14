/*
Quiero implementar en Java un programa que ordene un arreglo casi ordenado utilizando el algoritmo Insertion Sort (Ordenamiento por Inserción).
El razonamiento de este algoritmo se basa en construir una porción ordenada del arreglo paso a paso, tomando el siguiente elemento desordenado e insertándolo en la posición correcta dentro de la porción ya ordenada mediante desplazamientos. Insertion Sort funciona excepcionalmente bien cuando los datos están casi ordenados porque la condición de búsqueda se detiene rápidamente, lo que significa que requiere muy pocos desplazamientos y comparaciones en comparación con otros algoritmos.
El programa recibe un arreglo casi ordenado, específicamente: {1, 2, 3, 5, 4, 6, 7}.
El programa debe mostrar por pantalla el arreglo ordenado resultante y la cantidad exacta de desplazamientos realizados.
El código debe incluir comentarios que expliquen la variable que cuenta los desplazamientos y la condición del bucle while que permite que se detenga anticipadamente.
Para verificar que el resultado sea correcto, se debe comprobar que el arreglo quede completamente ordenado y que el número de desplazamientos sea mínimo (solo los necesarios para acomodar el 5 y el 4).
*/

import java.util.Arrays;

/**
 * Ordena un arreglo casi ordenado mediante Insertion Sort y cuenta desplazamientos.
 */
public class OrdenamientoInsercion {

    /**
     * Ordena el arreglo recibido en forma ascendente.
     *
     * @param numeros arreglo de enteros que se ordenará
     * @return cantidad de elementos desplazados una posición a la derecha
     */
    public static int ordenarPorInsercion(int[] numeros) {
        int desplazamientos = 0;

        for (int indice = 1; indice < numeros.length; indice++) {
            int clave = numeros[indice];
            int posicion = indice - 1;

            // El while para si la posición sale del arreglo o encuentra un valor <= clave.
            // El orden de la condición evita acceder al arreglo cuando posicion es negativo.
            while (posicion >= 0 && numeros[posicion] > clave) {
                numeros[posicion + 1] = numeros[posicion];

                // Cada incremento corresponde a mover un elemento existente una posición a la derecha.
                desplazamientos++;
                posicion--;
            }

            // Inserta la clave en el hueco encontrado; esta escritura no es un desplazamiento contado.
            numeros[posicion + 1] = clave;
        }

        return desplazamientos;
    }

    /**
     * Ejecuta el ejemplo casi ordenado indicado en el ejercicio.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 5, 4, 6, 7};
        int desplazamientos = ordenarPorInsercion(numeros);

        System.out.println("Arreglo ordenado: " + Arrays.toString(numeros));
        System.out.println("Desplazamientos: " + desplazamientos);
    }
}