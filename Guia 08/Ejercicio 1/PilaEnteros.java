/*
Quiero implementar en Java una clase llamada PilaEnteros utilizando una estructura de datos de arreglo de tamaño fijo.
El comportamiento que espero obtener es el de una estructura LIFO (Last In, First Out), donde el último elemento en entrar sea el primero en salir.
La clase debe tener las operaciones fundamentales: push (insertar), pop (eliminar y devolver), peek (ver el tope sin eliminar), isEmpty (saber si está vacía), isFull (saber si está llena) y size (cantidad actual de elementos).
Para manejar el arreglo interno, utilizaré una variable entera llamada 'top' (tope). El valor inicial de 'top' debe ser -1, lo que indica lógicamente que la pila está vacía porque no apunta a ningún índice válido del arreglo.
Al hacer 'push', primero se debe incrementar 'top' en 1 y luego guardar el valor en esa nueva posición. Al hacer 'pop', se recupera el valor en la posición 'top' y luego se decrementa 'top' en 1.
Los casos límite que deben contemplarse son:
1. Cuando la pila está vacía (top == -1), hacer un 'pop' o 'peek' debe arrojar una excepción o mensaje de error porque no hay elementos para sacar.
2. Cuando la pila está llena (top == tamaño del arreglo - 1), hacer un 'push' debe arrojar un error de desbordamiento (Stack Overflow) impidiendo agregar más elementos.
Para verificar el código, quiero hacer pruebas de ejecución agregando tres números, verificando el tope, sacando uno y comprobando el tamaño.
*/

/**
 * Pila LIFO de enteros implementada mediante un arreglo de capacidad fija.
 */
public class PilaEnteros {
    private final int[] elementos;
    // top es el índice del elemento más reciente; -1 representa una pila vacía.
    private int top = -1;

    /**
     * Crea una pila con la capacidad especificada.
     *
     * @param capacidad cantidad máxima de elementos que puede contener la pila
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public PilaEnteros(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        elementos = new int[capacidad];
    }

    /**
     * Inserta un valor en el tope de la pila.
     *
     * @param valor entero que se agregará
     * @throws IllegalStateException si la pila está llena
     */
    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("No se puede insertar: la pila está llena.");
        }

        // La capacidad se valida antes de avanzar top para no escribir fuera del arreglo.
        top++;
        elementos[top] = valor;
    }

    /**
     * Elimina y devuelve el valor que está en el tope.
     *
     * @return valor que se retiró
     * @throws IllegalStateException si la pila está vacía
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede retirar: la pila está vacía.");
        }

        int valorRetirado = elementos[top];
        top--;
        return valorRetirado;
    }

    /**
     * Devuelve el valor del tope sin retirarlo.
     *
     * @return valor ubicado en el tope
     * @throws IllegalStateException si la pila está vacía
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar: la pila está vacía.");
        }
        return elementos[top];
    }

    /**
     * Indica si la pila no contiene elementos.
     *
     * @return true si está vacía; de lo contrario, false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Indica si la pila alcanzó su capacidad máxima.
     *
     * @return true si está llena; de lo contrario, false
     */
    public boolean isFull() {
        return top == elementos.length - 1;
    }

    /**
     * Devuelve la cantidad actual de elementos.
     *
     * @return número de elementos en la pila
     */
    public int size() {
        return top + 1;
    }

    /**
     * Demuestra las operaciones y el comportamiento LIFO de la pila.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        PilaEnteros pila = new PilaEnteros(3);

        System.out.println("Vacía al inicio: " + pila.isEmpty());
        System.out.println("Llena al inicio: " + pila.isFull());
        System.out.println("Tamaño inicial: " + pila.size());

        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("Tope con peek: " + pila.peek());
        System.out.println("Tamaño después de tres push: " + pila.size());
        System.out.println("Llena después de tres push: " + pila.isFull());

        System.out.println("Valor retirado con pop: " + pila.pop());
        System.out.println("Nuevo tope: " + pila.peek());
        System.out.println("Tamaño después del pop: " + pila.size());
        System.out.println("Vacía después del pop: " + pila.isEmpty());
        System.out.println("Llena después del pop: " + pila.isFull());

        pila.pop();
        pila.pop();
        System.out.println("Vacía después de retirar todos los valores: " + pila.isEmpty());
        System.out.println("Tamaño final: " + pila.size());
    }
}