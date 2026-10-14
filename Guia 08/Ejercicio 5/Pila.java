/*
Quiero implementar en Java una clase llamada Pila genérica (Pila<T>) utilizando una estructura de arreglo interno.
El problema fundamental que resuelve el uso de genéricos es la reutilización de código y la seguridad de tipos (type safety). La gran diferencia con una pila hecha solamente con 'int' (como la del Ejercicio 1), es que la pila de enteros nos obligaría a programar una clase completamente nueva (PilaString, PilaFloat, PilaPersona) por cada tipo de dato distinto que queramos almacenar. Al usar genéricos (<T>), escribimos la lógica una sola vez y Java nos permite instanciarla para guardar de forma segura Integer, String u objetos simples, detectando errores de tipos en tiempo de compilación.
La clase debe permitir las operaciones clásicas (push, pop, peek, isEmpty, isFull y size) usando una variable 'top'. Los casos límite (llena y vacía) deben controlarse arrojando excepciones.
Para verificar que el resultado sea correcto, quiero que la prueba de ejecución en el main instancie dos pilas diferentes: una Pila<Integer> donde se agreguen números, y una Pila<String> donde se agreguen textos. Se debe hacer un pop en ambas y mostrar el resultado por consola para demostrar que la misma clase sirve para múltiples tipos de datos.
*/

/**
 * Pila LIFO genérica de capacidad fija respaldada por un arreglo.
 *
 * @param <T> tipo de elementos almacenados
 */
public class Pila<T> {
    private final Object[] elementos;
    // top es el índice del elemento más reciente; -1 representa una pila vacía.
    private int top = -1;

    /**
     * Crea una pila con la capacidad especificada.
     *
     * @param capacidad cantidad máxima de elementos
     * @throws IllegalArgumentException si la capacidad es menor que uno
     */
    public Pila(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        elementos = new Object[capacidad];
    }

    /**
     * Inserta un elemento en el tope de la pila.
     *
     * @param elemento valor que se agregará
     * @throws IllegalStateException si la pila está llena
     */
    public void push(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("No se puede insertar: la pila está llena.");
        }

        top++;
        elementos[top] = elemento;
    }

    /**
     * Retira y devuelve el elemento del tope.
     *
     * @return elemento retirado
     * @throws IllegalStateException si la pila está vacía
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede retirar: la pila está vacía.");
        }

        T elemento = (T) elementos[top];
        elementos[top] = null;
        top--;
        return elemento;
    }

    /**
     * Devuelve el elemento del tope sin retirarlo.
     *
     * @return elemento ubicado en el tope
     * @throws IllegalStateException si la pila está vacía
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("No se puede consultar: la pila está vacía.");
        }
        return (T) elementos[top];
    }

    /**
     * Indica si la pila está vacía.
     *
     * @return true si no contiene elementos
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Indica si la pila alcanzó su capacidad máxima.
     *
     * @return true si no hay espacio para más elementos
     */
    public boolean isFull() {
        return top == elementos.length - 1;
    }

    /**
     * Devuelve la cantidad actual de elementos.
     *
     * @return número de elementos almacenados
     */
    public int size() {
        return top + 1;
    }

    /**
     * Demuestra que la misma pila genérica puede almacenar distintos tipos.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Pila<Integer> pilaNumeros = new Pila<>(3);
        pilaNumeros.push(10);
        pilaNumeros.push(20);

        Pila<String> pilaTextos = new Pila<>(3);
        pilaTextos.push("hola");
        pilaTextos.push("mundo");

        System.out.println("Pop de Pila<Integer>: " + pilaNumeros.pop());
        System.out.println("Tope de Pila<Integer>: " + pilaNumeros.peek());
        System.out.println("Pop de Pila<String>: " + pilaTextos.pop());
        System.out.println("Tope de Pila<String>: " + pilaTextos.peek());
    }
}