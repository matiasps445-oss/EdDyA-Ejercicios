/*
Prompt inicial utilizado:
Quiero tomar nuestra implementación de lista enlazada simple que usa números enteros (int) y transformarla en una versión genérica utilizando la sintaxis <T> de Java.
Para esto, tanto la clase del nodo como la de la lista deben actualizarse a 'Nodo<T>' y 'ListaEnlazada<T>'. El único cambio real en el código interno ocurrirá en el atributo que almacena la información (pasará de 'int dato' a 'T dato') y en las firmas de los métodos que reciben o devuelven ese dato (como insertar, buscar o modificar).
Las partes del código que se mantienen exactamente iguales son todas las operaciones estructurales: los algoritmos de recorrido, inserción, eliminación e inversión no cambian en lo absoluto. Esto se debe a que la lógica de manipular punteros (head, getSiguiente, setSiguiente) es completamente independiente del tipo de dato almacenado; la lista solo conecta 'cajas' (nodos) sin importarle qué hay adentro de ellas.
Para verificar que la transformación fue exitosa y segura, el método main debe instanciar dos listas distintas: una 'ListaEnlazada<Integer>' donde se inserten números, y una 'ListaEnlazada<String>' donde se inserten palabras, demostrando el polimorfismo y la reutilización del código.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt identificó correctamente que la lógica de los punteros es agnóstica al tipo de dato, y especificó claramente los cambios en las firmas de los métodos, logrando una clase genérica funcional en el primer intento.
*/
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

public class ListaEnlazada<T> {
    private Nodo<T> head;
    private int size;

    public ListaEnlazada() {
        head = null;
        size = 0;
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = head;
        Nodo<T> siguiente;

        while (actual != null) {
            siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);
            anterior = actual;
            actual = siguiente;
        }

        head = anterior;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        if (head == null) {
            System.out.println("Lista vacía.");
            return;
        }

        Nodo<T> actual = head;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? "" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaEnlazada<Integer> numeros = new ListaEnlazada<>();
        numeros.insertarAlFinal(10);
        numeros.insertarAlFinal(20);
        numeros.insertarAlFinal(30);

        ListaEnlazada<String> palabras = new ListaEnlazada<>();
        palabras.insertarAlFinal("Java");
        palabras.insertarAlFinal("Listas");
        palabras.insertarAlFinal("Genéricas");

        System.out.println("Lista de Integer:");
        numeros.imprimir();
        System.out.println("Lista de String:");
        palabras.imprimir();
    }
}