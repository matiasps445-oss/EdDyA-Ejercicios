/*
Prompt inicial utilizado:
Quiero implementar en Java un método 'boolean buscar(int dato)' para la lista enlazada simple que creamos anteriormente.
A diferencia de un arreglo tradicional, en una lista enlazada no podemos acceder a los elementos directamente utilizando un índice numérico (como arreglo[i]). Esto se debe a que los nodos se crean dinámicamente y están dispersos en distintas posiciones de la memoria de la computadora, no están en bloques contiguos.
Por lo tanto, la búsqueda requiere obligatoriamente un recorrido secuencial. El algoritmo debe utilizar un nodo auxiliar (puntero temporal) que comience apuntando a 'head'. A partir de ahí, debe avanzar secuencialmente de nodo en nodo (usando getSiguiente()) mientras el nodo actual no sea null. En cada paso, debe verificar si el dato almacenado coincide con el buscado para devolver 'true'. Si el bucle finaliza porque se llegó a 'null', significa que el elemento no existe en la lista y debe devolver 'false'.

Ajustes realizados luego de la primera respuesta de OpenCode:
Ninguno. El prompt fue claro al explicar por qué no se puede acceder directamente por índice debido a la memoria no contigua, y describió correctamente el recorrido secuencial necesario, por lo que el algoritmo funcionó de inmediato.
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

    public void insertarAlInicio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.setSiguiente(head);
        head = nuevoNodo;
        size++;
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

    public boolean buscar(int dato) {
        Nodo actual = head;

        while (actual != null) {
            if (actual.getDato() == dato) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void imprimir() {
        if (estaVacia()) {
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

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        System.out.println("Buscar 10 en lista vacía: " + lista.buscar(10));

        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlInicio(5);

        System.out.println("Contenido antes de buscar:");
        lista.imprimir();
        System.out.println("Tamaño antes de buscar: " + lista.getSize());

        System.out.println("Buscar 5 (presente): " + lista.buscar(5));
        System.out.println("Buscar 20 (presente): " + lista.buscar(20));
        System.out.println("Buscar 99 (ausente): " + lista.buscar(99));

        System.out.println("Contenido después de buscar:");
        lista.imprimir();
        System.out.println("Tamaño después de buscar: " + lista.getSize());
    }
}