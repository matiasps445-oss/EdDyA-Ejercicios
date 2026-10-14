/*
Quiero implementar en Java un sistema de turnos para atención en una oficina utilizando una estructura de Cola (Queue) basada en arreglos. Cada persona que llega recibe un turno (representado por su nombre en un String).
Este problema utiliza obligatoriamente el principio FIFO (First In, First Out) y no LIFO (Last In, First Out) porque simula una fila del mundo real: lo justo y lógico es que el primero en llegar sea el primero en ser atendido. Si utilizáramos LIFO (como en una Pila), la última persona en llegar sería atendida de inmediato, dejando a los primeros esperando indefinidamente.
El sistema debe tener las siguientes operaciones: agregar persona (equivalente a enqueue), atender siguiente persona (equivalente a dequeue, que retira y devuelve a la persona) y consultar quién está primero (equivalente a front).
Para la prueba de ejecución, quiero agregar a 'Ana', 'Pedro' y 'Lucia'. Luego consultar quién está primero (debería ser Ana), atender a la siguiente persona (retirando a Ana) y volver a consultar quién está primero (debería ser Pedro).
*/

/**
 * Cola circular FIFO de nombres para administrar turnos de atención.
 */
public class ColaTurnos {
    private final String[] nombres;
    // front señala al siguiente turno; rear señala el último turno agregado.
    private int front = 0;
    private int rear = -1;
    private int size = 0;

    /**
     * Crea una cola de turnos con capacidad fija.
     *
     * @param capacidad cantidad máxima de personas en espera
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public ColaTurnos(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        nombres = new String[capacidad];
    }

    /**
     * Agrega una persona al final de la fila.
     *
     * @param nombre nombre de la persona
     * @throws IllegalStateException si la cola está llena
     */
    public void agregarPersona(String nombre) {
        if (estaLlena()) {
            throw new IllegalStateException("No se puede agregar: la cola de turnos está llena.");
        }

        // El módulo permite volver al inicio y reutilizar espacios ya liberados.
        rear = (rear + 1) % nombres.length;
        nombres[rear] = nombre;
        size++;
    }

    /**
     * Atiende y devuelve a la persona que lleva más tiempo esperando.
     *
     * @return nombre de la persona atendida
     * @throws IllegalStateException si la cola está vacía
     */
    public String atenderSiguiente() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay personas esperando para atender.");
        }

        String personaAtendida = nombres[front];
        nombres[front] = null;
        front = (front + 1) % nombres.length;
        size--;
        return personaAtendida;
    }

    /**
     * Consulta quién está primero sin retirarlo de la cola.
     *
     * @return nombre de la primera persona en espera
     * @throws IllegalStateException si la cola está vacía
     */
    public String consultarPrimero() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay personas esperando para consultar.");
        }
        return nombres[front];
    }

    private boolean estaVacia() {
        return size == 0;
    }

    private boolean estaLlena() {
        return size == nombres.length;
    }

    /**
     * Demuestra la atención FIFO con los nombres indicados en el ejercicio.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        ColaTurnos cola = new ColaTurnos(3);

        cola.agregarPersona("Ana");
        cola.agregarPersona("Pedro");
        cola.agregarPersona("Lucia");

        System.out.println("Primera persona: " + cola.consultarPrimero());
        System.out.println("Atendida: " + cola.atenderSiguiente());
        System.out.println("Nueva primera persona: " + cola.consultarPrimero());
    }
}