/*
Prompt inicial utilizado:
Quiero implementar en Java una estructura de Cola (Queue) de números enteros utilizando internamente una lista enlazada simple.
Corresponde usar una cola porque necesito un comportamiento estricto FIFO (First In, First Out). Para el funcionamiento interno, es absolutamente necesario utilizar dos referencias: 'head' (frente) y 'tail' (final).
El problema crítico que aparecería si solamente usáramos 'head' es la pérdida de eficiencia: cada vez que quisiéramos encolar un elemento nuevo al final, tendríamos que recorrer toda la lista desde el principio, lo que resultaría en una complejidad de tiempo O(n). Al mantener un puntero 'tail' apuntando siempre al último nodo, podemos encolar directamente en un tiempo constante O(1). Desencolar también será O(1) porque siempre se retira desde el 'head'.
Las operaciones que debe implementar son: encolar, desencolar, consultar el frente, verificar si está vacía, buscar si contiene un valor (lo cual será O(n) mediante recorrido) e imprimir su contenido.
Como casos especiales, si se intenta desencolar o consultar el frente en una cola vacía, debe arrojar una excepción. Además, al desencolar el último elemento, 'tail' también debe actualizarse a null.
Para la prueba, quiero encolar 3 números, consultar el frente, desencolar un elemento y buscar un valor específico.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt identificó correctamente la necesidad imperativa de utilizar los punteros 'head' y 'tail' para mantener la complejidad O(1) en las operaciones FIFO, por lo que el algoritmo resultó eficiente y exacto al primer intento.
*/
import java.util.NoSuchElementException;

class Nodo {
    private final int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

public class ColaEnlazada {
    private Nodo head;
    private Nodo tail;
    private int size;

    public void encolar(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (tail == null) {
            head = nuevo;
            tail = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public int desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("No se puede desencolar: la cola está vacía.");
        }

        int datoRetirado = head.getDato();
        head = head.getSiguiente();
        size--;

        if (head == null) {
            tail = null;
        }

        return datoRetirado;
    }

    public int consultarFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay frente: la cola está vacía.");
        }

        return head.getDato();
    }

    public boolean estaVacia() {
        return head == null;
    }

    public boolean contiene(int dato) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.getDato() == dato) {
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

        Nodo actual = head;
        System.out.print("Frente -> ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? " -> Final" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ColaEnlazada cola = new ColaEnlazada();
        System.out.println("Estado inicial:");
        cola.imprimir();

        cola.encolar(10);
        System.out.println("Después de encolar 10:");
        cola.imprimir();

        cola.encolar(20);
        System.out.println("Después de encolar 20:");
        cola.imprimir();

        cola.encolar(30);
        System.out.println("Después de encolar 30:");
        cola.imprimir();

        System.out.println("Frente: " + cola.consultarFrente());
        cola.imprimir();
        System.out.println("¿Contiene 20? " + cola.contiene(20));
        cola.imprimir();

        System.out.println("Elemento desencolado: " + cola.desencolar());
        cola.imprimir();

        while (!cola.estaVacia()) {
            System.out.println("Elemento desencolado: " + cola.desencolar());
            cola.imprimir();
        }

        System.out.println("¿Está vacía al final? " + cola.estaVacia());
        System.out.println("Tamaño final: " + cola.getSize());

        try {
            cola.desencolar();
        } catch (NoSuchElementException excepcion) {
            System.out.println("Desencolar en vacío: excepción controlada.");
        }

        try {
            cola.consultarFrente();
        } catch (NoSuchElementException excepcion) {
            System.out.println("Consultar frente en vacío: excepción controlada.");
        }
    }
}