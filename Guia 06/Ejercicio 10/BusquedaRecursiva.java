/*
Quiero implementar en Java una función recursiva que busque un número dentro de un arreglo, sin usar ciclos.
Este problema puede resolverse recursivamente porque buscar en todo el arreglo equivale a revisar la posición actual y, si no es el número buscado, delegar la búsqueda al resto del arreglo.
La búsqueda empieza desde la posición inicial (índice 0), pasándola como parámetro a la función.
Para avanzar en el arreglo sin usar for ni while, se hace una llamada recursiva incrementando el índice actual en 1 (indice + 1) en cada iteración.
Hay dos casos base:
1. Cuando encuentra el valor buscado en la posición actual, devuelve true (o la posición), deteniendo la recursión porque ya cumplió su objetivo.
2. Cuando el índice llega al final del arreglo (indice == arreglo.length), devuelve false (o -1), deteniendo la recursión porque ya no hay más elementos que revisar.
La función debe devolver un valor que indique si el elemento fue encontrado.
Quiero que el código tenga comentarios explicando el caso base, el caso recursivo y la pila de llamadas.
También quiero probarlo con estos casos:
- caso 1: arreglo = [1, 2, 3, 4, 5], buscar = 3 (debería encontrarlo)
- caso 2: arreglo = [1, 2, 3, 4, 5], buscar = 6 (no debería encontrarlo)
- caso 3: arreglo = [], buscar = 1 (arreglo vacío)
*/

/**
 * Ejercicio 10: búsqueda de un elemento en un arreglo mediante recursividad.
 */
public class BusquedaRecursiva {

    /**
     * Busca un valor a partir del índice indicado.
     *
     * En cada llamada, la pila conserva pendiente el resultado de buscar desde
     * el índice siguiente. Cuando se alcanza un caso base, el valor devuelto
     * se propaga por las llamadas pendientes hasta llegar a la llamada inicial.
     *
     * @param arreglo arreglo de enteros donde se realizará la búsqueda
     * @param buscado valor que se desea encontrar
     * @param indice posición actual que se está revisando
     * @return true si el valor aparece en el arreglo; de lo contrario, false
     */
    public static boolean buscar(int[] arreglo, int buscado, int indice) {
        // Caso base 1: el índice llegó al final; no quedan elementos por revisar.
        if (indice == arreglo.length) {
            return false;
        }

        // Caso base 2: el valor actual coincide con el buscado; termina con éxito.
        if (arreglo[indice] == buscado) {
            return true;
        }

        // Caso recursivo: delega la búsqueda al resto del arreglo, avanzando un índice.
        return buscar(arreglo, buscado, indice + 1);
    }

    /**
     * Ejecuta los casos de prueba solicitados.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        int[] primerArreglo = {1, 2, 3, 4, 5};
        int[] segundoArreglo = {1, 2, 3, 4, 5};
        int[] tercerArreglo = {};

        System.out.println("Caso 1: " + buscar(primerArreglo, 3, 0));
        System.out.println("Caso 2: " + buscar(segundoArreglo, 6, 0));
        System.out.println("Caso 3: " + buscar(tercerArreglo, 1, 0));
    }
}