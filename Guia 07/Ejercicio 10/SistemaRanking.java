/*
Quiero implementar en Java un programa que reciba un arreglo de objetos Jugador (con nombre y puntaje) y los ordene para formar un ranking de mayor a menor puntaje.
Para resolver este problema, elijo utilizar el algoritmo MergeSort. La justificación de esta elección es que, al ordenar objetos complejos (como un ranking de jugadores), MergeSort garantiza un rendimiento sumamente rápido, estable y consistente (O(n log n)). Esto es ideal para un sistema de ranking, ya que si la cantidad de jugadores crece drásticamente, el sistema no se volverá lento como ocurriría con algoritmos simples (Burbuja, Inserción) o en el peor caso de Quicksort.
El programa recibe un arreglo con los siguientes jugadores: Jugador("Ana", 1200), Jugador("Pedro", 900), Jugador("Lucia", 1500).
El programa debe mostrar por pantalla el ranking final ordenado desde el jugador con más puntos hasta el de menos puntos.
El código debe incluir comentarios que expliquen la creación de la clase Jugador, cómo se accede al puntaje para compararlo y cómo se invirtió la lógica para ordenar de forma descendente.
Para verificar que el resultado sea correcto, la consola debe imprimir en orden: Lucia (1500), Ana (1200) y Pedro (900).
*/

import java.util.Arrays;

/**
 * Ordena jugadores por puntaje descendente mediante MergeSort.
 */
public class SistemaRanking {

    /**
     * Representa un jugador con nombre y puntaje inmutables.
     */
    public static class Jugador {
        private final String nombre;
        private final int puntaje;

        public Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntaje() {
            return puntaje;
        }

        @Override
        public String toString() {
            return nombre + " (" + puntaje + ")";
        }
    }

    /**
     * Ordena el arreglo de jugadores de mayor a menor puntaje.
     *
     * @param jugadores arreglo que se ordenará
     */
    public static void ordenarRanking(Jugador[] jugadores) {
        Jugador[] auxiliar = jugadores.clone();
        mergeSort(jugadores, auxiliar, 0, jugadores.length - 1);
    }

    private static void mergeSort(Jugador[] jugadores, Jugador[] auxiliar, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int medio = inicio + (fin - inicio) / 2;
        mergeSort(jugadores, auxiliar, inicio, medio);
        mergeSort(jugadores, auxiliar, medio + 1, fin);
        fusionar(jugadores, auxiliar, inicio, medio, fin);
    }

    private static void fusionar(
            Jugador[] jugadores,
            Jugador[] auxiliar,
            int inicio,
            int medio,
            int fin) {
        int indiceIzquierdo = inicio;
        int indiceDerecho = medio + 1;
        int indiceAuxiliar = inicio;

        while (indiceIzquierdo <= medio && indiceDerecho <= fin) {
            // Se accede al puntaje mediante el getter. >= invierte el orden ascendente:
            // se coloca primero el puntaje mayor y, en empate, se conserva el de la izquierda.
            if (jugadores[indiceIzquierdo].getPuntaje()
                    >= jugadores[indiceDerecho].getPuntaje()) {
                auxiliar[indiceAuxiliar++] = jugadores[indiceIzquierdo++];
            } else {
                auxiliar[indiceAuxiliar++] = jugadores[indiceDerecho++];
            }
        }

        while (indiceIzquierdo <= medio) {
            auxiliar[indiceAuxiliar++] = jugadores[indiceIzquierdo++];
        }

        while (indiceDerecho <= fin) {
            auxiliar[indiceAuxiliar++] = jugadores[indiceDerecho++];
        }

        System.arraycopy(auxiliar, inicio, jugadores, inicio, fin - inicio + 1);
    }

    /**
     * Crea los jugadores de ejemplo, ordena el arreglo y muestra el ranking.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Jugador[] jugadores = {
            new Jugador("Ana", 1200),
            new Jugador("Pedro", 900),
            new Jugador("Lucia", 1500)
        };

        ordenarRanking(jugadores);
        Arrays.stream(jugadores).forEach(System.out::println);
    }
}