/*
Quiero implementar en Java un programa que ordene un arreglo de nombres alfabéticamente utilizando el algoritmo Selection Sort.
El razonamiento del algoritmo es el mismo que con números: buscar el menor elemento en la parte no ordenada y colocarlo en su posición final. La gran diferencia es que, al ordenar textos (Strings), no podemos usar los operadores relacionales tradicionales como '<' o '>'. En su lugar, el razonamiento cambia y debemos utilizar el método compareTo() de la clase String, el cual compara los caracteres según su valor lexicográfico en la tabla ASCII.
El programa recibe el siguiente arreglo de nombres: {"Lucia", "Ana", "Pedro", "Juan"}.
El programa debe mostrar por pantalla el arreglo original y luego el arreglo final ordenado alfabéticamente.
El código debe incluir comentarios que expliquen específicamente cómo funciona el método compareTo() en la condición de búsqueda del menor elemento.
Para verificar que el resultado sea correcto, se debe comprobar que la salida final sea Ana, Juan, Lucia y Pedro.
*/

import java.util.Arrays;

/**
 * Ordena nombres alfabéticamente mediante Selection Sort.
 */
public class OrdenamientoNombres {

    /**
     * Ordena el arreglo de nombres recibido en orden lexicográfico ascendente.
     *
     * @param nombres arreglo de nombres no nulos que se ordenará
     */
    public static void ordenarSeleccion(String[] nombres) {
        for (int indiceActual = 0; indiceActual < nombres.length - 1; indiceActual++) {
            int indiceMenor = indiceActual;

            // Busca el menor nombre en la zona que todavía no está ordenada.
            for (int candidato = indiceActual + 1; candidato < nombres.length; candidato++) {
                // compareTo devuelve un valor negativo si el candidato precede lexicográficamente
                // al nombre menor actual, cero si son iguales y positivo si aparece después.
                if (nombres[candidato].compareTo(nombres[indiceMenor]) < 0) {
                    indiceMenor = candidato;
                }
            }

            // Intercambia una sola vez al final de la búsqueda, si encontró otro menor.
            if (indiceMenor != indiceActual) {
                String temporal = nombres[indiceActual];
                nombres[indiceActual] = nombres[indiceMenor];
                nombres[indiceMenor] = temporal;
            }
        }
    }

    /**
     * Muestra el arreglo original y su versión ordenada.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};

        System.out.println("Arreglo original: " + Arrays.toString(nombres));
        ordenarSeleccion(nombres);
        System.out.println("Arreglo ordenado: " + Arrays.toString(nombres));
    }
}