/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'int obtener(int posicion)' para nuestra lista enlazada simple. Este método debe devolver el dato almacenado en la posición indicada por el usuario.
Es crucial entender el razonamiento detrás de esto: aunque estemos pidiendo una 'posición' numérica (como si fuera un índice), la lista enlazada no tiene acceso directo a sus elementos en memoria como ocurre con un arreglo tradicional (donde haríamos arreglo[posicion]). Por lo tanto, el algoritmo debe obligatoriamente recorrer la estructura nodo por nodo desde el 'head' llevando una variable contadora hasta alcanzar la posición deseada.
Antes de iniciar el recorrido, el método debe validar de forma estricta si la posición solicitada es inválida (es decir, si posicion < 0 o si posicion >= size). En esos casos, debe arrojar una excepción (por ejemplo, IndexOutOfBoundsException) para proteger la integridad del programa y evitar un NullPointerException.
Para verificar el funcionamiento, se debe crear una lista, agregar 3 elementos y tratar de obtener el elemento en la posición 1 (debería ser el del medio), y luego forzar un error pidiendo una posición fuera de los límites.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue muy explícito al aclarar que la lista enlazada no posee acceso directo por índice y debe recorrerse nodo por nodo, detallando además las validaciones de límites, lo que resultó en un código correcto al primer intento.
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
        Nodo nuevoNodo = new Nodo(dato);

        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }

        size++;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición " + posicion + " inválida para una lista de tamaño " + size + ".");
        }

        Nodo actual = head;
        int contador = 0;

        while (contador < posicion) {
            actual = actual.getSiguiente();
            contador++;
        }

        return actual.getDato();
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
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        lista.imprimir();
        System.out.println("Elemento en la posición 1: " + lista.obtener(1));

        try {
            lista.obtener(3);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Error controlado: " + excepcion.getMessage());
        }

        try {
            lista.obtener(-1);
        } catch (IndexOutOfBoundsException excepcion) {
            System.out.println("Error controlado: " + excepcion.getMessage());
        }
    }
}