/*
Quiero implementar en Java una clase de Cola Circular utilizando un arreglo de tamaño fijo. El objetivo principal de esta estructura es resolver el problema de desperdicio de espacio de la cola simple. Para reutilizar los espacios liberados al inicio del arreglo, los índices deben volver a la posición 0 una vez que alcanzan el límite final, simulando un anillo. El programa debe permitir las operaciones `enqueue`, `dequeue`, `front`, `isEmpty` e `isFull`. Para manejar el arreglo interno y lograr el comportamiento circular, es imperativo usar el operador módulo `%`. Cada vez que se incrementan los índices, la fórmula matemática debe ser: `rear = (rear + 1) % tamaño` y `front = (front + 1) % tamaño`. Para detectar los casos límite: la cola se considera vacía si la cantidad actual de elementos (`size`) es 0. Se considera llena si la cantidad de elementos es igual al tamaño total del arreglo (`size == tamaño`). Para verificar que el resultado sea correcto, quiero ejecutar una prueba que consista en instanciar una cola de tamaño 3, llenarla con tres elementos, hacer un `dequeue` del primero, y luego hacer un `enqueue` de un elemento nuevo para demostrar visualmente que se reutiliza el índice 0 exitosamente.
*/

import java.util.Arrays;

/**
 * Cola circular FIFO de enteros implementada sobre un arreglo de tamaño fijo.
 */
public class ColaCircularEnteros {
    private final int[] elementos;
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    /**
     * Crea una cola circular con la capacidad indicada.
     *
     * @param capacidad cantidad máxima de elementos
     * @throws IllegalArgumentException si la capacidad no es positiva
     */
    public ColaCircularEnteros(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        elementos = new int[capacidad];
    }

    /**
     * Agrega un valor al final de la cola.
     *
     * @param valor entero que se agrega
     * @throws IllegalStateException si la cola está llena
     */
    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede encolar: la cola está llena.");
        }

        rear = (rear + 1) % elementos.length;
        elementos[rear] = valor;
        size++;
    }

    /**
     * Retira y devuelve el primer valor de la cola.
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
        front = (front + 1) % elementos.length;
        size--;
        return valorRetirado;
    }

    /**
     * Consulta el primer valor sin retirarlo.
     *
     * @return primer valor de la cola
     * @throws IllegalStateException si la cola está vacía
     */
    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar el frente: la cola está vacía.");
        }
        return elementos[front];
    }

    /**
     * Indica si la cola no contiene elementos.
     *
     * @return true si la cola está vacía
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Indica si el arreglo está completamente ocupado por elementos de la cola.
     *
     * @return true si la cola está llena
     */
    public boolean isFull() {
        return size == elementos.length;
    }

    /**
     * Devuelve la cantidad actual de elementos.
     *
     * @return tamaño lógico de la cola
     */
    public int size() {
        return size;
    }

    /**
     * Imprime el arreglo físico y los índices para visualizar el estado circular.
     */
    public void mostrarEstado() {
        System.out.println("Arreglo: " + Arrays.toString(elementos)
                + " | front: " + front
                + " | rear: " + rear
                + " | size: " + size);
    }

    /**
     * Demuestra cómo rear vuelve al índice 0 y reutiliza una posición liberada.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        ColaCircularEnteros cola = new ColaCircularEnteros(3);

        System.out.println("Estado inicial:");
        cola.mostrarEstado();

        System.out.println("\nEncolando 10:");
        cola.enqueue(10);
        cola.mostrarEstado();

        System.out.println("\nEncolando 20:");
        cola.enqueue(20);
        cola.mostrarEstado();

        System.out.println("\nEncolando 30:");
        cola.enqueue(30);
        cola.mostrarEstado();
        System.out.println("¿Está llena?: " + cola.isFull());

        int valorRetirado = cola.dequeue();
        System.out.println("\nDesencolando el primer elemento: " + valorRetirado);
        cola.mostrarEstado();

        System.out.println("\nEncolando 40 en la posición liberada:");
        cola.enqueue(40);
        cola.mostrarEstado();
        System.out.println("Nuevo frente (debe ser 20): " + cola.front());
        System.out.println("¿Está llena?: " + cola.isFull());
    }
}