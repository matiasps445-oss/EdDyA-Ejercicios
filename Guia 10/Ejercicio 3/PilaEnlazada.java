/*
Prompt inicial utilizado:
Quiero modificar nuestra implementación anterior de Pila enlazada de enteros para que sea una Pila Genérica utilizando la sintaxis <T> de Java.
Usar genéricos en Java significa que podemos diseñar la estructura de datos una sola vez y permitir que el compilador garantice la seguridad de tipos (Type Safety) dinámicamente. Esto nos evita tener que programar una 'PilaString', 'PilaInteger' o 'PilaTorneo' por separado.
La lógica fundamental de la pila (el comportamiento LIFO, el manejo del puntero 'head' y la complejidad O(1) en push/pop) no depende en absoluto del tipo de dato almacenado. A la estructura solo le interesa cómo enlazar los nodos (las 'cajas'); el contenido de la caja (la carga útil) es irrelevante para los algoritmos de apilar o desapilar, por lo que la separación de responsabilidades es perfecta.
Para la estructura, tanto Nodo como PilaEnlazada deben recibir el parámetro <T>.
Para comprobar que la implementación funciona con cualquier tipo de dato, el método main debe instanciar y probar tres pilas distintas: una PilaEnlazada<Integer> para números, una PilaEnlazada<String> para textos, y una PilaEnlazada<Torneo> para probar con objetos propios (simulando un sistema de gestión de torneos con atributos básicos como nombre y cantidad de participantes).

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt justificó claramente que la lógica de los punteros no se ve afectada por el tipo de dato almacenado y requirió las tres pruebas específicas, logrando una estructura genérica segura desde el primer intento.
*/
import java.util.EmptyStackException;
import java.util.Objects;

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

class Torneo {
    private final String nombre;
    private final int cantidadParticipantes;

    public Torneo(String nombre, int cantidadParticipantes) {
        this.nombre = nombre;
        this.cantidadParticipantes = cantidadParticipantes;
    }

    @Override
    public String toString() {
        return nombre + " (" + cantidadParticipantes + " participantes)";
    }
}

public class PilaEnlazada<T> {
    private Nodo<T> head;
    private int size;

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new EmptyStackException();
        }

        T datoRetirado = head.getDato();
        head = head.getSiguiente();
        size--;
        return datoRetirado;
    }

    public T consultarTope() {
        if (estaVacia()) {
            throw new EmptyStackException();
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
            System.out.println("Pila vacía.");
            return;
        }

        Nodo<T> actual = head;
        System.out.print("Tope -> ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? " -> Fondo" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PilaEnlazada<Integer> numeros = new PilaEnlazada<>();
        numeros.apilar(10);
        numeros.apilar(20);
        System.out.println("Pila Integer:");
        numeros.imprimir();
        System.out.println("Tope: " + numeros.consultarTope());
        System.out.println("¿Contiene 10? " + numeros.contiene(10));
        System.out.println("Desapilado: " + numeros.desapilar());
        numeros.imprimir();

        PilaEnlazada<String> textos = new PilaEnlazada<>();
        textos.apilar("Primero");
        textos.apilar("Segundo");
        System.out.println("\nPila String:");
        textos.imprimir();
        System.out.println("Tope: " + textos.consultarTope());
        System.out.println("¿Contiene 'Primero'? " + textos.contiene("Primero"));
        System.out.println("Desapilado: " + textos.desapilar());
        textos.imprimir();

        Torneo torneoLocal = new Torneo("Torneo local", 16);
        Torneo torneoRegional = new Torneo("Torneo regional", 32);
        PilaEnlazada<Torneo> torneos = new PilaEnlazada<>();
        torneos.apilar(torneoLocal);
        torneos.apilar(torneoRegional);
        System.out.println("\nPila Torneo:");
        torneos.imprimir();
        System.out.println("Tope: " + torneos.consultarTope());
        System.out.println("¿Contiene el torneo local? " + torneos.contiene(torneoLocal));
        System.out.println("Desapilado: " + torneos.desapilar());
        torneos.imprimir();
    }
}