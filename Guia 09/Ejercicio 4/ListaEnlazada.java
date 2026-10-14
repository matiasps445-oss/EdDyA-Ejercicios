/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'void insertarEnPosicion(int dato, int posicion)' para nuestra lista enlazada simple.
Este método debe ser capaz de manejar tres escenarios distintos de inserción: al inicio (posición 0), en el medio de la lista, y al final (posición igual al size actual).
Primero, se deben validar las posiciones inválidas (posicion < 0 o posicion > size). Notar que posicion == size es válido porque equivale a insertar al final.
Para insertar en el medio, el algoritmo debe recorrer la lista hasta ubicarse en el nodo 'anterior' a la posición deseada. Una vez allí, la creación y reconexión de los enlaces (referencias) debe hacerse en un orden estrictamente riguroso para no romper la lista:
1. El nuevo nodo debe apuntar al nodo que actualmente le sigue al 'anterior': nuevo.setSiguiente(anterior.getSiguiente());
2. Recién entonces, el nodo 'anterior' debe actualizarse para apuntar al nuevo nodo: anterior.setSiguiente(nuevo);
Si hiciéramos esto al revés, sobrescribiríamos el enlace del nodo anterior antes de guardarlo, perdiendo para siempre el acceso al resto de la lista (quedaría desconectada en la memoria).
Para verificar la funcionalidad, se debe crear una lista, insertar elementos al inicio, luego insertar en la última posición, luego insertar en la posición 1 (el medio), y finalmente intentar insertar en una posición inválida.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue extremadamente detallado al explicar el orden correcto de actualización de las referencias (nuevo.setSiguiente y luego actual.setSiguiente) y advirtió sobre el peligro de perder la lista si se hacía al revés, lo que permitió que la generación del código fuera perfecta.
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

    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException(
                    "Posición " + posicion + " inválida para una lista de tamaño " + size + ".");
        }

        Nodo nuevo = new Nodo(dato);

        if (posicion == 0) {
            nuevo.setSiguiente(head);
            head = nuevo;
        } else {
            Nodo anterior = head;
            for (int indice = 0; indice < posicion - 1; indice++) {
                anterior = anterior.getSiguiente();
            }

            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }

        size++;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
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

        lista.insertarEnPosicion(20, 0);
        System.out.println("Después de insertar 20 al inicio (posición 0):");
        lista.imprimir();

        lista.insertarEnPosicion(10, 0);
        System.out.println("Después de insertar 10 al inicio (posición 0):");
        lista.imprimir();

        lista.insertarEnPosicion(30, lista.getSize());
        System.out.println("Después de insertar 30 al final:");
        lista.imprimir();

        lista.insertarEnPosicion(15, 1);
        System.out.println("Después de insertar 15 en la posición 1:");
        lista.imprimir();

        System.out.println("Tamaño final: " + lista.getSize());

        try {
            lista.insertarEnPosicion(99, lista.getSize() + 1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Error controlado: " + excepcion.getMessage());
        }
    }
}