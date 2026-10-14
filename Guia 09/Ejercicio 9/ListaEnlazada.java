/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'void invertir()' para nuestra lista enlazada simple.
El objetivo de este método es dar vuelta la estructura de la lista alterando sus enlaces, de modo que el último nodo pase a ser el 'head' y el primer nodo termine apuntando a null.
Para lograr invertir la lista sin perder ningún nodo en el proceso, es obligatorio utilizar tres referencias auxiliares (punteros): 'anterior', 'actual' y 'siguiente'.
Inicialmente, 'anterior' empieza en null y 'actual' empieza en 'head'. El algoritmo debe iterar mientras 'actual' no sea null. Dentro del ciclo, el orden de actualización de las referencias es absolutamente crítico:
1. Primero, resguardar el resto de la lista: siguiente = actual.getSiguiente();
2. Segundo, invertir el enlace del nodo actual para que apunte hacia atrás: actual.setSiguiente(anterior);
3. Tercero, avanzar los punteros para la siguiente iteración: anterior = actual; actual = siguiente;
Si alteráramos el enlace del nodo actual antes de guardar la referencia al 'siguiente', desconectaríamos el resto de la lista y se perdería en la memoria. Al terminar el bucle, 'head' debe actualizarse para apuntar al nodo 'anterior', que será el nuevo primer elemento de la lista invertida.
Para probarlo, quiero crear una lista con los valores 10, 20, 30 y 40. Mostrarla, ejecutar invertir(), y volver a mostrarla para comprobar que el orden ahora es 40, 30, 20, 10.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt explicó con total claridad la necesidad de usar las tres referencias auxiliares (anterior, actual, siguiente) y el orden crítico de sus actualizaciones, por lo que el algoritmo de inversión se ejecutó sin perder nodos en la memoria.
*/
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

public class ListaEnlazada {
    private Nodo head;
    private int size;

    public ListaEnlazada() {
        head = null;
        size = 0;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public void invertir() {
        Nodo anterior = null;
        Nodo actual = head;
        Nodo siguiente;

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

        Nodo actual = head;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.getDato());
            actual = actual.getSiguiente();
            System.out.print(actual == null ? "" : " -> ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.println("Lista antes de invertir:");
        lista.imprimir();
        System.out.println("Tamaño: " + lista.getSize());

        lista.invertir();

        System.out.println("Lista después de invertir:");
        lista.imprimir();
        System.out.println("Tamaño: " + lista.getSize());
    }
}