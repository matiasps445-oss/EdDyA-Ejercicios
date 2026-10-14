/*
Quiero implementar en Java una clase llamada ColaEnteros utilizando una estructura de arreglo de tamaño fijo.
El comportamiento esperado es el de una estructura FIFO (First In, First Out), donde el primer elemento en entrar debe ser el primero en salir.
La clase debe tener las siguientes operaciones: enqueue (encolar/agregar), dequeue (desencolar/retirar), front (consultar el primero sin retirarlo), isEmpty (saber si está vacía), isFull (saber si está llena) y size (cantidad de elementos).
Para manejar el arreglo interno, utilizaré dos índices: 'front' y 'rear'. El índice 'front' sirve para apuntar a la posición del primer elemento que está listo para salir, mientras que 'rear' sirve para apuntar a la posición del último elemento que ingresó.
Cuando se agrega un elemento (enqueue), el índice 'rear' avanza una posición (se incrementa en 1) y el valor se guarda allí. Cuando se retira un elemento (dequeue), se recupera el valor en la posición 'front' y luego el índice 'front' avanza una posición (se incrementa en 1).
Los casos límite son: intentar hacer dequeue o front cuando la cola está vacía (debe lanzar error) e intentar hacer enqueue cuando la cola está llena (rear alcanza el límite del arreglo, debe lanzar error).
Para verificar la ejecución, quiero encolar los números 10, 20 y 30. Luego consultar el front, desencolar un elemento y verificar que el nuevo front sea 20 y el size sea 2.
*/

/**
 * Cola FIFO de enteros basada en un arreglo lineal de capacidad fija.
 */
public class ColaEnteros {
    private final int[] elementos;
    // front apunta al próximo elemento en salir; rear apunta al último que ingresó.
    private int front = 0;
    private int rear = -1;

    /**
     * Crea una cola con la capacidad indicada.
     *
     * @param capacidad cantidad máxima de elementos
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public ColaEnteros(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        elementos = new int[capacidad];
    }

    /**
     * Agrega un valor al final de la cola.
     *
     * @param valor entero que se encolará
     * @throws IllegalStateException si la cola está llena
     */
    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede encolar: la cola está llena.");
        }

        rear++;
        elementos[rear] = valor;
    }

    /**
     * Retira y devuelve el primer valor de la cola.
     *
     * @return valor que se desencoló
     * @throws IllegalStateException si la cola está vacía
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede desencolar: la cola está vacía.");
        }

        int valorRetirado = elementos[front];
        front++;

        // Reinicia los índices al consumir el último elemento para reutilizar el arreglo.
        if (front > rear) {
            front = 0;
            rear = -1;
        }

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
            throw new IllegalStateException("No se puede consultar: la cola está vacía.");
        }
        return elementos[front];
    }

    /**
     * Indica si la cola no contiene elementos.
     *
     * @return true si está vacía
     */
    public boolean isEmpty() {
        return front > rear;
    }

    /**
     * Indica si rear llegó al último índice del arreglo.
     *
     * @return true si no quedan posiciones al final del arreglo
     */
    public boolean isFull() {
        return rear == elementos.length - 1;
    }

    /**
     * Devuelve la cantidad actual de elementos.
     *
     * @return cantidad de elementos en la cola
     */
    public int size() {
        return isEmpty() ? 0 : rear - front + 1;
    }

    /**
     * Demuestra el comportamiento FIFO con los valores del ejercicio.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        ColaEnteros cola = new ColaEnteros(3);

        System.out.println("Empty al inicio: " + cola.isEmpty());
        System.out.println("Full al inicio: " + cola.isFull());

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        System.out.println("Front inicial: " + cola.front());
        System.out.println("Full después de encolar tres valores: " + cola.isFull());
        System.out.println("Dequeue: " + cola.dequeue());
        System.out.println("Nuevo front: " + cola.front());
        System.out.println("Size: " + cola.size());
        System.out.println("Empty después de dequeue: " + cola.isEmpty());
    }
}