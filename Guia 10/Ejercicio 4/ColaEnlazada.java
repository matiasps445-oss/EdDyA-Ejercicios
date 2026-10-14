/*
Prompt inicial utilizado:
Quiero modificar nuestra implementación anterior de Cola enlazada para que sea una Cola Genérica utilizando la sintaxis <T> de Java.
El objetivo es que la estructura pueda almacenar cualquier tipo de dato de forma segura. Es fundamental entender que el comportamiento FIFO (First In, First Out) se mantiene intacto independientemente del tipo de dato almacenado. Esto ocurre porque la lógica matemática de la cola (encolar siempre al final usando el puntero 'tail' y desencolar siempre al frente usando el puntero 'head') rige exclusivamente sobre los Nodos contenedores y sus enlaces. A la estructura de la cola no le importa qué hay dentro del nodo (la carga útil); solo le importa el orden de los eslabones.
Para comprobar que la implementación funciona correctamente, el método main debe instanciar y probar dos colas distintas: una 'ColaEnlazada<String>' para almacenar nombres de personas, y una 'ColaEnlazada<Cliente>' para almacenar objetos de una clase propia 'Cliente' (que debe crearse con atributos básicos como nombre y número de cliente). Se deben encolar y desencolar elementos en ambas colas para demostrar su versatilidad.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó a la perfección cómo la estructura FIFO opera sobre las referencias de los nodos y no sobre los datos en sí, garantizando una implementación genérica sólida y funcional al primer intento.
*/
import java.util.Objects;
import java.util.NoSuchElementException;

class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}

class Cliente {
    private final String nombre;
    private final int numeroCliente;

    public Cliente(String nombre, int numeroCliente) {
        this.nombre = nombre;
        this.numeroCliente = numeroCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    @Override
    public String toString() {
        return nombre + " (cliente #" + numeroCliente + ")";
    }
}

public class ColaEnlazada<T> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("No se puede desencolar: la cola está vacía.");
        }

        T datoRetirado = head.getDato();
        head = head.getSiguiente();
        size--;

        if (head == null) {
            tail = null;
        }

        return datoRetirado;
    }

    public T consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay frente: la cola está vacía.");
        }

        return head.getDato();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Cola vacía.");
            return;
        }

        Nodo<T> actual = head;
        System.out.print("Frente -> ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? " -> Final" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ColaEnlazada<String> nombres = new ColaEnlazada<>();
        System.out.println("Cola de nombres:");
        System.out.println("Encolar Ana");
        nombres.encolar("Ana");
        nombres.imprimir();
        System.out.println("Encolar Luis");
        nombres.encolar("Luis");
        nombres.imprimir();
        System.out.println("Desencolar: " + nombres.desencolar());
        nombres.imprimir();

        ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();
        Cliente clienteUno = new Cliente("Marta", 101);
        Cliente clienteDos = new Cliente("Diego", 102);

        System.out.println("\nCola de clientes:");
        System.out.println("Encolar " + clienteUno);
        clientes.encolar(clienteUno);
        clientes.imprimir();
        System.out.println("Encolar " + clienteDos);
        clientes.encolar(clienteDos);
        clientes.imprimir();
        System.out.println("Desencolar: " + clientes.desencolar());
        clientes.imprimir();
    }
}