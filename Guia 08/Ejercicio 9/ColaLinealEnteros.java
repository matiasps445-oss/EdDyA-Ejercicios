/*
Quiero implementar en Java una cola simple con arreglo y demostrar el problema del desperdicio de posiciones.
El programa debe mostrar el arreglo luego de varias operaciones enqueue y dequeue, para ver cómo quedan espacios libres al inicio que no pueden reutilizarse en una cola simple.
*/

import java.util.Arrays;

/**
 * Cola lineal de enteros que muestra el desperdicio de espacio al retirar elementos.
 */
public class ColaLinealEnteros {
    private final int[] elementos;
    // front avanza al retirar elementos; rear solo avanza al agregar elementos.
    private int front = 0;
    private int rear = -1;

    /**
     * Crea una cola lineal con la capacidad indicada.
     *
     * @param capacidad cantidad máxima de posiciones del arreglo
     * @throws IllegalArgumentException si la capacidad no es positiva
     */
    public ColaLinealEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        elementos = new int[capacidad];
    }

    /**
     * Agrega un valor en la siguiente posición disponible al final del arreglo.
     *
     * @param valor número que se agrega a la cola
     * @throws IllegalStateException si rear ya alcanzó el último índice
     */
    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede encolar: la cola está llena.");
        }

        rear++;
        elementos[rear] = valor;
    }

    /**
     * Retira y devuelve el primer valor de la cola sin desplazar los restantes.
     * La posición liberada se marca con cero para hacer visible el espacio vacío.
     *
     * @return valor retirado
     * @throws IllegalStateException si la cola está vacía
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
        }

        int valorRetirado = elementos[front];
        elementos[front] = 0;
        front++;
        return valorRetirado;
    }

    /**
     * Indica si la cola no contiene elementos activos.
     *
     * @return true si la cola está vacía
     */
    public boolean isEmpty() {
        return front > rear;
    }

    /**
     * Indica si rear llegó al último índice del arreglo. Los espacios anteriores a
     * front están vacíos físicamente, pero esta cola lineal no puede reutilizarlos.
     *
     * @return true si no quedan posiciones al final para encolar
     */
    public boolean isFull() {
        return rear == elementos.length - 1;
    }

    /**
     * Devuelve la cantidad lógica de elementos de la cola.
     *
     * @return cantidad de elementos activos
     */
    public int size() {
        return isEmpty() ? 0 : rear - front + 1;
    }

    /**
     * Imprime el arreglo físico completo, incluidos los espacios vacíos, y los índices.
     */
    public void mostrarArreglo() {
        System.out.println("Arreglo: " + Arrays.toString(elementos)
                + " | front: " + front
                + " | rear: " + rear
                + " | tamaño lógico: " + size());
    }

    /**
     * Ejecuta la secuencia que evidencia el desperdicio de espacio de una cola lineal.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        ColaLinealEnteros cola = new ColaLinealEnteros(5);

        System.out.println("Estado inicial:");
        cola.mostrarArreglo();

        System.out.println("\nEncolando 10:");
        cola.enqueue(10);
        cola.mostrarArreglo();

        System.out.println("\nEncolando 20:");
        cola.enqueue(20);
        cola.mostrarArreglo();

        System.out.println("\nEncolando 30:");
        cola.enqueue(30);
        cola.mostrarArreglo();

        System.out.println("\nEncolando 40:");
        cola.enqueue(40);
        cola.mostrarArreglo();

        System.out.println("\nEncolando 50:");
        cola.enqueue(50);
        cola.mostrarArreglo();

        System.out.println("\nDesencolando: " + cola.dequeue());
        cola.mostrarArreglo();

        System.out.println("\nDesencolando: " + cola.dequeue());
        cola.mostrarArreglo();

        System.out.println("\nIntentando encolar 60:");
        try {
            cola.enqueue(60);
        } catch (IllegalStateException excepcion) {
            System.out.println("Error: " + excepcion.getMessage());
            System.out.println("La cola lineal no puede reutilizar las posiciones vacías del inicio.");
        }
        cola.mostrarArreglo();
    }
}